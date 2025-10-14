package wanderer2357.redwire.service;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import wanderer2357.redwire.annotation.Patchable;
import wanderer2357.redwire.dto.ClientDto;
import wanderer2357.redwire.exception.InvalidPatchRequestException;
import wanderer2357.redwire.exception.ResourceNotFoundException;
import wanderer2357.redwire.mapper.ClientDtoMapper;
import wanderer2357.redwire.mapper.ClientEntityMapper;
import wanderer2357.redwire.model.ClientEntity;
import wanderer2357.redwire.repository.ClientRepository;
import wanderer2357.redwire.util.EmailUtil;
import wanderer2357.redwire.util.PhoneUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Validated
public class ClientService {
	
	private static final Logger log = LoggerFactory.getLogger(ClientService.class);
	
	private final ClientRepository clientRepository;
	private final ClientDtoMapper clientDtoMapper;
	private final ClientEntityMapper clientEntityMapper;
	private final ObjectMapper objectMapper;
	private final static Map<String, Field> PATCH_ALLOWED_FIELDS;
	private final EmailUtil emailUtil;
	private final PhoneUtil phoneUtil;
	
	public ClientService(ClientRepository clientRepository,
			ClientDtoMapper clientDtoMapper,
			ClientEntityMapper clientEntityMapper,
			ObjectMapper objectMapper,
			EmailUtil emailUtil,
			PhoneUtil phoneUtil) {
		this.clientRepository = clientRepository;
		this.clientDtoMapper = clientDtoMapper;
		this.clientEntityMapper = clientEntityMapper;
		this.objectMapper = objectMapper;
		this.emailUtil = emailUtil;
		this.phoneUtil = phoneUtil;
	}
	
	static {
	    Map<String, Field> patchMap = new HashMap<>();
	    for (Field field : ClientEntity.class.getDeclaredFields()) {
	        if (field.isAnnotationPresent(Patchable.class)) {
	            patchMap.put(field.getName(), field);
	        }
	    }
	    PATCH_ALLOWED_FIELDS = Collections.unmodifiableMap(patchMap);
	}
	
	public Page<ClientDto> getAllClients(Pageable pageable) {
	    return clientRepository.findAll(pageable)
	            .map(clientDtoMapper);
	}
	
	public ClientDto getClientById(Long id) {
        return clientRepository.findById(id)
        		.map(clientDtoMapper)
        		.orElseThrow(() -> {
                    log.warn("Client ID {} not found", id);
                    return new ResourceNotFoundException("Could not find specified client");
                });
    }
	
	public ClientDto saveClient(@Valid ClientDto clientDto) {
		if (clientRepository.existsByEmail(clientDto.getEmail())) {
	        throw new InvalidPatchRequestException("Email already in use");
	    }
	    if (clientRepository.existsByPhone(clientDto.getPhone())) {
	        throw new InvalidPatchRequestException("Phone number already in use");
	    }
		
		ClientEntity savedClientEntity = clientRepository.save(clientEntityMapper.apply(clientDto));
		ClientDto savedClientDto = clientDtoMapper.apply(savedClientEntity);
		log.debug("Saved new client with id {}", savedClientDto.getId());
		return savedClientDto;
	}

    public List<ClientDto> getClientsByEmail(String email) {
        return clientRepository.findByEmail(email)
        		.stream()
				.map(clientDtoMapper)
				.toList();
    }

    public List<ClientDto> getClientsByPhone(String phone) {
    	return clientRepository.findByPhone(phone)
        		.stream()
				.map(clientDtoMapper)
				.toList();
        
    }
    
    public ClientDto patchClient(Long id, Map<String, Object> updates) {
    	log.info("Patching client with ID {}. Fields: {}", id, updates.keySet());
    	
        ClientEntity clientEntity = clientRepository.findById(id)
            .orElseThrow(() -> {
                log.warn("Client ID {} not found", id);
                return new ResourceNotFoundException("Could not find specified client");
            });
        
        updates.forEach((key, value) -> {
        	
        	Field clientEntityField = PATCH_ALLOWED_FIELDS.get(key);
        	
        	if (clientEntityField == null) {
        	    log.warn("Attempt to patch patch-prohibited or unknown field '{}'", key);
        	    throw new InvalidPatchRequestException("Patch-prohibited or unknown field (" + key + ")");
        	}
            
            if ("email".equals(key)) {
            	if (!(value instanceof String email) || !emailUtil.isValidEmail(email)) {
                    log.warn("Invalid email format in patch request");
                    throw new InvalidPatchRequestException("Invalid email format");
                }
            	else if (clientRepository.existsByEmailAndIdNot(email, id)) {
                    log.warn("Email is already in use");
                    throw new InvalidPatchRequestException("Email already in use");
                }
            }
            
            if ("phone".equals(key)) {
            	if (!(value instanceof String phone) || !phoneUtil.isValidPhoneNumber(phone)) {
                    log.warn("Invalid phone format in patch request");
                    throw new InvalidPatchRequestException("Invalid phone format");
                }
            	else if (clientRepository.existsByPhoneAndIdNot(phone, id)) {
                    log.warn("Phone is already in use");
                    throw new InvalidPatchRequestException("Phone already in use");
                }
            }
                        
            clientEntityField.setAccessible(true);
            Class<?> expectedType = clientEntityField.getType();
            
            try {
                ReflectionUtils.setField(clientEntityField, clientEntity, 
                		objectMapper.convertValue(value, expectedType));
                log.debug("Patched field '{}' with value", key);
            } catch (IllegalArgumentException e) {
            	log.error("Type mismatch on field '{}' while patching client ID {}", key, id, e);
            	throw new InvalidPatchRequestException("Type mismatch on field '" + key +
            		    "'. Expected: " + expectedType.getSimpleName() +
            		    ", but received: " + value.getClass().getSimpleName());
            }
        });

        ClientDto result = clientDtoMapper.apply(clientRepository.save(clientEntity));
        log.info("Successfully patched client ID {}", id);
        return result;
    }	

}

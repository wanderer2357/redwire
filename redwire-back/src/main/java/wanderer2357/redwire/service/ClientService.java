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
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import wanderer2357.redwire.annotation.Patchable;
import wanderer2357.redwire.dto.ClientDto;
import wanderer2357.redwire.exception.InvalidPatchClientRequestException;
import wanderer2357.redwire.exception.InvalidSaveClientRequestException;
import wanderer2357.redwire.exception.ClientNotFoundException;
import wanderer2357.redwire.exception.InvalidClientPhoneException;
import wanderer2357.redwire.mapper.ClientDtoMapper;
import wanderer2357.redwire.mapper.ClientEntityMapper;
import wanderer2357.redwire.model.ClientEntity;
import wanderer2357.redwire.repository.ClientRepository;
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
	private final PhoneUtil phoneUtil;
	private final Validator validator 
		= Validation.buildDefaultValidatorFactory().getValidator();
	
	public ClientService(ClientRepository clientRepository,
			ClientDtoMapper clientDtoMapper,
			ClientEntityMapper clientEntityMapper,
			ObjectMapper objectMapper,
			PhoneUtil phoneUtil) {
		this.clientRepository = clientRepository;
		this.clientDtoMapper = clientDtoMapper;
		this.clientEntityMapper = clientEntityMapper;
		this.objectMapper = objectMapper;
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
	
	public Page<ClientDto> getClientPage(Pageable pageable) {
	    return clientRepository.findAll(pageable)
	            .map(clientDtoMapper);
	}
	
	public ClientDto getClientById(Long id) {
        return clientRepository.findById(id)
        		.map(clientDtoMapper)
        		.orElseThrow(() -> {
                    log.warn("Client ID {} not found", id);
                    throw new ClientNotFoundException("Could not find specified client (id : " + id + ")");
                });
    }
	
	public ClientDto getClientByEmail(String email) {
    	List<ClientDto> clientDtoList = clientRepository.findByEmail(email)
    			.stream()
    			.map(clientDtoMapper)
    			.toList();
    	    	
    	if (clientDtoList.isEmpty()) {
    		log.warn("Client email not found");
            throw new ClientNotFoundException("Could not find specified client (email : " + email + ")");
    	}
        return clientDtoList.get(0);
    }

    public ClientDto getClientByPhone(String phone) {
    	List<ClientDto> clientDtoList = clientRepository.findByPhone(phone)
    			.stream()
    			.map(clientDtoMapper)
    			.toList();
    	
    	if (clientDtoList.isEmpty()) {
    		log.warn("Client phone not found");
            throw new ClientNotFoundException("Could not find specified client (phone : " + phone + ")");
    	}
    	return clientDtoList.get(0);
    }
	
	public ClientDto saveClient(@Valid ClientDto clientDto) {
		if (clientRepository.existsByEmail(clientDto.getEmail())) {
			log.warn("Aborting client save: email already in use");
	        throw new InvalidSaveClientRequestException("Email already in use");
	    }
		
		if (clientRepository.existsByPhone(clientDto.getPhone())) {
			log.warn("Aborting client save: phone already in use");
	        throw new InvalidSaveClientRequestException("Phone already in use");
	    }
	    
		validateClientPhone(clientDto.getPhone(), null);
		
		ClientEntity savedClientEntity = clientRepository.save(clientEntityMapper.apply(clientDto));
		ClientDto savedClientDto = clientDtoMapper.apply(savedClientEntity);
		log.debug("Saved new client with id {}", savedClientDto.getId());
		return savedClientDto;
	}
    
    public ClientDto patchClient(Long id, Map<String, Object> updates) {
    	log.info("Patching client with ID {}. Fields: {}", id, updates.keySet());
    	
        ClientEntity clientEntity = clientRepository.findById(id)
            .orElseThrow(() -> {
                log.warn("Client ID {} not found", id);
                throw new ClientNotFoundException("Could not find specified client (id : " + id + ")");
            });
        
        Object phoneObject = updates.get("phone");
        if (phoneObject != null && phoneObject instanceof String phoneString) {
        	validateClientPhone(phoneString, id);
        }
        
        updates.forEach((key, value) -> {
        	
        	Field clientEntityField = PATCH_ALLOWED_FIELDS.get(key);
        	
        	if (clientEntityField == null) {
        	    log.warn("Attempt to patch patch-prohibited or unknown field '{}'", key);
        	    throw new InvalidPatchClientRequestException("Patch-prohibited or unknown field (" + key + ")");
        	}
                        
            clientEntityField.setAccessible(true);
            Class<?> expectedType = clientEntityField.getType();
            
            try {
                ReflectionUtils.setField(clientEntityField, clientEntity, 
                		objectMapper.convertValue(value, expectedType));
                log.debug("Patched field '{}' with specified value", key);
            } catch (IllegalArgumentException e) {
            	log.error("Type mismatch on field '{}' while patching client ID {}", key, id, e);
            	throw new InvalidPatchClientRequestException("Type mismatch on field '" + key +
            		    "'. Expected: " + expectedType.getSimpleName() +
            		    ", but received: " + value.getClass().getSimpleName());
            }
        });
        
        validator.validate(clientEntity);
        ClientDto savedClientDto = clientDtoMapper.apply(clientRepository.save(clientEntity));
        log.info("Successfully patched client ID {}", id);
        return savedClientDto;
    }
    
    private void validateClientPhone(String phone, Long id) {
    	if (!phoneUtil.isValidPhoneNumber(phone)) {
            log.warn("Invalid client phone format '{}'", phone);
            throw new InvalidClientPhoneException("Invalid client phone format");
        }
    	else if (clientRepository.existsByPhoneAndIdNot(phone, id)) {
            log.warn("Client phone is already in use");
            throw new InvalidClientPhoneException("Client phone already in use");
        }
    }

}

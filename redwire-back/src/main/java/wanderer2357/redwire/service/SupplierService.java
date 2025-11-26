package wanderer2357.redwire.service;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import wanderer2357.redwire.annotation.Patchable;
import wanderer2357.redwire.dto.SupplierDto;
import wanderer2357.redwire.exception.InvalidPatchClientRequestException;
import wanderer2357.redwire.exception.InvalidPatchSupplierRequestException;
import wanderer2357.redwire.exception.InvalidSaveSupplierRequestException;
import wanderer2357.redwire.exception.InvalidSupplierPhoneException;
import wanderer2357.redwire.exception.SupplierNotFoundException;
import wanderer2357.redwire.mapper.SupplierDtoMapper;
import wanderer2357.redwire.mapper.SupplierEntityMapper;
import wanderer2357.redwire.model.SupplierEntity;
import wanderer2357.redwire.repository.SupplierRepository;
import wanderer2357.redwire.util.PhoneUtil;

@Service
public class SupplierService {
	
	private static final Logger log = LoggerFactory.getLogger(SupplierService.class);
	
	private final SupplierRepository supplierRepository;
	private final SupplierDtoMapper supplierDtoMapper;
	private final SupplierEntityMapper supplierEntityMapper;
	private final static Map<String, Field> PATCH_ALLOWED_FIELDS;
	private final PhoneUtil phoneUtil;
	private final ObjectMapper objectMapper;
	private final Validator validator 
		= Validation.buildDefaultValidatorFactory().getValidator();
	
	public SupplierService(SupplierRepository supplierRepository,
			SupplierDtoMapper supplierDtoMapper,
			SupplierEntityMapper supplierEntityMapper,
			PhoneUtil phoneUtil,
			ObjectMapper objectMapper) {
		this.supplierRepository = supplierRepository;
		this.supplierDtoMapper = supplierDtoMapper;
		this.supplierEntityMapper = supplierEntityMapper;
		this.phoneUtil = phoneUtil;
		this.objectMapper = objectMapper;
	}
	
	static {
		Map<String, Field> patchMap = new HashMap<>();
		for (Field field: SupplierEntity.class.getDeclaredFields() ) {
			if(field.isAnnotationPresent(Patchable.class)) {
				patchMap.put(field.getName(), field);
			}
		}
		PATCH_ALLOWED_FIELDS = Collections.unmodifiableMap(patchMap);
	}
	
	public Page<SupplierDto> getSupplierPage(Pageable pageable) {
		return supplierRepository.findAll(pageable).map(supplierDtoMapper);
	}
	
	public SupplierDto getSupplierById(Long id) {
		return supplierRepository.findById(id)
				.map(supplierDtoMapper)
				.orElseThrow(() -> {
					log.warn("Supplier ID {} not found", id);
					throw new SupplierNotFoundException("Could not find specified supplier (id: " + id +")");
				});
	}
	
	public SupplierDto getSupplierByEmail(String email) {
		List<SupplierDto> supplierDtoList = supplierRepository.findByEmail(email)
				.stream()
				.map(supplierDtoMapper)
				.toList();
		
		if(supplierDtoList.isEmpty()) {
			log.warn("Supplier email not found");
			throw new SupplierNotFoundException("Could not find specified supplier (email: " + email + ")");
		}
		
		return supplierDtoList.get(0);
	}
	
	public SupplierDto getSupplierByPhone(String phone) {
		List<SupplierDto> supplierDtoList = supplierRepository.findByPhone(phone)
				.stream()
				.map(supplierDtoMapper)
				.toList();
		
		if(supplierDtoList.isEmpty()) {
			log.warn("Supplier phone not found");
			throw new SupplierNotFoundException("Could not find specified supplier (phone: " + phone + ")");
		}
		
		return supplierDtoList.get(0);
	}
	
	public SupplierDto saveSupplier(@Valid SupplierDto supplierDto) {
		if(supplierRepository.existsByName(supplierDto.getCompanyName())) {
			throw new InvalidSaveSupplierRequestException("Company name already in use");
		}
		if(supplierRepository.existsByEmail(supplierDto.getEmail())) {
			throw new InvalidSaveSupplierRequestException("Company email already in use");
		}
		if(supplierRepository.existsByPhone(supplierDto.getPhone())) {
			throw new InvalidSaveSupplierRequestException("Company phone already in use");
		}
		
		validateSupplierPhone(supplierDto.getPhone(), null);
		
		SupplierEntity savedSupplierEntity = supplierRepository.save(supplierEntityMapper.apply(supplierDto));
		SupplierDto savedSupplierDto = supplierDtoMapper.apply(savedSupplierEntity);
		log.debug("Saved new supplier with id {}", savedSupplierDto.getId());
		return savedSupplierDto;
	}
	
	public SupplierDto patchSupplier(Long id, Map<String, Object> updates) {
		log.info("Patching supplier with ID {}. Fields: {}", id, updates.keySet());
		
		SupplierEntity supplierEntity = supplierRepository.findById(id)
			.orElseThrow(() -> {
			log.warn("Supplier ID not found", id);
			throw new SupplierNotFoundException("Could not find specified supplier (id = " + id + ")");
			});
		
		Object phoneObject = updates.get("phone");
		if(phoneObject != null && phoneObject instanceof String phoneString) {
			validateSupplierPhone(phoneString, id);
		}
		
		updates.forEach((key, value) -> {
			Field supplierEntityField = PATCH_ALLOWED_FIELDS.get(key);
			
			if (supplierEntityField == null) {
				log.warn("Attempt to patch patch-prohibited or unknown field '{}'", key);
				throw new InvalidPatchSupplierRequestException("Patch-prohibited or unknown field (" + key + ")");
			}
			
			supplierEntityField.setAccessible(true);
			Class<?> expectedType = supplierEntityField.getType();
			
			try {
				ReflectionUtils.setField(supplierEntityField, supplierEntity, 
						objectMapper.convertValue(value, expectedType));;
				log.debug("Patched field '{}' with specified value for supplier ID {}", key, id);
			} catch (IllegalArgumentException e) {
				log.error("Type mismatch on field '{}' while patching supplier ID {}", key, id, e);
            	throw new InvalidPatchClientRequestException("Type mismatch on field '" + key +
            		    "'. Expected: " + expectedType.getSimpleName() +
            		    ", but received: " + value.getClass().getSimpleName());
			}
			
		});
		
		validator.validate(supplierEntity);
		SupplierDto savedSupplierDto = supplierDtoMapper.apply(supplierRepository.save(supplierEntity));
		log.info("Successfully patched supplier ID {}", id);
		return savedSupplierDto;
	}
	
	private void validateSupplierPhone(String phone, Long id) {
		if(!phoneUtil.isValidPhoneNumber(phone)) {
			log.warn("Invalid supplier phone format '{}'", phone);
			throw new InvalidSupplierPhoneException(phone);
		}
		if(supplierRepository.existsByPhoneAndIdNot(phone, id)) {
			log.warn("Supplier phone is aleady in use");
			throw new InvalidSupplierPhoneException("Supplier phone already in use");
		}
	}

}

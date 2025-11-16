package wanderer2357.redwire.service;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import wanderer2357.redwire.annotation.Patchable;
import wanderer2357.redwire.dto.SupplierDto;
import wanderer2357.redwire.exception.InvalidSaveSupplierRequestException;
import wanderer2357.redwire.exception.SupplierNotFoundException;
import wanderer2357.redwire.mapper.SupplierDtoMapper;
import wanderer2357.redwire.mapper.SupplierEntityMapper;
import wanderer2357.redwire.model.SupplierEntity;
import wanderer2357.redwire.repository.SupplierRepository;

@Service
public class SupplierService {
	
	private static final Logger log = LoggerFactory.getLogger(SupplierService.class);
	
	private final SupplierRepository supplierRepository;
	private final SupplierDtoMapper supplierDtoMapper;
	private final SupplierEntityMapper supplierEntityMapper;
	private final static Map<String, Field> PATCH_ALLOWED_FIELDS;
	
	public SupplierService(SupplierRepository supplierRepository,
			SupplierDtoMapper supplierDtoMapper,
			SupplierEntityMapper supplierEntityMapper) {
		this.supplierRepository = supplierRepository;
		this.supplierDtoMapper = supplierDtoMapper;
		this.supplierEntityMapper = supplierEntityMapper;
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
	
	public SupplierDto saveSupplier(@Valid SupplierDto supplierDto) {
		if (supplierRepository.existsByName(supplierDto.getCompanyName())) {
			throw new InvalidSaveSupplierRequestException("Company name already in use");
		}
		return null;
	}

}

package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.SupplierDto;
import wanderer2357.redwire.model.SupplierEntity;

@Component
public class SupplierDtoMapper implements Function<SupplierEntity, SupplierDto>{

	@Override
	public SupplierDto apply(SupplierEntity supplierEntity) {
		return new SupplierDto(supplierEntity.getId(),
				supplierEntity.getCompanyName(),
				supplierEntity.getStreet1(),
				supplierEntity.getStreet2(),
				supplierEntity.getPostalCode(),
				supplierEntity.getRegionStateProvince(),
				supplierEntity.getCountry(),
				supplierEntity.getEmail(),
				supplierEntity.getPhone(),
				supplierEntity.getRepresentativeFirstname(),
				supplierEntity.getRepresentativeLastname(),
				supplierEntity.getRepresentativeEmail(), 
				supplierEntity.getRepresentativePhone(),
				supplierEntity.getStatus(),
				supplierEntity.getCreatedAt(),
				supplierEntity.getUpdatedAt());
	}

}

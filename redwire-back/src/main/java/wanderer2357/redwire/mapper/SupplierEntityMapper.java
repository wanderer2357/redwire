package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.SupplierDto;
import wanderer2357.redwire.model.SupplierEntity;

@Component
public class SupplierEntityMapper implements Function<SupplierDto, SupplierEntity>{

	@Override
	public SupplierEntity apply(SupplierDto supplierDto) {
		return new SupplierEntity(supplierDto.getCompanyName().trim(),
				supplierDto.getStreet1().trim(),
				supplierDto.getStreet2().trim(),
				supplierDto.getPostalCode().trim(),
				supplierDto.getRegionStateProvince().trim(),
				supplierDto.getCountry().trim(),
				supplierDto.getEmail().trim(),
				supplierDto.getPhone().trim(),
				supplierDto.getRepresentativeFirstname().trim(),
				supplierDto.getRepresentativeLastname().trim(),
				supplierDto.getRepresentativeEmail().trim(),
				supplierDto.getRepresentativePhone().trim()
				);
	}

}

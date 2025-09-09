package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.SupplierOrderHeaderDto;
import wanderer2357.redwire.model.SupplierOrderHeaderEntity;

@Component
public class SupplierOrderHeaderDtoMapper implements Function<SupplierOrderHeaderEntity, SupplierOrderHeaderDto>{

	private ImmutableSupplierDtoMapper immutableSupplierDtoMapper;
	
	public SupplierOrderHeaderDtoMapper(ImmutableSupplierDtoMapper immutableSupplierDtoMapper) {
		this.immutableSupplierDtoMapper =  immutableSupplierDtoMapper;
	}
	
	@Override
	public SupplierOrderHeaderDto apply(SupplierOrderHeaderEntity SupplierOrderHeaderEntity) {
		return new SupplierOrderHeaderDto(SupplierOrderHeaderEntity.getId(),
				SupplierOrderHeaderEntity.getSupplierEntity().getId(),
				immutableSupplierDtoMapper.apply(SupplierOrderHeaderEntity.getImmutableSupplier()),
				SupplierOrderHeaderEntity.getOrderStatus(),
				SupplierOrderHeaderEntity.getCreatedAt(),
				SupplierOrderHeaderEntity.getUpdatedAt());
	}

}

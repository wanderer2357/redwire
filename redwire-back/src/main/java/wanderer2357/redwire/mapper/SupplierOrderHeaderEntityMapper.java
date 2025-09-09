package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.SupplierOrderHeaderDto;
import wanderer2357.redwire.model.SupplierEntity;
import wanderer2357.redwire.model.SupplierOrderHeaderEntity;

@Component
public class SupplierOrderHeaderEntityMapper implements Function<SupplierOrderHeaderDto, SupplierOrderHeaderEntity>{

	@Override
	public SupplierOrderHeaderEntity apply(SupplierOrderHeaderDto supplierOrderHeaderDto) {
		return new SupplierOrderHeaderEntity(new SupplierEntity(supplierOrderHeaderDto.getSupplierId()),
				supplierOrderHeaderDto.getOrderStatus());
	}

}

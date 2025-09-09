package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.SupplierOrderDetailDto;
import wanderer2357.redwire.model.ArticleEntity;
import wanderer2357.redwire.model.SupplierOrderDetailEntity;
import wanderer2357.redwire.model.SupplierOrderHeaderEntity;

@Component
public class SupplierOrderDetailEntityMapper implements Function<SupplierOrderDetailDto, SupplierOrderDetailEntity>{

	@Override
	public SupplierOrderDetailEntity apply(SupplierOrderDetailDto supplierOrderDetailDto) {
		return new SupplierOrderDetailEntity(new SupplierOrderHeaderEntity(supplierOrderDetailDto.getSupplierOrderHeaderId()),
				new ArticleEntity(supplierOrderDetailDto.getArticleId()),
				supplierOrderDetailDto.getQuantity());
	}

}

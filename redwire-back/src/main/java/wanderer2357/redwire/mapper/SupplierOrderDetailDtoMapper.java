package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.SupplierOrderDetailDto;
import wanderer2357.redwire.model.SupplierOrderDetailEntity;

@Component
public class SupplierOrderDetailDtoMapper implements Function<SupplierOrderDetailEntity, SupplierOrderDetailDto>{

	private ImmutableArticleDtoMapper immutableArticleDtoMapper;
	
	public SupplierOrderDetailDtoMapper(ImmutableArticleDtoMapper immutableArticleDtoMapper) {
		this.immutableArticleDtoMapper = immutableArticleDtoMapper;
	}
	
	@Override
	public SupplierOrderDetailDto apply(SupplierOrderDetailEntity supplierOrderDetailEntity) {
		return new SupplierOrderDetailDto(supplierOrderDetailEntity.getId(),
				supplierOrderDetailEntity.getSupplierOrderHeaderEntity().getId(),
				supplierOrderDetailEntity.getArticleEntity().getId(),
				immutableArticleDtoMapper.apply(supplierOrderDetailEntity.getImmutableArticle()),
				supplierOrderDetailEntity.getQuantity());
	}

}

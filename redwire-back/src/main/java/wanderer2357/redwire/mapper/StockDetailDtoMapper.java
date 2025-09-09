package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.StockDetailDto;
import wanderer2357.redwire.model.StockDetailEntity;

@Component
public class StockDetailDtoMapper implements Function<StockDetailEntity, StockDetailDto>{

	private ArticleDtoMapper articleDtoMapper;
	
	public StockDetailDtoMapper(ArticleDtoMapper articleDtoMapper) {
		this.articleDtoMapper = articleDtoMapper;
	}
	
	@Override
	public StockDetailDto apply(StockDetailEntity stockDetailEntity) {
		return new StockDetailDto(stockDetailEntity.getId(),
				articleDtoMapper.apply(stockDetailEntity.getArticleEntity()),
				stockDetailEntity.getLocation(),
				stockDetailEntity.getQuantity(),
				stockDetailEntity.getCreatedAt(),
				stockDetailEntity.getUpdatedAt());
	}

}

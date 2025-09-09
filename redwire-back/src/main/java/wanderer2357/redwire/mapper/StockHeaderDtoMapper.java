package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.StockHeaderDto;
import wanderer2357.redwire.model.StockHeaderEntity;

@Component
public class StockHeaderDtoMapper implements Function<StockHeaderEntity, StockHeaderDto>{

	@Override
	public StockHeaderDto apply(StockHeaderEntity stockHeaderEntity) {
		return new StockHeaderDto(stockHeaderEntity.getId(),
				stockHeaderEntity.getArticleEntity().getId(),
				stockHeaderEntity.getMin(),
				stockHeaderEntity.getMax(),
				stockHeaderEntity.getCreatedAt(),
				stockHeaderEntity.getUpdatedAt());
	}

}

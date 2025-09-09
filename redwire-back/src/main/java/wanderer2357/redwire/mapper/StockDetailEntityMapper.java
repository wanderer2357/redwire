package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.StockDetailDto;
import wanderer2357.redwire.model.ArticleEntity;
import wanderer2357.redwire.model.StockDetailEntity;

@Component
public class StockDetailEntityMapper implements Function<StockDetailDto, StockDetailEntity>{

	@Override
	public StockDetailEntity apply(StockDetailDto stockDetailDto) {
		return new StockDetailEntity(new ArticleEntity(stockDetailDto.getArticleDto().getId()),
				stockDetailDto.getLocation().trim(),
				stockDetailDto.getQuantity());
	}

}

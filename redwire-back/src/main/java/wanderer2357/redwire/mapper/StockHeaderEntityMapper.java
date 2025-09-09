package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.StockHeaderDto;
import wanderer2357.redwire.model.ArticleEntity;
import wanderer2357.redwire.model.StockHeaderEntity;

@Component
public class StockHeaderEntityMapper implements Function<StockHeaderDto, StockHeaderEntity>{

	@Override
	public StockHeaderEntity apply(StockHeaderDto stockHeaderDto) {
		return new StockHeaderEntity(new ArticleEntity(stockHeaderDto.getArticleId()),
				stockHeaderDto.getMin(),
				stockHeaderDto.getMax());
	}

}

package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.ArticleDto;
import wanderer2357.redwire.model.ArticleEntity;

@Component
public class ArticleEntityMapper implements Function<ArticleDto, ArticleEntity>{

	@Override
	public ArticleEntity apply(ArticleDto articleDto) {
		return new ArticleEntity(articleDto.getCode().trim(),
				articleDto.getLabel1().trim(),
				articleDto.getLabel2().trim(),
				articleDto.getPrice(),
				articleDto.getCostPrice());
	}

}

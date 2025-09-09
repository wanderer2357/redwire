package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.ArticleDto;
import wanderer2357.redwire.model.ArticleEntity;

@Component
public class ArticleDtoMapper implements Function<ArticleEntity, ArticleDto>{

	@Override
	public ArticleDto apply(ArticleEntity articleEntity) {
		return new ArticleDto(articleEntity.getId(),
				articleEntity.getCode(),
				articleEntity.getLabel1(),
				articleEntity.getLabel2(),
				articleEntity.getPrice(),
				articleEntity.getCostPrice(),
				articleEntity.getCreatedAt(),
				articleEntity.getUpdatedAt());
	}

}

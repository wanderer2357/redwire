package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.ImmutableArticleDto;
import wanderer2357.redwire.model.embeddable.ImmutableArticle;

@Component
public class ImmutableArticleDtoMapper implements Function<ImmutableArticle, ImmutableArticleDto>{

	@Override
	public ImmutableArticleDto apply(ImmutableArticle immutableArticle) {
		return new ImmutableArticleDto(immutableArticle.getCode(), 
				immutableArticle.getLabel1(), 
				immutableArticle.getLabel2(), 
				immutableArticle.getPrice(), 
				immutableArticle.getCostPrice());
	}

}

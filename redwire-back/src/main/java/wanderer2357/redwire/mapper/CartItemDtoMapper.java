package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.CartItemDto;
import wanderer2357.redwire.model.CartItemEntity;

@Component
public class CartItemDtoMapper implements Function<CartItemEntity, CartItemDto>{

	private ArticleDtoMapper articleDtoMapper;
	
	public CartItemDtoMapper(ArticleDtoMapper articleDtoMapper) {
		this.articleDtoMapper = articleDtoMapper;
	}
	
	@Override
	public CartItemDto apply(CartItemEntity cartItemEntity) {
		return new CartItemDto(cartItemEntity.getId().getClientId(),
				articleDtoMapper.apply(cartItemEntity.getArticleEntity()),
				cartItemEntity.getQuantity());
	}

}

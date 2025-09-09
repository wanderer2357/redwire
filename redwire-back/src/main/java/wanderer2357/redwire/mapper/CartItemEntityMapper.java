package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.CartItemDto;
import wanderer2357.redwire.model.ArticleEntity;
import wanderer2357.redwire.model.CartItemEntity;
import wanderer2357.redwire.model.ClientEntity;

@Component
public class CartItemEntityMapper implements Function<CartItemDto, CartItemEntity>{

	@Override
	public CartItemEntity apply(CartItemDto cartItemDto) {
		return new CartItemEntity(new ClientEntity(cartItemDto.getClientId()),
				new ArticleEntity(cartItemDto.getArticleDto().getId()),
				cartItemDto.getQuantity());
	}

}

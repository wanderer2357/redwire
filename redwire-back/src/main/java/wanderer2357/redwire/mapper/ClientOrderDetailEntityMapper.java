package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.ClientOrderDetailDto;
import wanderer2357.redwire.model.ArticleEntity;
import wanderer2357.redwire.model.ClientOrderDetailEntity;
import wanderer2357.redwire.model.ClientOrderHeaderEntity;

@Component
public class ClientOrderDetailEntityMapper implements Function<ClientOrderDetailDto, ClientOrderDetailEntity>{

	@Override
	public ClientOrderDetailEntity apply(ClientOrderDetailDto clientOrderDetailDto) {
		return new ClientOrderDetailEntity(new ClientOrderHeaderEntity(clientOrderDetailDto.getClientOrderHeaderId()),
				new ArticleEntity(clientOrderDetailDto.getArticleId()),
				clientOrderDetailDto.getQuantity());
	}

}

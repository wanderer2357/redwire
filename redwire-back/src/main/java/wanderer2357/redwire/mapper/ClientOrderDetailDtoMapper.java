package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.ClientOrderDetailDto;
import wanderer2357.redwire.model.ClientOrderDetailEntity;

@Component
public class ClientOrderDetailDtoMapper implements Function<ClientOrderDetailEntity, ClientOrderDetailDto>{
	
	private ImmutableArticleDtoMapper immutableArticleDtoMapper;
	
	public ClientOrderDetailDtoMapper(ImmutableArticleDtoMapper immutableArticleDtoMapper) {
		this.immutableArticleDtoMapper = immutableArticleDtoMapper;
	}
	
	@Override
	public ClientOrderDetailDto apply(ClientOrderDetailEntity clientOrderDetailEntity) {
		return new ClientOrderDetailDto(clientOrderDetailEntity.getId(),
				clientOrderDetailEntity.getClientOrderHeaderEntity().getId(),
				clientOrderDetailEntity.getArticleEntity().getId(),
				immutableArticleDtoMapper.apply(clientOrderDetailEntity.getImmutableArticle()),
				clientOrderDetailEntity.getQuantity());
	}

}

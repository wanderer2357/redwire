package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.ClientOrderHeaderDto;
import wanderer2357.redwire.model.ClientOrderHeaderEntity;

@Component
public class ClientOrderHeaderDtoMapper implements Function<ClientOrderHeaderEntity, ClientOrderHeaderDto>{
	
	private ImmutableClientDtoMapper immutableClientDtoMapper;
	private ImmutableAddressDtoMapper immutableAddressDtoMapper;
	
	public ClientOrderHeaderDtoMapper(ImmutableClientDtoMapper immutableClientDtoMapper, 
			ImmutableAddressDtoMapper immutableAddressDtoMapper) {
		this.immutableClientDtoMapper = immutableClientDtoMapper;
		this.immutableAddressDtoMapper = immutableAddressDtoMapper;
	}

	@Override
	public ClientOrderHeaderDto apply(ClientOrderHeaderEntity clientOrderHeaderEntity) {
		return new ClientOrderHeaderDto(clientOrderHeaderEntity.getId(),
				clientOrderHeaderEntity.getClientEntity().getId(),
				immutableClientDtoMapper.apply(clientOrderHeaderEntity.getImmutableClient()),
				clientOrderHeaderEntity.getAddressEntity().getId(),
				immutableAddressDtoMapper.apply(clientOrderHeaderEntity.getImmutableAddress()),
				clientOrderHeaderEntity.getOrderStatus(),
				clientOrderHeaderEntity.getCreatedAt(),
				clientOrderHeaderEntity.getUpdatedAt());
	}

}

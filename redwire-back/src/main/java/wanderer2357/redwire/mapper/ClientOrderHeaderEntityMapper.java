package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.ClientOrderHeaderDto;
import wanderer2357.redwire.model.AddressEntity;
import wanderer2357.redwire.model.ClientEntity;
import wanderer2357.redwire.model.ClientOrderHeaderEntity;

@Component
public class ClientOrderHeaderEntityMapper implements Function<ClientOrderHeaderDto, ClientOrderHeaderEntity>{

	@Override
	public ClientOrderHeaderEntity apply(ClientOrderHeaderDto clientOrderHeaderDto) {
		return new ClientOrderHeaderEntity(new ClientEntity(clientOrderHeaderDto.getClientId()),
				new AddressEntity(clientOrderHeaderDto.getAddressId()),
				clientOrderHeaderDto.getOrderStatus());
	}

}

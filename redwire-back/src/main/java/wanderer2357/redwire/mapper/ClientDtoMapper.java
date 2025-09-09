package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.ClientDto;
import wanderer2357.redwire.model.ClientEntity;

@Component
public class ClientDtoMapper implements Function<ClientEntity, ClientDto>{

	@Override
	public ClientDto apply(ClientEntity clientEntity) {
		return new ClientDto(clientEntity.getId(),
				clientEntity.getFirstname(),
				clientEntity.getLastname(),
				clientEntity.getEmail(),
				clientEntity.getPhone(),
				null,
				clientEntity.getStatus(),
				clientEntity.getCreatedAt(),
				clientEntity.getUpdatedAt());
	}

}

package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.ImmutableClientDto;
import wanderer2357.redwire.model.embeddable.ImmutableClient;

@Component
public class ImmutableClientDtoMapper implements Function<ImmutableClient, ImmutableClientDto>{

	@Override
	public ImmutableClientDto apply(ImmutableClient immutableClient) {
		return new ImmutableClientDto(immutableClient.getFirstname(),
				immutableClient.getLastname(), 
				immutableClient.getEmail(),
				immutableClient.getPhone());
	}

}

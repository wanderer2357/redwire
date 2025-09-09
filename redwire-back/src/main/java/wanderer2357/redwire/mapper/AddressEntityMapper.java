package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.AddressDto;
import wanderer2357.redwire.model.AddressEntity;
import wanderer2357.redwire.model.ClientEntity;

@Component
public class AddressEntityMapper implements Function<AddressDto, AddressEntity>{

	@Override
	public AddressEntity apply(AddressDto addressDto) {
		return new AddressEntity(new ClientEntity(addressDto.getId()),
				addressDto.getStreet1().trim(),
				addressDto.getStreet2().trim(),
				addressDto.getPostalCode().trim(),
				addressDto.getRegionStateProvince().trim(),
				addressDto.getCountry().trim());
	}

}

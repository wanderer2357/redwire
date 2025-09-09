package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.AddressDto;
import wanderer2357.redwire.model.AddressEntity;

@Component
public class AddressDtoMapper implements Function<AddressEntity, AddressDto>{

	@Override
	public AddressDto apply(AddressEntity addressEntity) {
		return new AddressDto(addressEntity.getId(),
				addressEntity.getClientEntity().getId(),
				addressEntity.getStreet1(),
				addressEntity.getStreet2(),
				addressEntity.getPostalCode(),
				addressEntity.getRegionStateProvince(),
				addressEntity.getCountry(),
				addressEntity.getCreatedAt(),
				addressEntity.getUpdatedAt());
	}

}

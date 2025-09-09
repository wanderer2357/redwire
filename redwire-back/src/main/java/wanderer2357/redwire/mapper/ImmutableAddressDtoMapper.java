package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.ImmutableAddressDto;
import wanderer2357.redwire.model.embeddable.ImmutableAddress;

@Component
public class ImmutableAddressDtoMapper implements Function<ImmutableAddress, ImmutableAddressDto> {

	@Override
	public ImmutableAddressDto apply(ImmutableAddress immutableAddress) {
		return new ImmutableAddressDto(immutableAddress.getStreet1(), 
				immutableAddress.getStreet2(), 
				immutableAddress.getPostalCode(), 
				immutableAddress.getRegionStateProvince(), 
				immutableAddress.getCountry());
	}

}

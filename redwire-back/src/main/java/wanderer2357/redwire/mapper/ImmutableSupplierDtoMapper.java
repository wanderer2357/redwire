package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.ImmutableSupplierDto;
import wanderer2357.redwire.model.embeddable.ImmutableSupplier;

@Component
public class ImmutableSupplierDtoMapper implements Function<ImmutableSupplier, ImmutableSupplierDto>{

	@Override
	public ImmutableSupplierDto apply(ImmutableSupplier immutableSupplier) {
		return new ImmutableSupplierDto(immutableSupplier.getCompanyName(),
				immutableSupplier.getStreet1(),
				immutableSupplier.getStreet2(),
				immutableSupplier.getPostalCode(),
				immutableSupplier.getRegionStateProvince(),
				immutableSupplier.getCountry(),
				immutableSupplier.getEmail(),
				immutableSupplier.getPhone(),
				immutableSupplier.getRepresentativeFirstname(),
				immutableSupplier.getRepresentativeLastname(),
				immutableSupplier.getRepresentativeEmail(),
				immutableSupplier.getRepresentativePhone());
	}

}

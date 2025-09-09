package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.AdministratorDto;
import wanderer2357.redwire.model.AdministratorEntity;

@Component
public class AdministratorDtoMapper implements Function<AdministratorEntity, AdministratorDto>{
	
	@Override
	public AdministratorDto apply(AdministratorEntity administratorEntity) {
		
		return new AdministratorDto(administratorEntity.getId(),
				administratorEntity.getFirstname(),
				administratorEntity.getLastname(),
				administratorEntity.getEmail(), 
				administratorEntity.getPhone(),
				null,
				administratorEntity.isSuperAdmin(),
				administratorEntity.getStatus(),
				administratorEntity.getCreatedAt(),
				administratorEntity.getUpdatedAt());
	}

}

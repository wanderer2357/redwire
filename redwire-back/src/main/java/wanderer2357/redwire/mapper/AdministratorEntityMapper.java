package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.AdministratorDto;
import wanderer2357.redwire.model.AdministratorEntity;
import wanderer2357.redwire.util.PasswordUtil;

@Component
public class AdministratorEntityMapper implements Function<AdministratorDto, AdministratorEntity>{

	@Autowired
	private PasswordUtil passwordUtil;

	@Override
	public AdministratorEntity apply(AdministratorDto AdministratorDto) {
		return new AdministratorEntity(AdministratorDto.getFirstname().trim(),
				AdministratorDto.getLastname().trim(),
				AdministratorDto.getEmail().trim(),
				AdministratorDto.getPhone().trim(),
				passwordUtil.hashPassword(AdministratorDto.getClearPassword()));
	}

}

package wanderer2357.redwire.mapper;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wanderer2357.redwire.dto.ClientDto;
import wanderer2357.redwire.model.ClientEntity;
import wanderer2357.redwire.util.PasswordUtil;

@Component
public class ClientEntityMapper implements Function<ClientDto, ClientEntity>{
	
	@Autowired
	private PasswordUtil passwordUtil;
	
	@Override
	public ClientEntity apply(ClientDto clientDto) {
		return new ClientEntity(clientDto.getFirstname().trim(),
				clientDto.getLastname().trim(),
				clientDto.getEmail().trim(),
				clientDto.getPhone().trim(),
				passwordUtil.hashPassword(clientDto.getClearPassword()));
	}

}

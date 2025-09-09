package wanderer2357.redwire.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import wanderer2357.redwire.dto.ClientDto;
import wanderer2357.redwire.exception.ResourceNotFoundException;
import wanderer2357.redwire.mapper.ClientDtoMapper;
import wanderer2357.redwire.mapper.ClientEntityMapper;
import wanderer2357.redwire.repository.ClientRepository;

@Service
public class ClientService {
	
	private final ClientRepository clientRepository;
	private final ClientDtoMapper clientDtoMapper;
	private final ClientEntityMapper clientEntityMapper;
	
	public ClientService(ClientRepository clientRepository,
			ClientDtoMapper clientDtoMapper,
			ClientEntityMapper clientEntityMapper) {
		this.clientRepository = clientRepository;
		this.clientDtoMapper = clientDtoMapper;
		this.clientEntityMapper = clientEntityMapper;
	}
		
	public Page<ClientDto> getAllClients(Pageable pageable) {
	    return clientRepository.findAll(pageable)
	            .map(clientDtoMapper);
	}
	
	public ClientDto getClientById(Long id) {
        return clientRepository.findById(id)
        		.map(clientDtoMapper)
        		.orElseThrow(() -> new ResourceNotFoundException("Could not find requested client"));
    }
	
	public void saveClient(ClientDto clientDto) {
		clientRepository.save(clientEntityMapper.apply(clientDto));
	}

    public List<ClientDto> getClientsByEmail(String email) {
        return clientRepository.findByEmail(email)
        		.stream()
				.map(clientDtoMapper)
				.toList();
    }

    public List<ClientDto> getClientsByPhone(String phone) {
        return clientRepository.findByPhone(phone)
        		.stream()
				.map(clientDtoMapper)
				.toList();
    }

}

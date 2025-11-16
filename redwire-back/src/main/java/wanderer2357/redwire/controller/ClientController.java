package wanderer2357.redwire.controller;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import wanderer2357.redwire.dto.ClientDto;
import wanderer2357.redwire.payload.RedwireResponsePayload;
import wanderer2357.redwire.service.ClientService;

@RestController
@Validated
@RequestMapping("/clients")
public class ClientController implements RedwireApiBaseController {
	
	private final ClientService clientService;
	
	public ClientController (ClientService clientService) {
		this.clientService = clientService;
	}
	
	@PostMapping
	public ResponseEntity<RedwireResponsePayload<ClientDto>> 
	saveClient(@RequestBody @Valid ClientDto clientDto) {
		
		ClientDto savedClientDto = clientService.saveClient(clientDto);
		
		RedwireResponsePayload<ClientDto> payload = new RedwireResponsePayload<ClientDto>(
				HttpStatus.CREATED,
				"Saved client",
				savedClientDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(payload);
	}
	
	@GetMapping
    public ResponseEntity<RedwireResponsePayload<Page<ClientDto>>> 
	getClients(@RequestBody @PageableDefault(size = 10, sort = "lastName") Pageable pageable) {
		
        Page<ClientDto> clientDtoPage = clientService.getClientPage(pageable);
        
        RedwireResponsePayload<Page<ClientDto>> payload = new RedwireResponsePayload<Page<ClientDto>>(
                HttpStatus.OK,
                "Fetched client page",
                clientDtoPage
        );
        
        return ResponseEntity.ok(payload);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RedwireResponsePayload<ClientDto>> getClientById(
            @PathVariable @NotNull @Positive Long id) {

        ClientDto clientDto = clientService.getClientById(id);
        
        RedwireResponsePayload<ClientDto> payload = new RedwireResponsePayload<ClientDto>(
                HttpStatus.OK,
                "Retrieved client with ID " + id,
                clientDto
        );
        
        return ResponseEntity.ok(payload);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<RedwireResponsePayload<ClientDto>> patchClientById(
            @PathVariable @NotNull @Min(1) Long id,
            @RequestBody @NotNull Map<String, Object> updates) {
    	
    	ClientDto clientDto = clientService.patchClient(id, updates);
        
        RedwireResponsePayload<ClientDto> payload = new RedwireResponsePayload<ClientDto>(
                HttpStatus.OK,
                "Patched client with ID " + id,
                clientDto
        );
        
        return ResponseEntity.ok(payload);
    }

}

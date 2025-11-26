package wanderer2357.redwire.controller;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import org.springframework.http.HttpStatus;

import wanderer2357.redwire.dto.SupplierDto;
import wanderer2357.redwire.payload.RedwireResponsePayload;
import wanderer2357.redwire.service.SupplierService;

@RestController
@Validated
@RequestMapping("suppliers")
public class SupplierController implements RedwireApiBaseController{

	private final SupplierService supplierService;
	
	SupplierController(SupplierService supplierService) {
		this.supplierService = supplierService;
	}
	
	@GetMapping
	public ResponseEntity<RedwireResponsePayload<Page<SupplierDto>>> 
	getSuppliers(@PageableDefault(size=10, sort="companyName") Pageable pageable) {
		Page<SupplierDto> supplierDtoPage = supplierService.getSupplierPage(pageable);
		
		RedwireResponsePayload<Page<SupplierDto>> payload = new RedwireResponsePayload<Page<SupplierDto>>
		(HttpStatus.OK,
			"Fetched supplier page",
			supplierDtoPage);
		
		return ResponseEntity.ok(payload);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RedwireResponsePayload<SupplierDto>>
	getSupplierById(@PathVariable @NotNull @Positive Long id) {
		
		SupplierDto supplierDto = supplierService.getSupplierById(id);
		
		RedwireResponsePayload<SupplierDto> payload = new RedwireResponsePayload<SupplierDto>
		(HttpStatus.OK,
		"Fetched supplier using ID",
		supplierDto);
		
		return ResponseEntity.ok(payload);
	}
	
	@GetMapping("/{email}/email")
	public ResponseEntity<RedwireResponsePayload<SupplierDto>>
	getSupplierByEmail(@PathVariable @NotBlank String email) {
		
		SupplierDto supplierDto = supplierService.getSupplierByEmail(email);
		
		RedwireResponsePayload<SupplierDto> payload = new RedwireResponsePayload<SupplierDto>
		(HttpStatus.OK,
		"Fetched supplier using email",
		supplierDto);
		
		return ResponseEntity.ok(payload);
	}
	
	@GetMapping("/{phone}/phone")
	public ResponseEntity<RedwireResponsePayload<SupplierDto>>
	getSupplierByPhone(@PathVariable @NotBlank String phone) {
		
		SupplierDto supplierDto = supplierService.getSupplierByPhone(phone);
		
		RedwireResponsePayload<SupplierDto> payload = new RedwireResponsePayload<SupplierDto>
		(HttpStatus.OK,
		"Fetched supplier using phone",
		supplierDto);
		
		return ResponseEntity.ok(payload);
	}
	
	@PostMapping
	public ResponseEntity<RedwireResponsePayload<SupplierDto>> 
	saveSupplier(@RequestBody SupplierDto supplierDto) {
		
		SupplierDto savedSupplierDto = supplierService.saveSupplier(supplierDto);
		
		RedwireResponsePayload<SupplierDto> payload = new RedwireResponsePayload<SupplierDto>
		(HttpStatus.CREATED,
				"Saved supplier",
				savedSupplierDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(payload);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<RedwireResponsePayload<SupplierDto>>
	patchSupplier(@PathVariable @Positive Long id,
			@RequestBody @NotNull Map<String, Object> updates) {
		
		SupplierDto supplierDto = supplierService.patchSupplier(id, updates);
		
		RedwireResponsePayload<SupplierDto> payload = new RedwireResponsePayload<SupplierDto>(
                HttpStatus.OK,
                "Patched supplier with ID " + id,
                supplierDto
        );
		
		return ResponseEntity.ok(payload);
	}
}

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
	
	@PostMapping
	public ResponseEntity<RedwireResponsePayload<SupplierDto>> 
	saveSupplier(@RequestBody SupplierDto supplierDto) {
		
		SupplierDto savedSupplierDto = new SupplierDto();
		
		RedwireResponsePayload<SupplierDto> payload = new RedwireResponsePayload<SupplierDto>
		(HttpStatus.CREATED,
				"Saved supplier",
				savedSupplierDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(payload);
	}
	
	@GetMapping
	public ResponseEntity<RedwireResponsePayload<Page<SupplierDto>>> 
	getSuppliers(@RequestBody @PageableDefault(size=10, sort="companyName") Pageable pageable) {
		Page<SupplierDto> supplierDtoPage = null;
		
		RedwireResponsePayload<Page<SupplierDto>> payload = new RedwireResponsePayload<Page<SupplierDto>>
		(HttpStatus.OK,
			"Fetched supplier page",
			supplierDtoPage);
		
		return ResponseEntity.ok(payload);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RedwireResponsePayload<SupplierDto>>
	getSupplierById(@PathVariable Long id) {
		
		SupplierDto supplierDto = null;
		
		RedwireResponsePayload<SupplierDto> payload = new RedwireResponsePayload<SupplierDto>
		(HttpStatus.OK,
		"Fetched supplier",
		supplierDto);
		
		return ResponseEntity.ok(payload);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<RedwireResponsePayload<SupplierDto>>
	patchSupplier(@PathVariable Long id,
			@RequestBody Map<String, Object> updates) {
		
		SupplierDto supplierDto = null;
		
		RedwireResponsePayload<SupplierDto> payload = new RedwireResponsePayload<SupplierDto>(
                HttpStatus.OK,
                "Patched supplier with ID " + id,
                supplierDto
        );
		
		return null;
	}
}

package wanderer2357.redwire.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import wanderer2357.redwire.payload.RedwireResponsePayload;
import wanderer2357.redwire.service.ClientService;

@ControllerAdvice
public class RedwireExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(RedwireExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<RedwireResponsePayload<Object>> handleGenericException(Exception ex) {
	    RedwireResponsePayload<Object> payload = new RedwireResponsePayload<>(
	            HttpStatus.INTERNAL_SERVER_ERROR,
	            "UNEXPECTED EXCEPTION",
	            null
	    );
	    log.error("UNEXPECTED EXCEPTION", ex);
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(payload);
	}

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RedwireResponsePayload<Object>> handleRuntimeException(RuntimeException ex) {
        RedwireResponsePayload<Object> payload = new RedwireResponsePayload<>(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                null
        );
        log.error("RUNTIME EXCEPTION", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(payload);
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<RedwireResponsePayload<Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        RedwireResponsePayload<Object> payload = new RedwireResponsePayload<>(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                null
        );
        log.error("RESOURCE NOT FOUND EXCEPTION", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(payload);
    }
    
    @ExceptionHandler(InvalidPostRequestException.class)
    public ResponseEntity<RedwireResponsePayload<Object>> handlePostRequestException(InvalidPostRequestException ex) {
        RedwireResponsePayload<Object> payload = new RedwireResponsePayload<>(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                null
        );
        log.error("POST REQUEST EXCEPTION", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(payload);
    }
    
    @ExceptionHandler(InvalidPatchRequestException.class)
    public ResponseEntity<RedwireResponsePayload<Object>> handlePatchRequestException(InvalidPatchRequestException ex) {
        RedwireResponsePayload<Object> payload = new RedwireResponsePayload<>(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                null
        );
        log.error("PATCH REQUEST EXCEPTION", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(payload);
    }
}

package wanderer2357.redwire.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import wanderer2357.redwire.payload.RedwireResponsePayload;

@ControllerAdvice
public class RedwireExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RedwireResponsePayload<Object>> handleRuntimeException(RuntimeException ex) {
        RedwireResponsePayload<Object> payload = new RedwireResponsePayload<>(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(payload);
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<RedwireResponsePayload<Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        RedwireResponsePayload<Object> payload = new RedwireResponsePayload<>(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(payload);
    }
}

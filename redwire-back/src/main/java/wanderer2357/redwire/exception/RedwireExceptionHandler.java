package wanderer2357.redwire.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import wanderer2357.redwire.payload.RedwireResponsePayload;

@ControllerAdvice
public class RedwireExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(RedwireExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<RedwireResponsePayload<Object>> handleGenericException(Exception ex) {
	    return handleResponse(ex, "UNEXPECTED EXCEPTION", HttpStatus.INTERNAL_SERVER_ERROR);
	}

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RedwireResponsePayload<Object>> handleRuntimeException(RuntimeException ex) {
        return handleResponse(ex, "RUNTIME EXCEPTION", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<RedwireResponsePayload<Object>> handleClientNotFoundException(ClientNotFoundException ex) {
    	return handleResponse(ex, "CLIENT RESOURCE NOT FOUND EXCEPTION", HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(InvalidSaveClientRequestException.class)
    public ResponseEntity<RedwireResponsePayload<Object>> handleInvalidSaveClientRequestException(InvalidSaveClientRequestException ex) {
    	return handleResponse(ex, "SAVE CLIENT REQUEST EXCEPTION", HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(InvalidPatchClientRequestException.class)
    public ResponseEntity<RedwireResponsePayload<Object>> handleInvalidPatchClientRequestException(InvalidPatchClientRequestException ex) {
    	return handleResponse(ex, "PATCH CLIENT REQUEST EXCEPTION", HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(InvalidClientPhoneException.class)
    public ResponseEntity<RedwireResponsePayload<Object>> handleInvalidClientPhoneException(InvalidClientPhoneException ex) {
        return handleResponse(ex, "INVALID CLIENT PHONE EXCEPTION", HttpStatus.BAD_REQUEST);

    }
    
    @ExceptionHandler(SupplierNotFoundException.class)
    public ResponseEntity<RedwireResponsePayload<Object>> handleSupplierNotFoundException(SupplierNotFoundException ex) {
        return handleResponse(ex, "SUPPLIER RESOURCE NOT FOUND EXCEPTION", HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(InvalidSaveSupplierRequestException.class)
    public ResponseEntity<RedwireResponsePayload<Object>> handleInvalidSaveSupplierRequestException(InvalidSaveSupplierRequestException ex) {
        return handleResponse(ex, "SAVE SUPPLIER REQUEST EXCEPTION", HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(InvalidPatchSupplierRequestException.class)
    public ResponseEntity<RedwireResponsePayload<Object>> handleInvalidPatchSupplierRequestException(InvalidPatchSupplierRequestException ex) {
        return handleResponse(ex, "PATCH SUPPLIER REQUEST EXCEPTION", HttpStatus.BAD_REQUEST);

    }
    
    @ExceptionHandler(InvalidSupplierPhoneException.class)
    public ResponseEntity<RedwireResponsePayload<Object>> handleInvalidSupplierPhoneException(InvalidSupplierPhoneException ex) {
        return handleResponse(ex, "INVALID SUPPLIER PHONE EXCEPTION", HttpStatus.BAD_REQUEST);
        
    }
    
    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<RedwireResponsePayload<Object>> handleArticleNotFoundException(ArticleNotFoundException ex) {
        return handleResponse(ex, "ARTICLE RESOURCE NOT FOUND EXCEPTION", HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(InvalidSaveArticleRequestException.class)
    public ResponseEntity<RedwireResponsePayload<Object>> handleInvalidSaveArticleRequestException(InvalidSaveArticleRequestException ex) {
        return handleResponse(ex, "SAVE ARTICLE REQUEST EXCEPTION", HttpStatus.BAD_REQUEST);
    }
    
    private ResponseEntity<RedwireResponsePayload<Object>>
    handleResponse(Exception ex, String errorLog, HttpStatus httpStatus) {
    	RedwireResponsePayload<Object> payload = new RedwireResponsePayload<>(
    			httpStatus,
                ex.getMessage(),
                null
        );
        log.error(errorLog, ex);
        return ResponseEntity.status(httpStatus).body(payload);
    }
}

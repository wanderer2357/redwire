package wanderer2357.redwire.exception;

public class InvalidPostRequestException extends RuntimeException{
	
private static final long serialVersionUID = 1L;
	
	public InvalidPostRequestException(String message) {
        super(message);
    }
}
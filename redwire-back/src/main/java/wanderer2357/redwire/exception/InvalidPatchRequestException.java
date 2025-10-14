package wanderer2357.redwire.exception;

public class InvalidPatchRequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidPatchRequestException(String message) {
        super(message);
    }

}

package wanderer2357.redwire.exception;

public class InvalidSaveRequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidSaveRequestException(String message) {
		super(message);
	}

}

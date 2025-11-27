package wanderer2357.redwire.exception;

public class InvalidSaveArticleRequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidSaveArticleRequestException(String message) {
		super(message);
	}

}

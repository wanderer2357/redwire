package wanderer2357.redwire.exception;

public class SupplierNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public SupplierNotFoundException(String message) {
		super(message);
	}
}

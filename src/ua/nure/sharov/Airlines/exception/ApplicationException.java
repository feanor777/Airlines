package ua.nure.sharov.Airlines.exception;

public class ApplicationException extends Exception {
	private static final long serialVersionUID = 1146283025966987216L;
	
	public ApplicationException() {
		super();
	}
	
	public ApplicationException(String message) {
		super(message);
	}
	
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

}

package Exceptions;

public class CustomerFacadeException extends FacadeException{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerFacadeException() {
		super();
	}

	public CustomerFacadeException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerFacadeException(String message) {
		super(message);
	}

	public CustomerFacadeException(Throwable cause) {
		super(cause);
	}

}




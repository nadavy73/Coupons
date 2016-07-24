package Exceptions;

public class AdminFacadeException extends Exception {

	private static final long serialVersionUID = 1L;

	public AdminFacadeException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AdminFacadeException(String message, Throwable cause) {
		super(message, cause);
	
	}
	
	public AdminFacadeException (String massage){
		super (massage);
	}
	
		
	public AdminFacadeException (Throwable cause){
		super (cause);
	}

}

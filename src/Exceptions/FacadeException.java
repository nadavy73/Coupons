package Exceptions;


public class FacadeException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FacadeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FacadeException (String massage){
		super (massage);
	}
	
	public FacadeException (String massage, Throwable cause){
		super (massage,cause);
		
	}
	
	public FacadeException (Throwable cause){
		super (cause);
	}
	
	
}


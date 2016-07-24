package Exceptions;

public class DoesNotExistException extends Exception {

	private static final long serialVersionUID = 1L;

	
				
				public DoesNotExistException() {
				super();
					// TODO Auto-generated constructor stub
				}

				public DoesNotExistException (String massage){
					super (massage);
				}
				
				public DoesNotExistException (String massage, Throwable cause){
					super (massage,cause);
					
				}
				
				public DoesNotExistException (Throwable cause){
					super (cause);
				}

	}



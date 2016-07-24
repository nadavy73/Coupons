package Exceptions;

public class AlreadyExistException extends Exception {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private static final long serialVersionUID = 1L;

	public AlreadyExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlreadyExistException (String massage){
		super (massage);
	}
	
	public AlreadyExistException (String massage, Throwable cause){
		super (massage,cause);
		
	}
	
	public AlreadyExistException (Throwable cause){
		super (cause);
	}
}

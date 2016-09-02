package Exceptions;

@SuppressWarnings("serial")
public class CompanyFacadeException extends FacadeException{
	

	public CompanyFacadeException() {
		super();
	}

	public CompanyFacadeException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompanyFacadeException(String message) {
		super(message);
	}

	public CompanyFacadeException(Throwable cause) {
		super(cause);
	}

}




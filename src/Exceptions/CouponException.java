package Exceptions;

@SuppressWarnings("serial")
public class CouponException extends Exception {
	

	public CouponException() {
		super();
	}

	public CouponException(String message, Throwable cause) {
		super(message, cause);
	}

	public CouponException(String message) {
		super(message);
	}

	public CouponException(Throwable cause) {
		super(cause);
	}

}

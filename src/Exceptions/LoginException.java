package Exceptions;


		public class LoginException extends Exception {

		public static void main(String[] args) {
			// TODO Auto-generated method stub

		}

		private static final long serialVersionUID = 1L;

		public LoginException() {
			super();
			// TODO Auto-generated constructor stub
		}

		public LoginException (String massage){
			super (massage);
		}
		
		public LoginException (String massage, Throwable cause){
			super (massage,cause);
			
		}
		
		public LoginException (Throwable cause){
			super (cause);
		}
	}



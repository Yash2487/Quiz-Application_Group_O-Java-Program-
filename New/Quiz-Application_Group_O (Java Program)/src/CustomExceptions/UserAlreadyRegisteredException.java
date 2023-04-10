package CustomExceptions;

public class UserAlreadyRegisteredException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAlreadyRegisteredException(String exc) {
		super(exc);
}
}

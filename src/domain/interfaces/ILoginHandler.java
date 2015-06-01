package domain.interfaces;

/**
 * The interface with the login use case
 * 
 * @author fmartins
 *
 */
public interface ILoginHandler {

	/**
	 * @param username The username of the user that wants to login
	 * @param password The password associated with the username
	 * @return true when the user successfully logs in 
	 */
	boolean login (String username, String password);
}

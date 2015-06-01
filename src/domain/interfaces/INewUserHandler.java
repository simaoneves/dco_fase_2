package domain.interfaces;

/**
 * The interface with the add user use case
 * 
 * @author fmartins
 *
 */
public interface INewUserHandler {

 	/**
	 * @param type The kind of user
	 * @param username The username of the user to add 
	 * @param password The password of the user to add
	 * @return true if the user was successfully added to the system
     * @requires type.equals("Orientado") || type.equals("Supervisor")
	 */
	boolean registerUser (String type, String username, String password);
}

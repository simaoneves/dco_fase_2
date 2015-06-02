package domain.handlers;

import services.SessionManager;
import domain.catalog.UserCatalog;
import domain.interfaces.ILoginHandler;
import domain.User;

/**
 * The login handler used for building the user interface
 * 
 * @author Joao R. && Simao N.
 *
 */
public class LoginHandler implements ILoginHandler {
	
	/**
	 * users catalog
	 */
	public UserCatalog userCatalog;
	
	/**
	 * constructor
	 * 
	 * @param uc
	 * 			users catalog to be used
	 * @ensures userCatalog = uc
	 */
	public LoginHandler(UserCatalog uc) {
		userCatalog = uc;
	}

	/**
	 * validates a user login
	 * 
	 * @see ILoginHandler#login(String, String)
	 */
	@Override
	public boolean login(String username, String password) {
		
		System.out.println("Login: login(\"" + username + "\", \"" + password + "\")");
		
		User u = userCatalog.getUser(username);
		Boolean result = false;
		
		if (u != null) {
			if (u.goodPwd(password)) {
				SessionManager.getInstance().createSession(username);
				result = true;
			}
		}
		
		return result;
	}

}

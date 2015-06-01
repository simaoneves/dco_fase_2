package domain.handlers;

import services.SessionManager;
import domain.interfaces.ILoginHandler;
import domain.User;
import domain.UserCatalog;

/**
 * The login handler used for building the user interface
 * 
 * @author fmartins
 *
 */
public class LoginHandler implements ILoginHandler {
	
	public UserCatalog userCatalog;
	
	public LoginHandler(UserCatalog uc) {
		userCatalog = uc;
	}

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

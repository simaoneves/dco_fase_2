package domain.handlers;

import domain.Oriented;
import domain.Supervisor;
import domain.User;
import domain.catalog.UserCatalog;
import domain.interfaces.INewUserHandler;

/**
 * A new user handler used for building the user interface
 * 
 * @author fmartins
 *
 */
public class NewUserHandler implements INewUserHandler {
	
	public UserCatalog userCatalog;
	
	public NewUserHandler(UserCatalog uc) {
		this.userCatalog = uc;
	}

	@Override
	public boolean registerUser(String tipo, String username, String password) {
		System.out.println("NewUser: registerUser(\"" + tipo + "\", \"" + username + 
				"\", \"" + password + "\")");
		
		User user = userCatalog.getUser(username);
		if (user == null) {
			User newUser = null;
			if (tipo.equals("Supervisor")) {
				newUser = new Supervisor(username, password);
			}
			else if (tipo.equals("Orientado")) {
				newUser = new Oriented(username, password);
			}
			userCatalog.addUser(newUser);
		}
		return user == null;
	}

}

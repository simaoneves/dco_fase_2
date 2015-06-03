package domain.handlers;

import domain.Oriented;
import domain.Supervisor;
import domain.User;
import domain.catalog.UserCatalog;
import domain.interfaces.INewUserHandler;

/**
 * A new user handler used for building the user interface
 * 
 * @author Joao R. && Simao N. && Miguel V.
 * @author fc45582 && fc45681 && fc39279
 * 
 */
public class NewUserHandler implements INewUserHandler {
	
	/**
	 * users catalog
	 */
	public UserCatalog userCatalog;
	
	/**
	 * Constructor
	 * 
	 * @param uc
	 * 			users catalog to be used
	 * @ensures this.userCatalog = uc
	 */
	public NewUserHandler(UserCatalog uc) {
		this.userCatalog = uc;
	}

	/**
	 * Creates an User, depending on the tipo
	 * 
	 * @see INewUserHandler#registerUser(String, String, String)
	 */
	@Override
	public boolean registerUser(String tipo, String username, String password) {
		//System.out.println("NewUser: registerUser(\"" + tipo + "\", \"" + username + 
		//		"\", \"" + password + "\")");
		
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

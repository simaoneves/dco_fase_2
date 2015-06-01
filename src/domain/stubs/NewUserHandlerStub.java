package domain.stubs;

import domain.interfaces.INewUserHandler;

/**
 * A new user handler used for building the user interface
 * 
 * @author fmartins
 *
 */
public class NewUserHandlerStub implements INewUserHandler {

	@Override
	public boolean registerUser(String tipo, String username, String password) {
		System.out.println("NewUser: registerUser(\"" + tipo + "\", \"" + username + 
				"\", \"" + password + "\")");
		return !username.equals("fail");
	}

}

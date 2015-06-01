package domain.stubs;

import domain.interfaces.ILoginHandler;

/**
 * The login handler used for building the user interface
 * 
 * @author fmartins
 *
 */
public class LoginHandlerStub implements ILoginHandler {

	@Override
	public boolean login(String username, String password) {
		System.out.println("Login: login(\"" + username + "\", \"" + password + "\")");
		return !username.equals("fail");
	}

}

package domain.catalog;


import java.util.HashMap;
import java.util.Map;

import domain.Oriented;
import domain.User;

/**
 * This class represents a users catalog
 * 
 * @author Joao R. && Simao N.
 *
 */
public class UserCatalog {
	
	/**
	 * attributes
	 */
	private Map<String, User> userMap;
	
	/**
	 * constructor
	 * 
	 * @ensures userMap != null
	 */
	public UserCatalog() {
		this.userMap = new HashMap<String, User>();
		
		/// TESTE
		User simao = new Oriented("ss", "ss");
		simao.createCategory("categoria_teste");
		simao.createCategory("outra_categoria");
		userMap.put("ss", simao);
	}
	
	/**
	 * get user by given name
	 * 
	 * @param name
	 * 		user name to be considered
	 * @return
	 * 		User if given user name exists,
	 * 		Null if not
	 */
	public User getUser(String name) {
		return userMap.get(name);
	}
	
	/**
	 * add a new user
	 * 
	 * @param user
	 * 		user to be inserted
	 * @requires user != null
	 * @ensures if( !userMap.contains(user.getName()) )
	 * 				old(userMap.size()) + 1 == userMap.size()
	 */
	public void addUser(User user) {
		userMap.put(user.getName(), user);
	}

}

package domain.catalog;


import java.util.HashMap;
import java.util.Map;

import domain.Oriented;
import domain.User;

public class UserCatalog {
	
	private Map<String, User> userMap;
	
	public UserCatalog() {
		this.userMap = new HashMap<String, User>();
		
		/// TESTE
		User simao = new Oriented("ss", "ss");
		simao.createCategory("categoria_teste");
		simao.createCategory("outra_categoria");
		userMap.put("ss", simao);
	}
	
	public User getUser(String name) {
		return userMap.get(name);
	}
	
	public void addUser(User user) {
		userMap.put(user.getName(), user);
	}

}

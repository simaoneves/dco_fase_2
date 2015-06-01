package domain;

import java.util.Collection;
import java.util.HashMap;

public abstract class User {
	
	private String name;
	private String password;
	private HashMap<String, Category> createdCategories;
	
	public User(String name, String password) {
		this.name = name;
		this.password = password;
		this.createdCategories = new HashMap<String, Category>();
	}
	
	public boolean goodPwd(String password) {
		return this.password.equals(password);
	}

	public String getName() {
		return this.name;
	}

	public String getPassword() {
		return this.password;
	}
	
	public Collection<Category> getCategories() {
		return this.createdCategories.values();
	}
	
	public Category getCategory(String catId) {
		return createdCategories.get(catId);
	}
	
	public boolean createCategory(String catId) {
		Boolean result = false;
		if (!this.createdCategories.containsKey(catId)) {
			this.createdCategories.put(catId, new Category(catId));
			result = true;
		}
		return result;
	}
	
	// NÃO É PRECISO
	public String toString() {
		return "User: [name:" + this.name + " password:" + this.password + "]";
	}

}

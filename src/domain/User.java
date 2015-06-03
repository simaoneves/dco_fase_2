package domain;

import java.util.Collection;
import java.util.HashMap;

/**
 * This class represents a user
 * 
 * @author Joao R. && Simao N. && Miguel V.
 * @author fc45582 && fc45681 && fc39279
 * 
 */
public abstract class User {
	
	/**
	 * attributes
	 */
	private String name;
	private String password;
	private HashMap<String, Category> createdCategories;
	
	
	/**
	 * constructor
	 * 
	 * @param name
	 * 			username
	 * @param password
	 * 			password
	 * @ensures getName().equals(name) && getPassword().equals(password)
	 * 			&& createdCategories != null
	 */
	public User(String name, String password) {
		this.name = name;
		this.password = password;
		this.createdCategories = new HashMap<String, Category>();
	}
	
	/**
	 * matches a received string with user password
	 * 
	 * @param password	
	 * 			string to be compared with user password
	 * 
	 * @return 	 True if match, 
	 * 			 False if not 
	 * @requires this.password != null
	 */
	public boolean goodPwd(String password) {
		return this.password.equals(password);
	}

	/**
	 * get user name
	 * 
	 * @return
	 * 		user name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * get user password
	 * 
	 * @return
	 * 		user password
	 */	
	public String getPwd() {
		return this.password;
	}
	
	/**
	 * get all user categories
	 * 
	 * @return
	 * 		user created categories
	 */
	public Collection<Category> getCategories() {
		return this.createdCategories.values();
	}
	
	/**
	 * get a certain category by name
	 * 
	 * @param catId
	 * 			category name to be considered
	 * @return
	 * 			Category if createCategories.contains(catId)
	 * 			null 	 if !createCategories.contains(catId)
	 */
	public Category getCategory(String catId) {
		
		return createdCategories.get(catId);
	}
	
	/**
	 * create a new category
	 * 
	 * @param catId
	 * 			category name to be created and inserted
	 * @return
	 * 			True if createdCategories.contains(catId),
	 * 			False if not
	 */
	public boolean createCategory(String catId) {
		Boolean result = false;
		if (!this.createdCategories.containsKey(catId)) {
			this.createdCategories.put(catId, new Category(catId));
			//check if inserted
			result = this.createdCategories.containsKey(catId);
		}
		return result;
	}

}

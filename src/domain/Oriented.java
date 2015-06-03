package domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * This class represents an Oriented user
 * 
 * @author Joao R. && Simao N. && Miguel V.
 * @author fc45582 && fc45681 && fc39279
 * 
 */
public class Oriented extends User {

	/**
	 * user supervised categories
	 */
	private Map<String, Category> supervised; 
	
	/**
	 * constructor
	 * 
	 * @see User
	 * @ensures supervised != null
	 * 
	 */
	public Oriented(String name, String password) {
		super(name, password);
		
		this.supervised = new HashMap<>();
	}
	
	/**
	 * get all user categories
	 * 
	 * @see User#getCategories()
	 * @return 
	 * 		user created categories plus supervised
	 * 
	 */
	@Override
	public Collection<Category> getCategories(){
		
		LinkedList<Category> list = new LinkedList<>();
		
		list.addAll(super.getCategories());
		list.addAll(this.supervised.values());
		
		return list;
		
	}

}

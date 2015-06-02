package domain.handlers;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import domain.Category;
import domain.User;
import domain.interfaces.IObtainCategoriesHandler;

/**
 * An obtain categories handler user for building the user interface
 * 
 * @author Joao R. && Simao N.
 *
 */
public class ObtainCategoriesHandler implements IObtainCategoriesHandler {

	/**
	 * attributes
	 */
	protected User currentUser;
	protected LinkedList<String> categoryList;
	private Collection<Category> userCategories;
	
	/**
	 * constructor
	 * 
	 * @param authenticatedUser
	 * 			autheticated user
	 * 
	 * @ensures currentUser != null
	 */
	public ObtainCategoriesHandler(User authenticatedUser) {
		this.currentUser = authenticatedUser;
	}
	
	/**
	 * get user categories
	 * 
	 * @see IObtainCategoriesHandler#getCategoriesAuthenticatedUser()
	 */
	@Override
	public Iterable<String> getCategoriesAuthenticatedUser() {
		System.out.println("ObtainCategories: getCategoriesAuthenticatedUser()");
		createCategoriesList();
		return categoryList;
	}
	
	/**
	 * get user created categories
	 * 
	 * @return
	 * 		names of user categories
	 */
	public LinkedList<String> createCategoriesList() {
		this.categoryList = new LinkedList<String>();
		
		userCategories = currentUser.getCategories(); 
		preencheCategNames();
		
		return categoryList;
	}
	
	
	private void preencheCategNames() {
		Iterator<Category> iterator = userCategories.iterator();
		Category c;
		String categoryName;
		while(iterator.hasNext()) {
			c = iterator.next();
			categoryName = c.getName();
			categoryList.add(categoryName);
		}
		
		
	}
}

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
 * @author fmartins
 *
 */
public class ObtainCategoriesHandler implements IObtainCategoriesHandler {

	// This is a stub, so there is no need for attributes.
	// I guess you will need, maybe, the current user ;)
	
	protected User currentUser;
	protected LinkedList<String> categoryList;
	private Collection<Category> userCategories;
	
	public ObtainCategoriesHandler(User authenticatedUser) {
		this.currentUser = authenticatedUser;
	}
	
	@Override
	public Iterable<String> getCategoriesAuthenticatedUser() {
		System.out.println("ObtainCategories: getCategoriesAuthenticatedUser()");
		createCategoriesList();
		return categoryList;
	}
	
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

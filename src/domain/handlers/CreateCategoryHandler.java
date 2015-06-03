package domain.handlers;

import domain.User;
import domain.interfaces.ICreateCategoryHandler;

/**
 * A create indicator handler used for building the user interface
 * 
 * @author Joao R. && Simao N. && Miguel V.
 * @author fc45582 && fc45681 && fc39279
 * 
 */
public class CreateCategoryHandler extends ObtainCategoriesHandler 
								 implements ICreateCategoryHandler {
	
	
	/**
	 * constructor 
	 * 
	 * @see ObtainCategoriesHandler#ObtainCategoriesHandler(User)
	 */
	public CreateCategoryHandler(User authenticatedUser) {
		super(authenticatedUser);
	}

	/**
	 * @see ICreateCategoryHandler#newCategory()
	 */
	@Override
	public void newCategory() {
		//System.out.println("CreateCategory: newCategory()");
		super.createCategoriesList();
	}

	/**
	 * create a new category
	 * 
	 * @see ICreateCategoryHandler#createCategory(String)
	 */
	@Override
	public boolean createCategory(String catId) {
		//System.out.println("CreateCategory: createCategory(\"" + catId + "\")");
		return this.currentUser.createCategory(catId);
	}
	
}

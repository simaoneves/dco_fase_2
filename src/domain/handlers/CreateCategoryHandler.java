package domain.handlers;

import domain.User;
import domain.interfaces.ICreateCategoryHandler;

/**
 * A create indicator handler used for building the user interface
 * 
 * @author fmartins
 *
 */
public class CreateCategoryHandler extends ObtainCategoriesHandler 
								 implements ICreateCategoryHandler {
	
	
	public CreateCategoryHandler(User authenticatedUser) {
		super(authenticatedUser);
	}

	@Override
	public void newCategory() {
		System.out.println("CreateCategory: newCategory()");
		super.createCategoriesList();
	}

	@Override
	public boolean createCategory(String catId) {
		System.out.println("CreateCategory: createCategory(\"" + catId + "\")");
		return this.currentUser.createCategory(catId);
	}
	
}

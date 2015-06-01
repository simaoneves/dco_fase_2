package domain.stubs;

import domain.interfaces.ICreateCategoryHandler;

/**
 * A create indicator handler used for building the user interface
 * 
 * @author fmartins
 *
 */
public class CreateCategoryHandler extends ObtainCategoriesHandler 
								 implements ICreateCategoryHandler {

	@Override
	public void newCategory() {
		System.out.println("CreateCategory: newCategory()");
	}

	@Override
	public boolean createCategory(String name) {
		System.out.println("CreateCategory: createCategory(\"" + name + "\")");
		return !name.equals("fail");
	}
}

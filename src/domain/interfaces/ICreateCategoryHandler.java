package domain.interfaces;

/**
 * The interface with the create category use case
 * 
 * @author fmartins
 *
 */
public interface ICreateCategoryHandler extends IObtainCategoriesHandler {

    /**
     * Start the use case for the new category
     */
    void newCategory();

    /**
	 * @return True if the category was created.
	 */
    boolean createCategory(String name);
}

package domain.interfaces;

/**
 * The interface with the obtain categories sub-use case
 * Notice that this sub-case is used in multiple use cases
 * 
 * @author fmartins
 *
 */
public interface IObtainCategoriesHandler {

	/**
	 * @return The names of the categories in the system
	 */
    Iterable<String> getCategoriesAuthenticatedUser();
}

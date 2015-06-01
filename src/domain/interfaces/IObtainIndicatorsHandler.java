package domain.interfaces;

/**
 * The interface with the obtain indicators from a category sub-use case
 * Notice that this sub-case is used in multiple use cases
 * 
 * @author fmartins
 *
 */
public interface IObtainIndicatorsHandler extends IObtainCategoriesHandler {

	/**
	 * Indicate the name of the category the indicator belongs to
	 * 
	 * @param name The name of the category
	 */
	void selectCategory(String name);

	/**
	 * @return The names of the indicators associated with the 
	 * selected indicator
	 */
	Iterable<String> getIndicatorsAuthenticatedUser();
}

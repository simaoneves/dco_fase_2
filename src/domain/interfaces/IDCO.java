package domain.interfaces;

/**
 * The interface with the application main object
 * 
 * @author fmartins
 *
 */
public interface IDCO {

	/**
	 * @return The handler of the create indicator use case
	 */
	ICreateIndicatorHandler getCreateIndicatorHandler();

	/**
	 * @return The handler of the login use case
	 */
	ILoginHandler getLoginHandler();

	/**
	 * @return The handler of the obtain categories use case
	 */
	IObtainCategoriesHandler getObtainCategoriesHandler();

	/**
	 * @return The handler of the obtain indicators for category use case
	 */
	IObtainIndicatorsHandler getObtainIndicatorsHandler();

	/**
	 * @return The handler of the create categories use case
	 */
	ICreateCategoryHandler getCreateCategoryHandler();

	/**
	 * @return The handler of the create user use case
	 */
	INewUserHandler getNewUserHandler();

	/**
	 * @return The handler of the add observations for an indicator use case
	 */
	IAddObservationsHandler getAddObservationsHandler();

	/**
	 * @return The handler of the create units use case
	 */
	ICreateUnitHandler getCreateUnitHandler();
}

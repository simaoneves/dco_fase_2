package domain;

import domain.catalog.UnitCatalog;
import domain.catalog.UserCatalog;
import domain.handlers.LoginHandler;
import domain.handlers.NewUserHandler;
import domain.handlers.CreateCategoryHandler;
import domain.handlers.ObtainCategoriesHandler;
import domain.handlers.CreateIndicatorHandler;
import domain.handlers.ObtainIndicatorsHandler;
import domain.handlers.ObtainUnitsHandler;
import domain.handlers.CreateUnitHandler;
import domain.handlers.AddObservationsHandler;
import domain.interfaces.IAddObservationsHandler;
import domain.interfaces.ICreateCategoryHandler;
import domain.interfaces.ICreateIndicatorHandler;
import domain.interfaces.ICreateUnitHandler;
import domain.interfaces.IDCO;
import domain.interfaces.ILoginHandler;
import domain.interfaces.INewUserHandler;
import domain.interfaces.IObtainCategoriesHandler;
import domain.interfaces.IObtainIndicatorsHandler;
import services.SessionManager;

/**
 * An implementation of the main class of the domain.
 * It executes the start up use case and provides the
 * handlers application use case handlers
 * 
 * @author Joao R. && Simao N. && Miguel V.
 * @author fc45582 && fc45681 && fc39279
 * 
 */
public class DCO implements IDCO {
	
	/**
	 * attributes
	 */
	public UnitCatalog unitCatalog;
	public UserCatalog userCatalog;

	/**
	 * constructor
	 * 
	 * @ensures unitCatalog != null &&
	 * 			userCatalog != null
	 */
	public DCO() {
		this.unitCatalog = new UnitCatalog();
		this.userCatalog = new UserCatalog();
	}
	
	/**
	 * get authenticated user
	 * 
	 * @return
	 * 		User if a user is auth
	 * 		Null if not
	 */
	public User getAuthenticatedUser() {
		SessionManager sm = SessionManager.getInstance();
		String name = sm.getAuthenticatedUser();
		return userCatalog.getUser(name);
	}
	
	/**
	 * get unit catalog
	 * 
	 * @return
	 * 		system units catalog
	 */
	public UnitCatalog getUnitCatalog() {
		return unitCatalog;
	}
	
	/**
	 * @see IDCO#getCreateIndicatorHandler()
	 */
	@Override
	public ICreateIndicatorHandler getCreateIndicatorHandler() {
		User auth = getAuthenticatedUser();
		return new CreateIndicatorHandler(auth, unitCatalog);
	}

	/**
	 * @see IDCO#getAddObservationsHandler()
	 */
	@Override
	public IAddObservationsHandler getAddObservationsHandler() {
		User auth = getAuthenticatedUser();
		return new AddObservationsHandler(auth, unitCatalog);
	}

	/**
	 * @see IDCO#getLoginHandler()
	 */
	@Override
	public ILoginHandler getLoginHandler() {
		return new LoginHandler(userCatalog);
	}

	/**
	 * @see IDCO#getObtainCategoriesHandler()
	 */
	@Override
	public IObtainCategoriesHandler getObtainCategoriesHandler() {
		User auth = getAuthenticatedUser();
		return new ObtainCategoriesHandler(auth);
	}

	/**
	 * @see IDCO#getObtainIndicatorsHandler()
	 */
	@Override
	public IObtainIndicatorsHandler getObtainIndicatorsHandler() {
		User auth = getAuthenticatedUser();
		return new ObtainIndicatorsHandler(auth);
	}

	/**
	 * @see IDCO#getCreateCategoryHandler()
	 */
	@Override
	public ICreateCategoryHandler getCreateCategoryHandler() {
		User auth = getAuthenticatedUser();
		return new CreateCategoryHandler(auth);
	}

	/**
	 * @see IDCO#getNewUserHandler()
	 */
	@Override
	public INewUserHandler getNewUserHandler() {
		return new NewUserHandler(userCatalog);
	}

	/**
	 * @see IDCO#getCreateUnitHandler()
	 */
	@Override
	public ICreateUnitHandler getCreateUnitHandler() {
		return new CreateUnitHandler(unitCatalog);
	}
	
	/**
	 * get obtain units handler
	 * 
	 * @return
	 * 	 	obtain indicators handler
	 */
	public IObtainIndicatorsHandler getObtainUnitsHandler() {
		User auth = getAuthenticatedUser();
		return new ObtainUnitsHandler(auth, unitCatalog);
	}
}


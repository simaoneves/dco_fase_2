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
 * @author fmartins
 *
 */
public class DCO implements IDCO {
	
	public UnitCatalog unitCatalog;
	public UserCatalog userCatalog;

	public DCO() {
		this.unitCatalog = new UnitCatalog();
		this.userCatalog = new UserCatalog();
	}
	
	public User getAuthenticatedUser() {
		SessionManager sm = SessionManager.getInstance();
		String name = sm.getAuthenticatedUser();
		return userCatalog.getUser(name);
	}
	
	public UnitCatalog getUnitCatalog() {
		return unitCatalog;
	}
	
	@Override
	public ICreateIndicatorHandler getCreateIndicatorHandler() {
		User auth = getAuthenticatedUser();
		return new CreateIndicatorHandler(auth, unitCatalog);
	}

	@Override
	public IAddObservationsHandler getAddObservationsHandler() {
		User auth = getAuthenticatedUser();
		return new AddObservationsHandler(auth, unitCatalog);
	}

	@Override
	public ILoginHandler getLoginHandler() {
		return new LoginHandler(userCatalog);
	}

	@Override
	public IObtainCategoriesHandler getObtainCategoriesHandler() {
		User auth = getAuthenticatedUser();
		return new ObtainCategoriesHandler(auth);
	}

	@Override
	public IObtainIndicatorsHandler getObtainIndicatorsHandler() {
		User auth = getAuthenticatedUser();
		return new ObtainIndicatorsHandler(auth);
	}

	@Override
	public ICreateCategoryHandler getCreateCategoryHandler() {
		User auth = getAuthenticatedUser();
		return new CreateCategoryHandler(auth);
	}

	@Override
	public INewUserHandler getNewUserHandler() {
		return new NewUserHandler(userCatalog);
	}

	@Override
	public ICreateUnitHandler getCreateUnitHandler() {
		return new CreateUnitHandler(unitCatalog);
	}
	
	public IObtainIndicatorsHandler getObtainUnitsHandler() {
		User auth = getAuthenticatedUser();
		return new ObtainUnitsHandler(auth, unitCatalog);
	}
}


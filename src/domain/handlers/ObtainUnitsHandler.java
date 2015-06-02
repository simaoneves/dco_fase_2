package domain.handlers;

import java.util.LinkedList;

import domain.User;
import domain.catalog.UnitCatalog;
import domain.interfaces.IObtainUnitsHandler;

/**
 * An obtain units handler used for building the user interface
 * 
 * @author fmartins
 *
 */
public class ObtainUnitsHandler extends ObtainIndicatorsHandler 
				implements IObtainUnitsHandler {
	
	protected UnitCatalog unitCat;
	protected LinkedList<String> indicatorsList;	
	
	public ObtainUnitsHandler(User authenticatedUser, UnitCatalog unitCat) {
		super(authenticatedUser);
		this.unitCat = unitCat;
	}

	@Override
	public Iterable<String> getAllUnits() {
		System.out.println("ObtainUnitsHandler: getUnitNames()");
		// return Arrays.asList("Km", "Kg", "Km/h", "Unidades");
		// MAL
		return createUnitsList();
	}

	@Override
	public void selectCategory(String name) {
		// TODO Auto-generated method stub
		
	}
	
	protected Iterable<String> createUnitsList(){
		return unitCat.unitsList();
	}

	@Override
	public Iterable<String> getIndicatorsAuthenticatedUser() {
		// TODO Auto-generated method stub
		return this.createIndicatorsList(this.currentCat.getName());
	}

	@Override
	public Iterable<String> getCategoriesAuthenticatedUser() {
		// TODO Auto-generated method stub
		return categoryList;
	}
}

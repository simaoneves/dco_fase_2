package domain.handlers;

import java.util.LinkedList;

import domain.User;
import domain.catalog.UnitCatalog;
import domain.interfaces.IObtainUnitsHandler;

/**
 * An obtain units handler used for building the user interface
 * 
 * @author Joao R. && Simao N.
 *
 */
public class ObtainUnitsHandler extends ObtainIndicatorsHandler 
				implements IObtainUnitsHandler {
	
	/**
	 * attributes
	 */
	protected UnitCatalog unitCat;
	protected LinkedList<String> indicatorsList;	
	
	/**
	 * constructor
	 * 
	 * @see ObtainIndicatorsHandler#ObtainIndicatorsHandler(User)
	 * @param unitCat
	 * 		system units
	 * @ensures this.unitCat = unitCat
	 */
	public ObtainUnitsHandler(User authenticatedUser, UnitCatalog unitCat) {
		super(authenticatedUser);
		this.unitCat = unitCat;
	}

	/**
	 * @see IObtainUnitsHandler#getAllUnits()
	 */
	@Override
	public Iterable<String> getAllUnits() {
		System.out.println("ObtainUnitsHandler: getUnitNames()");
		// return Arrays.asList("Km", "Kg", "Km/h", "Unidades");
		// MAL
		return createUnitsList();
	}

	/**
	 * @see IObtainUnitsHandler#selectCategory(String)
	 */
	@Override
	public void selectCategory(String name) {
		// TODO Auto-generated method stub
		
	}
	
	protected Iterable<String> createUnitsList(){
		return unitCat.unitsList();
	}

	/**
	 * @see ObtainIndicatorsHandler#getIndicatorsAuthenticatedUser()
	 */
	@Override
	public Iterable<String> getIndicatorsAuthenticatedUser() {
		
		return this.createIndicatorsList(this.currentCat.getName());
	}

	/**
	 * @see ObtainIndicatorsHandler#getCategoriesAuthenticatedUser()
	 */
	@Override
	public Iterable<String> getCategoriesAuthenticatedUser() {
		
		return categoryList;
	}
}

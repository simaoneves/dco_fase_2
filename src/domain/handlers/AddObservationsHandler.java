package domain.handlers;


import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import domain.Indicator;
import domain.Observation;
import domain.Unit;
import domain.User;
import domain.catalog.UnitCatalog;
import domain.interfaces.IAddObservationsHandler;

/**
 * Add observation handler used for building the user interface
 *
 * @author Joao R. && Simao N. && Miguel V.
 * @author fc45582 && fc45681 && fc39279
 * 
 */
public class AddObservationsHandler extends ObtainUnitsHandler 
							implements IAddObservationsHandler {
	
	/**
	 * attributes
	 */
	@SuppressWarnings("unused")
	private String selectedIndicator;
	private Unit unit;
	private List<Observation> observList;
	LinkedList<String> indicatorsList;
	
	@SuppressWarnings("unused")
	private Iterable<String> unitsList;
	
	/**
	 * constructor
	 * 
	 * @see ObtainUnitsHandler#ObtainUnitsHandler(User, UnitCatalog)
	 */
	public AddObservationsHandler(User authenticatedUser, UnitCatalog unitCat) {
		super(authenticatedUser, unitCat);
		
	}
	
	/**
	 * @see IAddObservationsHandler#initiateRegister()
	 */
	@Override
	public void initiateRegister() {
		//System.out.println("AddObservations: initiateRegister()");
		this.categoryList = createCategoriesList();
    }
	
	/**
	 * get indicators by given category
	 * 
	 * @param catId
	 * 		
	 */
	public void selectCategory(String catId) {
		indicatorsList = createIndicatorsList(catId);
	}
	
	/**
	 * @see IAddObservationsHandler#selectIndicator(String)
	 */
	@Override
	public void selectIndicator(String name) {
		//System.out.println("ObtainUnitsHandler: selectIndicator(\"" + name + "\")");
		this.selectedIndicator = name;
		
		Iterator<Indicator> iterator = this.currentCat.getIndicators();
		Indicator ind = null;
		while(iterator.hasNext()) {
			ind = iterator.next();
			if(ind.getName().equals(name))
				break;
		}
		this.unit = this.currentCat.setCurrentIndicator(ind);
		this.unitsList = createUnitsList();
	}
	
	/**
	 * @see ObtainUnitsHandler#createUnitsList()
	 */
	@Override
	protected Iterable<String> createUnitsList() {
		String unNick = this.unit.getNick();
		List<String> unitsList = new LinkedList<String>();
		unitsList.add(unNick);
		Iterable<Unit> compatibleUnitsList = unit.getCompatibleUnits();
		addCompatible(compatibleUnitsList, unitsList);
		return unitsList;
	}
	
	private void addCompatible(Iterable<Unit> compatibleUnitsList, List<String> unitsList){
		Iterator<Unit> iter = compatibleUnitsList.iterator();
		while(iter.hasNext()) {
			String nick = iter.next().getNick();
			unitsList.add(nick);
		}
	}
    
	/**
	 * @see ObtainUnitsHandler#getAllUnits()
	 */
	@Override
	public Iterable<String> getAllUnits() {
		//System.out.println("AddObservations: getAllUnits() for indicator \"" + 
			//	selectedIndicator + "\"");		
		return super.getAllUnits();		
    }
	
	/**
	 * @requires getUnitsOfObservation contains name
	 * @param name
	 */
	@Override
    public void selectUnit(String name) {
		//System.out.println("AddObservations: selectUnit(\"" + name + "\")");
		Unit selectedUnit = this.unitCat.getUnit(name);
		this.currentCat.setObservationsUnit(selectedUnit);
		this.observList = new LinkedList<Observation>();
    }

	/**
	 * @see IAddObservationsHandler#newObservation(int, int, int, double)
	 */
	@Override
    public void newObservation(int y, int m, int d, double val) {
		//System.out.println("AddObservations: newObservation(" + y + ", " + m + ", " + d + ", " + val + ")");
		Calendar date = Calendar.getInstance();
		date.set(y, m, d);
		Observation o = new Observation(date, val);
		this.observList.add(o);
    }
    
	/**
	 * @see IAddObservationsHandler#confirmObservations()
	 */
	@Override
    public int confirmObservations() {
		//System.out.println("AddObservations: confirmObservations()");
		this.currentCat.addObservationsCurrentIndicator(this.observList);
        return this.observList.size();
    }

	/**
	 * @see IAddObservationsHandler#cancel()
	 */
	@Override
	public void cancel() {
		//System.out.println("AddObservations: cancel()");
	}
	
	/**
	 * get authenticated user categories
	 * 
	 * @return
	 * 		categories of auth user
	 */
	public Iterable<String> getCategoriesAuthenticatedUser() {
		return super.getCategoriesAuthenticatedUser();
	}
	
	/**
	 * get indicators of current category of auth user
	 * 
	 * @return
	 * 		indicators of a category
	 */
	public Iterable<String> getIndicatorsAuthenticatedUser() {
		return super.getIndicatorsAuthenticatedUser();
	}
		
}

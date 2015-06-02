package domain.handlers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
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
 * @author fmartins
 *
 */
public class AddObservationsHandler extends ObtainUnitsHandler 
							implements IAddObservationsHandler {
	
	public AddObservationsHandler(User authenticatedUser, UnitCatalog unitCat) {
		super(authenticatedUser, unitCat);
		// TODO Auto-generated constructor stub
	}

	private String selectedIndicator;
	private Iterable<String> unitsList;
	private Unit unit;
	private List<Observation> observList;
	
	
	@Override
	public void initiateRegister() {
		System.out.println("AddObservations: initiateRegister()");
		LinkedList<String> catList = createCategoriesList();
    }
	
	public void selectCategory(String catId) {
		LinkedList<String> indicatorsList = createIndicatorsList(catId);
	}
	
	@Override
	public void selectIndicator(String name) {
		System.out.println("ObtainUnitsHandler: selectIndicator(\"" + name + "\")");
		selectedIndicator = name;
		
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
	
	@Override
	protected Iterable<String> createUnitsList() {
		String unNick = this.unit.getNick();
		List<String> unitsList = new LinkedList<String>();
		unitsList.add(unNick);
		Iterator<Unit> iter = unit.getCompatibleUnits();
		addCompatible(iter, unitsList);
		return unitsList;
	}
	
	private void addCompatible(Iterator<Unit> iter, List<String> unitsList){
		while(iter.hasNext()) {
			String nick = iter.next().getNick();
			unitsList.add(nick);
		}
	}
               
	@Override
	public Iterable<String> getAllUnits() {
		System.out.println("AddObservations: getAllUnits() for indicator \"" + 
				selectedIndicator + "\"");
		return super.getAllUnits();		
    }
	
	/**
	 * @requires getUnitsOfObservation contains name
	 * @param name
	 */
	@Override
    public void selectUnit(String name) {
		System.out.println("AddObservations: selectUnit(\"" + name + "\")");
		
		Unit selectedUnit = this.unitCat.getUnit(name);
		System.out.println("ObservUnit: " + selectedUnit);
		this.currentCat.setObservationsUnit(selectedUnit);
		this.observList = new LinkedList<Observation>();
    }

	@Override
    public void newObservation(int y, int m, int d, double val) {
		System.out.println("AddObservations: newObservation(" + y + ", " + m + ", " + d + ", " + val + ")");
		LocalDate date = LocalDate.of(y, m, d);
		Observation o = new Observation(date, val);
		this.observList.add(o);
    }
    
	@Override
    public int confirmObservations() {
		System.out.println("AddObservations: confirmObservations()");
		this.currentCat.addObservationsCurrentIndicator(this.observList);
        return 0;
    }

	@Override
	public void cancel() {
		System.out.println("AddObservations: cancel()");
	}
	
	public Iterable<String> getCategoriesAuthenticatedUser() {
		return super.getCategoriesAuthenticatedUser();
	}
	
	public Iterable<String> getIndicatorsAuthenticatedUser() {
		return super.getIndicatorsAuthenticatedUser();
	}
		
}

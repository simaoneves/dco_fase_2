package domain.handlers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import domain.Indicator;
import domain.UnitCatalog;
import domain.User;
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
		this.currentCat.setCurrentIndicator(ind);
	}
               
	@Override
	public Iterable<String> getAllUnits() {
		System.out.println("AddObservations: getAllUnits() for indicator \"" + 
				selectedIndicator + "\"");
		return Arrays.asList("Km", "Kg", "Tar");		
    }
    
	@Override
    public void selectUnit(String name) {
		System.out.println("AddObservations: selectUnit(\"" + name + "\")");
    }

	@Override
    public void newObservation(int y, int m, int d, double val) {
		System.out.println("AddObservations: newObservation(" + y + ", " + m + ", " + d + ", " + val + ")");
    }
    
	@Override
    public int confirmObservations() {
		System.out.println("AddObservations: confirmObservations()");
        return 0;
    }

	@Override
	public void cancel() {
		System.out.println("AddObservations: cancel()");
	}
}

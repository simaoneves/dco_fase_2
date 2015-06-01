package domain.stubs;

import java.util.Arrays;

import domain.interfaces.IAddObservationsHandler;

/**
 * Add observation handler used for building the user interface
 *
 * @author fmartins
 *
 */
public class AddObservationsHandler extends ObtainUnitsHandler 
							implements IAddObservationsHandler {
	
	private String selectedIndicator;
	
	@Override
	public void initiateRegister() {
		System.out.println("AddObservations: initiateRegister()");
    }
	
	@Override
	public void selectIndicator(String name) {
		System.out.println("ObtainUnitsHandler: selectIndicator(\"" + name + "\")");
		selectedIndicator = name;
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

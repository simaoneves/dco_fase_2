package domain.stubs;

import java.util.Arrays;

import domain.interfaces.ICreateUnitHandler;

/**
 * Create units handler used for building the user interface
 *
 * @author fmartins
 *
 */
public class CreateUnitHandler implements ICreateUnitHandler {
		
	@Override
	public void newUnit() {
		System.out.println("CreateUnit: newUnit()");
    }
	
	@Override
	public boolean registerUnit(String name, String nick) {
		System.out.println("CreateUnit: registerUnit(\"" + name + "\", " + 
	            "\"" + nick + "\")");
		return !name.equals("fail");
	}
         
	@Override
    public boolean addCompatibleUnit(String nick) {
		System.out.println("CreateUnit: addCompatibleUnit(\"" + nick + "\")");
		return !nick.equals("fail");
    }

	@Override
    public void endCreate() {
		System.out.println("CreateUnit: endCreate()");
    }

	@Override
	public void cancel() {
		System.out.println("CreateUnit: cancel()");
	}

	@Override
	public Iterable<String> getAllUnits() {
		System.out.println("CreateUnit: getUnitNames()");
		return Arrays.asList("Km", "Kg", "Km/h", "Unidades");		
	}
}

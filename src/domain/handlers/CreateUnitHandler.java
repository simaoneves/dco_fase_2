package domain.handlers;

import domain.UnitCatalog;
import domain.Unit;
import java.util.Arrays;

import domain.interfaces.ICreateUnitHandler;

/**
 * Create units handler used for building the user interface
 *
 * @author fmartins
 *
 */
public class CreateUnitHandler implements ICreateUnitHandler {
	
	private UnitCatalog unitCat;
	private Unit currentUnit;
	
	public CreateUnitHandler(UnitCatalog unitCat){
		this.unitCat = unitCat;
	}
	@Override
	public void newUnit() {
		System.out.println("CreateUnit: newUnit()");
    }
	
	@Override
	public boolean registerUnit(String name, String nick) {
		System.out.println("CreateUnit: registerUnit(\"" + name + "\", " + 
	            "\"" + nick + "\")");
		Unit unit = unitCat.getUnit(nick);
		if (unit == null)
			this.currentUnit = new Unit(name, nick);
		return (unit == null);
	}
         
	@Override
    public boolean addCompatibleUnit(String nick) {
		System.out.println("CreateUnit: addCompatibleUnit(\"" + nick + "\")");
		Unit unit = unitCat.getUnit(nick);
		boolean b = false;
		if (unit != null)
			b = this.currentUnit.addCompatible(unit);
		return (unit != null && b);
    }

	@Override
    public void endCreate() {
		System.out.println("CreateUnit: endCreate()");
		unitCat.addUnit(this.currentUnit);
    }
	
	@Override
	public Iterable<String> getAllUnits() {
		System.out.println("CreateUnit: getAllUnits()");
		return unitCat.unitsList();
	}
	
	@Override
	public void cancel() {
		System.out.println("CreateUnit: cancel()");
	}
}

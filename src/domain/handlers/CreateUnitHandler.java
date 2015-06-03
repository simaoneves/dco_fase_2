package domain.handlers;

import domain.Unit;

import java.util.Arrays;

import domain.catalog.UnitCatalog;
import domain.interfaces.ICreateUnitHandler;

/**
 * Create units handler used for building the user interface
 *
 * @author Joao R. && Simao N.
 *
 */
public class CreateUnitHandler implements ICreateUnitHandler {
	
	/**
	 * attributes
	 */
	private UnitCatalog unitCat;
	private Unit currentUnit;
	
	/**
	 * constructor
	 * 
	 * @param unitCat
	 * 		system unit catalog
	 * @ensures this.unitCat == unitCat
	 */
	public CreateUnitHandler(UnitCatalog unitCat){
		this.unitCat = unitCat;
	}
	
	/**
	 * @see ICreateUnitHandler#newUnit()
	 */
	@Override
	public void newUnit() {
		System.out.println("CreateUnit: newUnit()");
    }
	
	/**
	 * @see ICreateUnitHandler#registerUnit(String, String)
	 */
	@Override
	public boolean registerUnit(String name, String nick) {
		System.out.println("CreateUnit: registerUnit(\"" + name + "\", " + 
	            "\"" + nick + "\")");
		Unit unit = unitCat.getUnit(nick);
		if (unit == null)
			this.currentUnit = new Unit(name, nick);
		return (unit == null);
	}
       
	/**
	 * @see ICreateUnitHandler#addCompatibleUnit(String)
	 */
	@Override
    public boolean addCompatibleUnit(String nick) {
		System.out.println("CreateUnit: addCompatibleUnit(\"" + nick + "\")");
		Unit unit = unitCat.getUnit(nick);
		boolean b = false;
		if (unit != null)
			b = this.currentUnit.addCompatible(unit);
		return (unit != null && b);
    }

	/**
	 * @see ICreateUnitHandler#endCreate()
	 */
	@Override
    public void endCreate() {
		System.out.println("CreateUnit: endCreate()");
		unitCat.addUnit(this.currentUnit);
    }
	
	/**
	 * get all unit in the system
	 * 
	 * @see ICreateUnitHandler#getAllUnits()
	 */
	@Override
	public Iterable<String> getAllUnits() {
		System.out.println("CreateUnit: getAllUnits()");
		return unitCat.unitsList();
	}
	
	/**
	 * @see ICreateUnitHandler#cancel()
	 */
	@Override
	public void cancel() {
		System.out.println("CreateUnit: cancel()");
	}
}

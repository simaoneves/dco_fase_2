package domain.handlers;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

import devices.DeviceFactory;
import devices.IDeviceAdapter;
import domain.User;
import domain.Unit;
import domain.catalog.UnitCatalog;
import domain.interfaces.ICreateIndicatorHandler;

/**
 * A create indicator handler used for building the user interface
 * 
 * @author fmartins
 *
 */
public class CreateIndicatorHandler extends ObtainUnitsHandler 
						implements ICreateIndicatorHandler {
	
	private Iterable<String> deviceList;
	
	public CreateIndicatorHandler(User authenticatedUser, UnitCatalog uniCat) {
		super(authenticatedUser, uniCat);
	}

	@Override
	public void newIndicator() {
		System.out.println("CreateIndicator: newIndicator()");
		this.categoryList = createCategoriesList(); 
	}
	
	public void selectCategory(String catId) {
		this.indicatorsList = createIndicatorsList(catId);
	}
			
	@Override 
	public boolean supplyNameAndMode(String name, String mode) {
		System.out.println("CreateIndicator: supplyNameAndMode(\"" + name + "\", \"" + mode + "\")");
		currentCat.createIndicator(name, mode);
		Iterable<String> unitList = createUnitsList();
		if (mode.equals("AUTOMATIC")) {
			this.deviceList = createDevicesList();
		}
		/// MAL
		return true;
	}
	
	private Iterable<String> createDevicesList() {
		return DeviceFactory.INSTANCE.deviceAdaptersList();
	}
	
	@Override 
	public Iterable<String> getDeviceNames() {
		System.out.println("CreateIndicator: getDeviceNames()");
		return getAllDevices();		
	}

	@Override
	public void selectDevice(String name) {
		System.out.println("CreateIndicator: selectDevice(\"" + name + "\")");
		IDeviceAdapter device = DeviceFactory.INSTANCE.getDeviceAdapter(name);
		currentCat.setDeviceCurrentIndicator(device);
	}	

	@Override
	public void cancel() {
		System.out.println("CreateIndicator: cancel()");
	}

	@Override
	public void selectUnit(String name) {
		System.out.println("CreateIndicator: selectUnit(\"" + name + "\")");
		Unit unit = unitCat.getUnit(name);
		currentCat.setUnitCurrentIndicator(unit);
	}

	@Override
	public void confirm() {
		System.out.println("CreateIndicator: confirm()");
		currentCat.confirmCreationIndicator();
	}
	
	public Iterable<String> getAllDevices() {
		System.out.println("TESTE :" + deviceList);
		return this.deviceList;
	}
}

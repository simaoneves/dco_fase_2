package domain.handlers;

import devices.DeviceFactory;
import devices.IDeviceAdapter;
import domain.User;
import domain.Unit;
import domain.catalog.UnitCatalog;
import domain.interfaces.ICreateIndicatorHandler;

/**
 * A create indicator handler used for building the user interface
 * 
 * @author Joao R. && Simao N. && Miguel V.
 * @author fc45582 && fc45681 && fc39279
 * 
 */
public class CreateIndicatorHandler extends ObtainUnitsHandler 
						implements ICreateIndicatorHandler {
	
	/**
	 * attributes
	 */
	private Iterable<String> deviceList;
	
	@SuppressWarnings("unused")
	private Iterable<String> unitList;
	
	/**
	 * constructor
	 * 
	 * @param authenticatedUser
	 * 			authenticated user
	 * @param uniCat
	 * 			system unit catalog
	 * @see ObtainUnitsHandler#ObtainUnitsHandler(User, UnitCatalog)
	 */
	public CreateIndicatorHandler(User authenticatedUser, UnitCatalog uniCat) {
		super(authenticatedUser, uniCat);
	}

	/**
	 * @see ICreateIndicatorHandler#newIndicator()
	 */
	@Override
	public void newIndicator() {
		//System.out.println("CreateIndicator: newIndicator()");
		this.categoryList = createCategoriesList(); 
	}
	
	/**
	 * select current category by name
	 * 
	 * @param catId
	 * 			category name to be considered
	 */
	public void selectCategory(String catId) {
		this.indicatorsList = createIndicatorsList(catId);
	}
	
	/**
	 * @see ICreateIndicatorHandler#supplyNameAndMode(String, String)
	 */
	@Override 
	public boolean supplyNameAndMode(String name, String mode) {
		//System.out.println("CreateIndicator: supplyNameAndMode(\"" + name + "\", \"" + mode + "\")");
		
		if(this.indicatorsList.contains(name))
			return false;
		
		currentCat.createIndicator(name, mode);
		this.unitList = createUnitsList();
		if (mode.equals("AUTOMATIC")) {
			this.deviceList = createDevicesList();
		}
		
		return true;
	}
	
	/**
	 * get a list of device adapters
	 * 
	 * @return
	 * 		get a list of device adapters
	 */
	private Iterable<String> createDevicesList() {
		return DeviceFactory.INSTANCE.deviceAdaptersList();
	}
	
	/**
	 * get the name of all available devices
	 * 
	 * @see ICreateIndicatorHandler#getDeviceNames()
	 */
	@Override 
	public Iterable<String> getDeviceNames() {
		//System.out.println("CreateIndicator: getDeviceNames()");
		return getAllDevices();		
	}

	/**
	 * @see ICreateIndicatorHandler#selectDevice(String)
	 * @requires currentCat != null
	 */
	@Override
	public void selectDevice(String name) {
		//System.out.println("CreateIndicator: selectDevice(\"" + name + "\")");
		IDeviceAdapter device = DeviceFactory.INSTANCE.getDeviceAdapter(name);
		currentCat.setDeviceCurrentIndicator(device);
	}	

	/**
	 * @see ICreateIndicatorHandler#cancel()
	 */
	@Override
	public void cancel() {
		//System.out.println("CreateIndicator: cancel()");
	}

	/**
	 * @see ICreateIndicatorHandler#selectUnit(String)
	 */
	@Override
	public void selectUnit(String name) {
		//System.out.println("CreateIndicator: selectUnit(\"" + name + "\")");
		Unit unit = unitCat.getUnit(name);
		currentCat.setUnitCurrentIndicator(unit);
	}

	/**
	 * @see ICreateIndicatorHandler#confirm()
	 */
	@Override
	public void confirm() {
		//System.out.println("CreateIndicator: confirm()");
		currentCat.confirmCreationIndicator();
	}
	
	/**
	 * get the name of all available devices
	 * 
	 * @return
	 * 		list with devices names
	 */
	public Iterable<String> getAllDevices() {
		return this.deviceList;
	}
}

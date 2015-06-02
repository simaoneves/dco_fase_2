package domain;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import devices.IDeviceAdapter;
import domain.factory.IndicatorFactory;

/**
 * This class represents a category
 * 
 * @author Joao R. && Simao N.
 *
 */
public class Category {
	
	/**
	 * attributes
	 */
	private String name;
	private Map<String, Indicator> indicatorsList;
	private Indicator currentIndicator;
	private Unit observUnit;
	
	/**
	 * constructor
	 * 
	 * @param name
	 * 		category name
	 * @ensures this.name != null && indicatorsList != null
	 */
	public Category(String name) {
		this.name = name;
		this.indicatorsList = new HashMap<String, Indicator>();
	}
	
	/**
	 * get category name
	 * 
	 * @return
	 * 		this.name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * get indicators
	 * 
	 * @return
	 * 		category indicators
	 */
	public Iterator<Indicator> getIndicators() {
		return this.indicatorsList.values().iterator();
	}
	
	/**
	 * create a new indicator
	 * 
	 * @param name
	 * 		new indicator name
	 * @param mode
	 * 		new indicator mode
	 * @requires mode.equals("MANUAL") || mode.equals("AUTOMATIC")
	 * @ensures old(currentIndicator) != currentIndicator
	 */
	public void createIndicator(String name, String mode) {
		Indicator curInd = IndicatorFactory.getInstance().getIndicator(name, mode);
		setCurrentIndicator(curInd);
	}
	
	/**
	 * set current indicator
	 * 
	 * @param 	ind
	 * @ensures old(currentIndicator) != currentIndicator
	 * @return 	
	 * 			currentIndicator unit
	 */
	public Unit setCurrentIndicator(Indicator ind) {
		this.currentIndicator = ind;
		return this.currentIndicator.getUnit();
	}
	
	/**
	 * set current indicator unit
	 * 
	 * @param unit
	 * @ensures old(currentIndicator.getUnit()) != currentIndicator.getUnit()
	 */
	public void setUnitCurrentIndicator(Unit unit) {
		this.currentIndicator.setUnit(unit);
	}
	
	/**
	 * set the device of current indicator
	 * 
	 * @param device
	 * 		device to be considered
	 * @requires currentIndicator.getClass() == Automatic.class
	 */
	public void setDeviceCurrentIndicator(IDeviceAdapter device) {
		((Automatic) currentIndicator).setDevice(device);
	}
	
	/**
	 * set unit of observation
	 * 
	 * @param unit
	 * 		unit to be considered
	 */
	public void setObservationsUnit(Unit unit) {
		
		this.observUnit = unit;
	}
	
	/**
	 * confirm creation of indicator
	 */
	public void confirmCreationIndicator() {
		String name = currentIndicator.getName();
		indicatorsList.put(name, currentIndicator);
	}
	
	/**
	 * add observations to the current indicator
	 * 
	 * @param observList
	 * 		observation list with the observations
	 */
	public void addObservationsCurrentIndicator(List<Observation> observList) {
		this.currentIndicator.addObservations(observList, this.observUnit);	
	}

}

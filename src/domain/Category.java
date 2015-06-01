package domain;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import devices.IDeviceAdapter;

public class Category {
	
	private String name;
	private Map<String, Indicator> indicatorsList;
	private Indicator currentIndicator;
	
	public Category(String name) {
		this.name = name;
		this.indicatorsList = new HashMap<String, Indicator>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public Iterator<Indicator> getIndicators() {
		return this.indicatorsList.values().iterator();
	}
	
	public void createIndicator(String name, String mode) {
		Indicator curInd = IndicatorFactory.getInstance().getIndicator(name, mode);
		setCurrentIndicator(curInd);
	}
	
	public void setCurrentIndicator(Indicator ind) {
		this.currentIndicator = ind;
	}
	
	public void setUnitCurrentIndicator(Unit unit) {
		this.currentIndicator.setUnit(unit);
	}
	
	public void setDeviceCurrentIndicator(IDeviceAdapter device) {
		((Automatic) currentIndicator).setDevice(device);
	}
	
	public void confirmCreationIndicator() {
		String name = currentIndicator.getName();
		indicatorsList.put(name, currentIndicator);
	}

}

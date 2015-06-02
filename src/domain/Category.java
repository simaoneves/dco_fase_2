package domain;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import devices.IDeviceAdapter;

public class Category {
	
	private String name;
	private Map<String, Indicator> indicatorsList;
	private Indicator currentIndicator;
	private Unit observUnit;
	
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
	
	public Unit setCurrentIndicator(Indicator ind) {
		this.currentIndicator = ind;
		return this.currentIndicator.getUnit();
	}
	
	public void setUnitCurrentIndicator(Unit unit) {
		this.currentIndicator.setUnit(unit);
	}
	
	public void setDeviceCurrentIndicator(IDeviceAdapter device) {
		((Automatic) currentIndicator).setDevice(device);
	}
	
	public void setObservationsUnit(Unit unit) {
		
		this.observUnit = unit;
	}
	
	public void confirmCreationIndicator() {
		String name = currentIndicator.getName();
		indicatorsList.put(name, currentIndicator);
	}

	public void addObservationsCurrentIndicator(List<Observation> observList) {
		this.currentIndicator.addObservations(observList, this.observUnit);	
	}

}

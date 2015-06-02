package domain;

import java.util.List;

/**
 * This class represents an Indicator
 * 
 * @author Joao R. && Simao N.
 *
 */
public class Indicator {
	
	/**
	 * attributes
	 */
	private String name;
	private Unit unit;
	
	/**
	 * constructor
	 * 
	 * @param name
	 * 		indicator name
	 */
	public Indicator(String name) {
		this.name = name;
	}
	
	/**
	 * get name
	 * @return
	 * 		this.name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * set indicator unit
	 * 
	 * @param unit
	 * 		unit to be considered
	 * @ensures this.unit == unit
	 */
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	/**
	 * get unit
	 * 
	 * @return
	 * 		get unit
	 */
	public Unit getUnit() {
		return this.unit;
	}

	/**
	 * add observations
	 * 
	 * @param observList
	 * 		observations list to be considered
	 * @param observUnit
	 * 		unit of observations inside observList
	 */
	public void addObservations(List<Observation> observList, Unit observUnit) {
		
		if (observUnit != this.unit)
			convertObservations(observList, observUnit);
		
	}

	private void convertObservations(List<Observation> observList, Unit observUnit) {
		System.out.println(observUnit);
		for(Observation o : observList) {
			Double oldVal = o.getValue();
			Double newVal = observUnit.convertTo(this.unit, oldVal);
			o.setValue(newVal);
		}
	}

}

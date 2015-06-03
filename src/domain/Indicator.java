package domain;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents an Indicator
 * 
 * @author Joao R. && Simao N. && Miguel V.
 * @author fc45582 && fc45681 && fc39279
 * 
 */
public class Indicator {
	
	/**
	 * attributes
	 */
	private String name;
	private Unit unit;
	private List<Observation> observationList;
	
	/**
	 * constructor
	 * 
	 * @param name
	 * 		indicator name
	 */
	public Indicator(String name) {
		this.name = name;
		this.observationList = new LinkedList<Observation>();
	}
	
	/**
	 * get name
	 * 
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
	 * 		this indicator unit
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
		
		if (observUnit != this.unit) {
			convertObservations(observList, observUnit);
		}
		this.observationList.addAll(observList);
	}

	private void convertObservations(List<Observation> observList, Unit observUnit) {
		for(Observation o : observList) {
			Double oldVal = o.getValue();
			Double newVal = observUnit.convertTo(this.unit, oldVal);
			o.setValue(newVal);
		}
	}

	/**
	 * returns all the observations in this indicator
	 * 
	 * @return 
	 * 		list with all observations
	 */
	public Iterable<Observation> getObservations() {
		return this.observationList;
	}

}

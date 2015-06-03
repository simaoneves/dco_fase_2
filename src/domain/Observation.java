package domain;

import java.time.LocalDate;

/**
 * This class represents an Observation
 * 
 * @author Joao R. && Simao N.
 *
 */
public class Observation {
	
	/**
	 * attributes
	 */
	private LocalDate localDate;
	private Double value;
	
	/**
	 * constructor
	 * 
	 * @param date
	 * 		date of observation
	 * @param val
	 * 		value of observation
	 * @ensures localDate.equals(date) &&
	 * 			value == val
	 */
	public Observation(LocalDate date, Double val) {
		this.localDate = localDate;
		this.value = val;
	}
	
	/**
	 * get value
	 * 
	 * @return
	 * 		value
	 */
	public Double getValue() {
		return this.value;
	}

	/**
	 * set value
	 * 
	 * @param newVal
	 * 		new value to be considered
	 * @ensures value == newVal
	 */
	public void setValue(Double newVal) {
		this.value = newVal;
	}
}

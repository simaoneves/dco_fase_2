package domain;

import java.util.Calendar;

/**
 * This class represents an Observation
 * 
 * @author Joao R. && Simao N. && Miguel V.
 * @author fc45582 && fc45681 && fc39279
 * 
 */
public class Observation {
	
	/**
	 * attributes
	 */
	private Calendar date;
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
	public Observation(Calendar date, Double val) {
		this.date = date;
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
	
	/**
	 * returns date
	 * 
	 * @return 
	 * 		Calendar of this observation
	 */
	public Calendar getDate() {
		return this.date;
	}
}

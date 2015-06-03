package domain.factory;

import domain.Automatic;
import domain.Indicator;

/**
 * This class represents a indicators factory
 * 
 * @author Joao R. && Simao N.
 *
 */
public class IndicatorFactory {
	
	/**
	 * factory instance
	 */
	public static IndicatorFactory INSTANCE;
	
	/**
	 * constructor
	 */
	private IndicatorFactory() {
		
	}
	
	/**
	 * get factory instance
	 * 
	 * @return
	 * 		factory instance
	 */
	public static IndicatorFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new IndicatorFactory();
		}
		return INSTANCE;
	}
	
	/**
	 * indicators creator
	 * 
	 * @param name
	 * 		new indicator name 
	 * @param mode
	 * 		new indicator mode
	 * @return
	 * 		built indicator
	 * @requires mode != null
	 */
	public Indicator getIndicator(String name, String mode) {
		
		if (mode.equals("AUTOMATIC")) {
			return new Automatic(name);
		}
		else if (mode.equals("MANUAL")) {
			return new Indicator(name);
		}
		else
			return null;
	}
}

package domain.factory;

import domain.Automatic;
import domain.Indicator;

public class IndicatorFactory {
	
	public static IndicatorFactory INSTANCE;
	
	private IndicatorFactory() {
		
	}
	
	public static IndicatorFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new IndicatorFactory();
		}
		return INSTANCE;
	}
	
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

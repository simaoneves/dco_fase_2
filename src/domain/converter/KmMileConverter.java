package domain.converter;

import domain.Pair;


/**
 * This class represents a converter that knows how to handle
 * Km to Mile conversions
 * 
 * @author Joao R. && Simao N. && Miguel V.
 * @author fc45582 && fc45681 && fc39279
 * 
 */
public class KmMileConverter extends AbstractUnitConverter {
	 
	/**
	 * conversion constants
	 */
	private static final double ONE_KILOMETER_IN_MILES = 1/(1.5);
	private static final double ONE_MILE_IN_KILOMETERS = 1.5;
	
	/**
	 * Constructor
	 * 
	 * Adds all the Pairs that this Converter converts
	 */
	public KmMileConverter() {
		add(new Pair<String, String>("m","Km"));
		
		add(new Pair<String, String>("Km","m"));
		add(new Pair<String, String>("Km","Mile"));
		
		add(new Pair<String, String>("Mile","m"));
		add(new Pair<String, String>("Mile","Km"));
	}
	

	/**
	 * @see AbstractUnitConverter#convert(String, String, Double)
	 */
	@Override
	public Double convert(String fromNick, String toNick, Double oldVal) {
		
		Double result = 0.0;
		if (fromNick.equals("Km")){
			switch (toNick) {
				case "Mile":
					result = oldVal * ONE_KILOMETER_IN_MILES;
					break;
				case "m":
					result = oldVal * 1000;
					break;
			}
		}
		
		else if (fromNick.equals("m")){
			switch (toNick) {
				case "Mile":
					result = (oldVal * 1000) * ONE_KILOMETER_IN_MILES;
					break;
				case "Km":
					result = (oldVal / 1000);
					break;
			}
		}
		
		else if (fromNick.equals("Mile")){
			switch (toNick) {
				case "Km":
					result = oldVal * ONE_MILE_IN_KILOMETERS;
					break;
				case "m":
					result = (oldVal * ONE_KILOMETER_IN_MILES) * 1000;
					break;
			}
		}
		return result;
	}
	
}

package domain;

public class KmMileConverter extends AbstractUnitConverter {
	 
	private static final double ONE_KILOMETER_IN_MILES = 0.621371192;
	private static final double ONE_MILE_IN_KILOMETERS = 1.609344;

	@Override
	public Double convert(String nick, String nick2, Double oldVal) {
		// FALTA
		return ONE_MILE_IN_KILOMETERS * oldVal;
	}

	
	
}

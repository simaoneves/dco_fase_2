package domain;

public class KmMileConverter extends AbstractUnitConverter {
	 
	private static final double ONE_KILOMETER_IN_MILES = 0.621371192;
	private static final double ONE_MILE_IN_KILOMETERS = 1.609344;

	@Override
	public Double convert(String fromNick, String toNick, Double oldVal) {
		Double result;
		if (fromNick.equals("km")){
			switch (toNick) {
				case "mile":
					result = oldVal * ONE_KILOMETER_IN_MILES;
					break;
			}
		}
		
		if (fromNick.equals("m")){
			switch (toNick) {
				case "mile":
					result = (oldVal * 1000) * ONE_KILOMETER_IN_MILES;
					break;
			}
		}
		
		return ONE_MILE_IN_KILOMETERS * oldVal;
	}

	
	
}

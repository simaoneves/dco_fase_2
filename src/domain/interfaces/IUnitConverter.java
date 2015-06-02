package domain.interfaces;

public interface IUnitConverter {
	
	boolean doYouConvert(String nick, String unitNick);
	Double convert(String nick, String nick2, Double oldVal);

}

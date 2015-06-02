package domain.interfaces;

public interface IUnitConverter {
	
	boolean doYouConvert(String nick, String unitNick);
	Double convert(String fromNick, String toNick, Double oldVal);

}

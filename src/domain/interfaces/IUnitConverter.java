package domain.interfaces;

/**
 * Interface of a Converter of Units
 * 
 * @author Joao R. && Simao N. && Miguel V.
 * @author fc45582 && fc45681 && fc39279
 *
 */
public interface IUnitConverter {
	
	/**
	 * check if exists a converter that knows how to
	 * handle the conversion between given unit
	 * 
	 * @param nick
	 * 		unit nick, to be converted from
	 * @param unitNick
	 * 		unit nick, to be converted to
	 * 
	 */
	boolean doYouConvert(String nick, String unitNick);
	
	/**
	 * convert a value from a unit to another
	 * 
	 * @param fromNick
	 * 		unit nick, to be converted from
	 * @param toNcik
	 * 		unit nick, to be converted to
	 * @param oldVal
	 * 		value to be converted
	 * @return
	 * 		converted value
	 * @requires
	 * 		fromNick != null
	 */
	Double convert(String fromNick, String toNick, Double oldVal);

}

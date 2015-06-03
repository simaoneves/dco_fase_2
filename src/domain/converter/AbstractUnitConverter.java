package domain.converter;

import domain.interfaces.IUnitConverter;
import domain.Pair;

import java.util.List;

/**
 * This class represents a unit converter template
 * 
 * @author Joao R. && Simao N.
 *
 */
public abstract class AbstractUnitConverter implements IUnitConverter{
	
	/**
	 * attributes
	 */
	private List<Pair<String, String>> fromTo;
	
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
	public boolean doYouConvert(String nick, String unitNick) {
		boolean result = false;
		for(Pair<String, String> pair : fromTo) {
			if (pair.getFirst() == nick && pair.getSecond() == unitNick) {
				result = true;
				break;
			}
		}
		return result;
	}
	
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
	public abstract Double convert(String fromNick, String toNick, Double oldVal);
}

package domain.converter;

import java.util.LinkedList;
import java.util.List;

import domain.Pair;
import domain.interfaces.IUnitConverter;


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
	 * Constructor
	 */
	public AbstractUnitConverter(){
		fromTo = new LinkedList<>();
	}
	
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
		for(Pair<String, String> pair : this.fromTo) {
			if (pair.getFirst().equals(nick) && pair.getSecond().equals(unitNick)) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	
	/**
	 * adds the Pair par to the list fromTo
	 * 
	 * @param par
	 */
	protected void add(Pair<String, String> par){
		this.fromTo.add(par);
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

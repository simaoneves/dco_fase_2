package domain.converter;

import java.util.LinkedList;
import java.util.List;

import domain.Pair;
import domain.interfaces.IUnitConverter;


/**
 * This class represents a unit converter template
 * 
 * @author Joao R. && Simao N. && Miguel V.
 * @author fc45582 && fc45681 && fc39279
 * 
 */
public abstract class AbstractUnitConverter implements IUnitConverter{
	
	/**
	 * attributes
	 */
	private List<Pair<String, String>> fromTo;
	
	/**
	 * Constructor
	 * 
	 */
	public AbstractUnitConverter(){
		this.fromTo = new LinkedList<>();
	}
	
	/**
	 * @see IUnitConverter#doYouConvert(String, String)
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
	 * @see IUnitConverter#convert(String, String, Double)
	 */
	public abstract Double convert(String fromNick, String toNick, Double oldVal);
	
}

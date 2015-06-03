package domain.factory;

import java.util.List;
import java.util.LinkedList;

import domain.converter.AbstractUnitConverter;
import domain.converter.KmMileConverter;

/**
 * This class represents a unit converters factory
 * 
 * @author Joao R. && Simao N.
 *
 */
public class UnitConverterFactory {
	
	/**
	 * attributes
	 */
	//all converters list
	private List<AbstractUnitConverter> absUnitConverterList;
	//factory instace
	private static UnitConverterFactory INSTANCE;
	
	/**
	 * constructor
	 * 
	 * @ensures absUnitConverterList != null
	 */
	private UnitConverterFactory() {
		this.absUnitConverterList = new LinkedList<AbstractUnitConverter>();
		this.absUnitConverterList.add(new KmMileConverter());
	}
	
	/**
	 * get factory instance
	 * 
	 * @return
	 * 		factory instance
	 */
	public static UnitConverterFactory getInstance() {
		if (INSTANCE == null)
			INSTANCE = new UnitConverterFactory();
		return INSTANCE;
	}
	
	/**
	 * get the converter that knows how to convert
	 * from a unit to another
	 * 
	 * @param nick
	 * 		unit nick, to be converted from
	 * @param unitNick
	 * 		unit nick, to be converted to
	 * @return
	 * 		AbstractUnitConverter if exists at least one that converts
	 * 		Null if not
	 */
	public AbstractUnitConverter getUnitConverter(String nick, String unitNick) {
		for(AbstractUnitConverter conv : absUnitConverterList) {
			if (conv.doYouConvert(nick, unitNick)) {
				return conv;
			}
		}
		return null;
	}
	
}

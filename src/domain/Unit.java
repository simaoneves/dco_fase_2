package domain;

import domain.converter.AbstractUnitConverter;
import domain.factory.UnitConverterFactory;
import domain.interfaces.IUnitConverter;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

/**
 * This class represents a Unit of observation
 * 
 * @author Joao R. && Simao N.
 *
 */
public class Unit {
	
	/**
	 * attributes
	 */
	private String name;
	private String nick;
	//map containing converters to every compatible units
	//in compatibleList
	private Map<String, IUnitConverter> converterMap;
	private List<Unit> compatibleList;
	
	/**
	 * constructor
	 * 
	 * @param name
	 * 		unit name
	 * @param nick
	 * 		unit shortname
	 * @ensures this.name.equals(name) &&
	 * 			this.nick.equals(nick) &&
	 * 			converterMap != null &&
	 * 			compatibleList != null
	 */
	public Unit(String name, String nick) {
		this.name = name;
		this.nick = nick;
		this.converterMap = new HashMap<String, IUnitConverter>();
		this.compatibleList = new LinkedList<Unit>();
	}
	
	/**
	 * add a compatible unit
	 * 
	 * @param unit
	 * 		compatible unit to be added
	 * @return
	 * 		True if there is a converter to handle the conversion
	 * 		False if not
	 * @requires unit != null
	 */
	public boolean addCompatible(Unit unit) {
		boolean b = !compatibleWith(unit);
		String newUnitNick = unit.getNick();
		AbstractUnitConverter converter = UnitConverterFactory.getInstance().getUnitConverter(this.getNick(), newUnitNick);
		if (converter != null) {
			addToBoth(unit, converter);
		}
		return converter != null && b;
	}
	
	/**
	 * check compatibility with the give unit
	 * 
	 * @param unit
	 * 		unit to be considered
	 * @return
	 * 		True if is compatible
	 * 		False if not
	 */
	private boolean compatibleWith(Unit unit) {
		for(Unit u : this.compatibleList) {
			if (u.equals(unit))
				return true;
		}
		return false;
	}
	
	/**
	 * insert a unit and a converter into the
	 * respective lists
	 * 
	 * @param unit
	 * 		unit to be inserted
	 * @param converter
	 * 		converter to be inserted
	 * @requires unit != null
	 */
	private void addToBoth(Unit unit, AbstractUnitConverter converter) {
		this.converterMap.put(unit.getNick(), converter);
		this.compatibleList.add(unit);
		unit.converterMap.put(this.getNick(), converter);
		unit.compatibleList.add(this);
	}
	
	/**
	 * get nick
	 * 
	 * @return
	 * 		nick
	 */
	public String getNick(){
		return this.nick;
	}
	
	/**
	 * get name
	 * 
	 * @return
	 * 		name
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * get compatible units
	 * 
	 * @return
	 * 		all compatible units
	 */
	public Iterable<Unit> getCompatibleUnits(){
		return this.compatibleList;
	}

	/**
	 * convert the given value to the given unit
	 * 
	 * @param unit
	 * 		unit to be considered
	 * @param oldVal
	 * 		value to be converted
	 * @return
	 * 		converted value
	 * @requires unit != null
	 */
	public Double convertTo(Unit unit, Double oldVal) {
		Double result = null;
		IUnitConverter conv = converterMap.get(unit.getNick());
		if (conv != null)
			result = conv.convert(nick, unit.getNick(), oldVal);
		return result;
	}

	/**
	 * get unit converters
	 * 
	 * @return
	 * 		all known converters
	 */
	public Iterable<IUnitConverter> getConverters() {
		return converterMap.values();
	}
}

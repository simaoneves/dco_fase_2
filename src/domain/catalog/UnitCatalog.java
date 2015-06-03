package domain.catalog;

import java.util.LinkedHashMap;
import java.util.Map;

import domain.Unit;

/**
 * This class represents a catalog of units
 * 
 * @author Joao R. && Simao N. && Miguel V.
 * @author fc45582 && fc45681 && fc39279
 */
public class UnitCatalog {
	
	/**
	 * attributes
	 */
	private Map<String, Unit> unitMap;
	
	/**
	 * constructor
	 * 
	 * @ensures unitMap != null
	 */
	public UnitCatalog() {
		this.unitMap = new LinkedHashMap<String, Unit>();
	}
	
	/**
	 * get a unit by it's nick
	 * 
	 * @param nick
	 * 		nick to be considered
	 * @return
	 * 		Unit if unitMap.contains(nick)
	 * 		Null if !unitMap.contains(nick)
	 */
	public Unit getUnit(String nick) {
		return this.unitMap.get(nick);
	}
	
	/**
	 * add a unit
	 * 
	 * @param unit
	 * 		unit to be inserted
	 * @requires unit != null
	 */
	public void addUnit(Unit unit) {
		this.unitMap.put(unit.getNick(), unit);
	}
	
	/**
	 * get all units inside catalog
	 * 
	 * @return
	 * 		all available units
	 */
	public Iterable<String> unitsList() {
		return unitMap.keySet();
	}
}

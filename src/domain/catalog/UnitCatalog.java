package domain.catalog;

import java.util.Map;
import java.util.HashMap;

import domain.Unit;

/**
 * This class represents a catalog of units
 * 
 * @author Joao R. && Simao N.
 *
 */
public class UnitCatalog {
	
	/**
	 * attributes
	 */
	private Map<String, Unit> unitMap;
	
	/**
	 * constructor
	 */
	public UnitCatalog() {
		this.unitMap = new HashMap<String, Unit>();
		
		// TESTE
		addUnit(new Unit("metro", "abv"));
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

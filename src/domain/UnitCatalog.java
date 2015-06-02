package domain;

import java.util.Map;
import java.util.HashMap;

public class UnitCatalog {
	
	private Map<String, Unit> unitMap;
	
	public UnitCatalog() {
		this.unitMap = new HashMap<String, Unit>();
		
		// TESTE
		addUnit(new Unit("metro", "abv"));
	}
	
	public Unit getUnit(String nick) {
		return this.unitMap.get(nick);
	}
	
	public void addUnit(Unit unit) {
		this.unitMap.put(unit.getNick(), unit);
	}
	
	public Iterable<String> unitsList() {
		return unitMap.keySet();
	}
}

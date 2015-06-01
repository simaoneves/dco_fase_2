package domain;

import domain.AbstractUnitConverter;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

public class Unit {
	
	private String name;
	private String nick;
	private Map<String, AbstractUnitConverter> converterMap;
	private List<Unit> compatibleList;
	
	public Unit(String name, String nick) {
		this.name = name;
		this.nick = nick;
		this.converterMap = new HashMap<String, AbstractUnitConverter>();
		this.compatibleList = new LinkedList<Unit>();
	}
	
	public boolean addCompatible(Unit unit) {
		boolean b = !compatibleWith(unit);
		String newUnitNick = unit.getNick();
		AbstractUnitConverter converter = UnitConverterFactory.getInstance().getUnitConverter(this.getNick(), newUnitNick);
		if (converter != null)
			/// MAL
			addToBoth(unit, converter);
		return false;
	}
	
	private boolean compatibleWith(Unit unit) {
		return false;
	}
	
	private void addToBoth(Unit unit, AbstractUnitConverter converter) {
		converterMap.put(unit.getNick(), converter);
		compatibleList.add(unit);
		unit.addCompatible(this);
	}
	
	public String getNick(){
		return this.nick;
	}
	
	public String getName(){
		return this.name;
	}
}

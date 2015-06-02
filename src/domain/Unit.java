package domain;

import domain.converter.AbstractUnitConverter;
import domain.factory.UnitConverterFactory;
import domain.interfaces.IUnitConverter;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

public class Unit {
	
	private String name;
	private String nick;
	private Map<String, IUnitConverter> converterMap;
	private List<Unit> compatibleList;
	
	public Unit(String name, String nick) {
		this.name = name;
		this.nick = nick;
		this.converterMap = new HashMap<String, IUnitConverter>();
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
	
	public Iterator<Unit> getCompatibleUnits(){
		return this.compatibleList.iterator();
	}

	public Double convertTo(Unit unit, Double oldVal) {
		Double result = null;
		IUnitConverter conv = converterMap.get(unit.getNick());
		if (conv != null)
			result = conv.convert(nick, unit.getNick(), oldVal);
		return result;
	}
	
	public String toString() {
		return "[Name: " + this.name + "; Nick:" + this.nick + ";]";
	}
}

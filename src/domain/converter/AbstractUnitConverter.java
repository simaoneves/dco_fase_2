package domain.converter;

import java.util.LinkedList;
import java.util.List;

import domain.Pair;
import domain.interfaces.IUnitConverter;


public abstract class AbstractUnitConverter implements IUnitConverter{
	
	private List<Pair<String, String>> fromTo;
	
	public AbstractUnitConverter(){
		fromTo = new LinkedList<>();
	}
	
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
	public abstract Double convert(String fromNick, String toNick, Double oldVal);
	
	protected void add(Pair<String, String> par){
		this.fromTo.add(par);
	}
}

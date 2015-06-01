package domain;

import domain.interfaces.IUnitConverter;
import domain.Pair;

import java.util.List;


public abstract class AbstractUnitConverter implements IUnitConverter{
	
	private List<Pair<String, String>> fromTo;
	
	public boolean doYouConvert(String nick, String unitNick) {
		boolean result = false;
		for(Pair pair : fromTo) {
			if (pair.getFirst() == nick && pair.getSecond() == unitNick) {
				result = true;
				break;
			}
		}
		return result;
	}
	
}

package domain.factory;

import java.util.List;
import java.util.LinkedList;

import domain.converter.AbstractUnitConverter;
import domain.converter.KmMileConverter;

public class UnitConverterFactory {
	
	private List<AbstractUnitConverter> absUnitConverterList;
	private static UnitConverterFactory INSTANCE;
	
	private UnitConverterFactory() {
		this.absUnitConverterList = new LinkedList<AbstractUnitConverter>();
		this.absUnitConverterList.add(new KmMileConverter());
	}
	
	public static UnitConverterFactory getInstance() {
		if (INSTANCE == null)
			INSTANCE = new UnitConverterFactory();
		return INSTANCE;
	}
	
	public AbstractUnitConverter getUnitConverter(String nick, String unitNick) {
		for(AbstractUnitConverter conv : absUnitConverterList) {
			if (conv.doYouConvert(nick, unitNick)) {
				return conv;
			}
		}
		return null;
	}
	
}

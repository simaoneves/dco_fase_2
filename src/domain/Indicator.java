package domain;

import java.util.List;

public class Indicator {
	
	private String name;
	private Unit unit;
	
	public Indicator(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	public Unit getUnit() {
		return this.unit;
	}

	public void addObservations(List<Observation> observList, Unit observUnit) {
		
		if (observUnit != this.unit)
			convertObservations(observList, observUnit);
		
	}

	private void convertObservations(List<Observation> observList, Unit observUnit) {
		System.out.println(observUnit);
		for(Observation o : observList) {
			Double oldVal = o.getValue();
			Double newVal = observUnit.convertTo(this.unit, oldVal);
			o.setValue(newVal);
		}
	}

}

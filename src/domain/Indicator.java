package domain;

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

}

package domain;

import java.time.LocalDate;

public class Observation {
	

	private LocalDate localDate;
	private Double value;
	
	public Observation(LocalDate date, Double val) {
		this.localDate = localDate;
		this.value = val;
	}
	
	public Double getValue() {
		return this.value;
	}

	public void setValue(Double newVal) {
		this.value = newVal;
	}
}

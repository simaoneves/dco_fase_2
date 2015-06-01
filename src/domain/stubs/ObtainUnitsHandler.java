package domain.stubs;

import java.util.Arrays;

import domain.interfaces.IObtainUnitsHandler;

/**
 * An obtain units handler used for building the user interface
 * 
 * @author fmartins
 *
 */
public class ObtainUnitsHandler extends ObtainIndicatorsHandler 
				implements IObtainUnitsHandler {

	@Override
	public Iterable<String> getAllUnits() {
		System.out.println("ObtainUnitsHandler: getUnitNames()");
		return Arrays.asList("Km", "Kg", "Km/h", "Unidades");		
	}
}

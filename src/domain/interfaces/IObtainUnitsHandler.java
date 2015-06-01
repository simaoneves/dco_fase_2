package domain.interfaces;

/**
 * The interface with the obtain units sub-use case
 * Notice that this sub-case is used in multiple use cases
 * 
 * @author fmartins
 *
 */
public interface IObtainUnitsHandler extends IObtainIndicatorsHandler {

	
	/**
	 * @return The names of the units in the system
	 */
	Iterable<String> getAllUnits();

}

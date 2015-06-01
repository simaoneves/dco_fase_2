package domain.interfaces;

/**
 * The interface with the create indicator use case
 * 
 * @author fmartins
 *
 */
public interface ICreateIndicatorHandler extends IObtainUnitsHandler {

	/**
	 * starts the create indicator use case
	 */
	void newIndicator();

	/**
	 * Indicate the name and the mode of the indicator 
	 * 
	 * @param name The name of the indicator
	 * @param mode The mode of the indicator
	 * @return True if the name does not exist for the category and the mode is valid
     * @requires type.equals("MANUAL") || type.equals("AUTOMATIC")
	 */
	boolean supplyNameAndMode(String name, String mode);

	/**
	 * @return The name of the extensions
	 */
	Iterable<String> getDeviceNames();

	/** 
	 * Selects a device to provide the observations. Mandatory in 
	 * case the indicator's mode is AUTOMATIC
	 * 
	 * @param name The name of the device
	 */
	void selectDevice(String name);

	/**
	 * Selects the unit associated with the indicator. The unit will 
	 * be used to register observations.
	 * 
	 * @param value The unit associated with indicator
	 */
	void selectUnit(String value);

	/**
	 * Confirms the creation of the indicator
	 */
	void confirm();

	/**
	 * Abandons the use case
	 */
	void cancel();
}

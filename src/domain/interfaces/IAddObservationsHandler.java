package domain.interfaces;

/**
 * The interface with the add observations to an indicator use case
 * 
 * @author fmartins
 *
 */
public interface IAddObservationsHandler extends IObtainUnitsHandler {
    
    /**
     * Starts the use case
     */
    void initiateRegister();

    /**
     * Defines the current indicator and pave the way to 
     * read observations for this indicator.
     *
     * @param name Indicator name
     */
    void selectIndicator(String name);

    /**
     * Selects the units in which the observations of the indicator
     * will be stored.
     * 
     * @param name The abbreviation of the unit to be used for this indicator
     */
    void selectUnit(String name);

    /**
     * Records a new observation to the indicator.
     * Stores the information periodically until the confirm observations 
     * operation commits it.
     * 
     * @param year The year of the observation
     * @param month The month of the observation (1-12)
     * @param day The day of the observation (1-31)
     * @param value The observation reading
     */
    void newObservation(int year, int month, int day, double value);
    
	/**
	 * Confirms the addition of the observation
	 */
    int confirmObservations();

	/**
	 * Abandons the use case
	 */
	void cancel();
}

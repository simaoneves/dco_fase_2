package domain.interfaces;

/**
 * The interface with the create unit use case
 * 
 * @author fmartins
 *
 */
public interface ICreateUnitHandler {
    
    /**
     * Starts the use case
     */
    void newUnit();

	/**
	 * @return The list of all units in the system 
	 */
	Iterable<String> getAllUnits();
    
    /**
     * @param name The description of the unit
     * @param nick The abbreviation of the unit
     * @return true if the unit does not exists
     */
    boolean registerUnit(String name, String nick);
    
    /**
     * Adds a unit compatible with the unit of the indicator.
     * 
     * @param nick The abbreviation of the unit compatible with unit of 
     * the indicator
     * @return true if the unit does not exists or is already added
     */
    boolean addCompatibleUnit(String nick);

	/**
	 * Confirms the creation of the unit.
	 * It requires that the name and the nick has been registered, but
	 * it allows no compatible unit given.
	 */
    void endCreate();
    
	/**
	 * Abandons the use case
	 */
	void cancel();
}

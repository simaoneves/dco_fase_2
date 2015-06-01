package devices;

/**
 * A device adapter
 * 
 * @author Isabel Nunes
 *
 */
public interface IDeviceAdapter {
	
	/**
	 * @return The device name
	 */
	String getName();
	
    /**
     * @return A device pending observation
     */
    double getObservations();
}

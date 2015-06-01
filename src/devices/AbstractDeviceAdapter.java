package devices;

/**
 * An abstract device adapter implementation
 * Notice that the class does not implement the getObservations
 * method. It remains abstract and will be implemented by concrete
 * sub-classes of this class.
 * 
 * @author Isabel Nunes
 *
 */
public abstract class AbstractDeviceAdapter implements IDeviceAdapter {
    
	/**
	 * The name of the device 
	 */
	private String name;
    
	/**
	 * Constructs a device adapter given its name
	 * 
	 * @param name The name of the device adapter
	 */
	public AbstractDeviceAdapter (String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
}

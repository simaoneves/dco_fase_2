package domain;

import devices.IDeviceAdapter;

/**
 * This class represents a Automatic Indicator
 * 
 * @author Joao R. && Simao N.
 *
 */
public class Automatic extends Indicator{
	
	/**
	 * attributes
	 */
	private IDeviceAdapter IDeviceAdapter;
	
	/**
	 * constructor
	 * 
	 * @see Indicator#Indicator(String)		
	 */
	public Automatic(String name) {
		super(name);
	}
	
	/**
	 * set device associated to obs reading
	 * 
	 * @param device
	 * 		device to be considered
	 */
	public void setDevice(IDeviceAdapter device) {
		this.IDeviceAdapter = device;
	}

}

package domain;

import devices.IDeviceAdapter;

/**
 * This class represents a Automatic Indicator
 * 
 * @author Joao R. && Simao N. && Miguel V.
 * @author fc45582 && fc45681 && fc39279
 *
 */
public class Automatic extends Indicator{
	
	/**
	 * attributes
	 */
	@SuppressWarnings("unused")
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

package domain;

import devices.IDeviceAdapter;

public class Automatic extends Indicator{
	
	private IDeviceAdapter IDeviceAdapter;
	
	public Automatic(String name) {
		super(name);
	}
	
	public void setDevice(IDeviceAdapter device) {
		this.IDeviceAdapter = device;
	}

}

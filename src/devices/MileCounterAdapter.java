package devices;

/**
 * A mile counter adapter
 * 
 * @author Isabel Nunes
 *
 */
public class MileCounterAdapter extends AbstractDeviceAdapter {
    // attributes to connect to the concrete adapter
    
	public MileCounterAdapter () {
		super("MileCounter");
	}
	
	public double getObservations() {
		// the code to interact with the concrete device
		return 400;
	}

}

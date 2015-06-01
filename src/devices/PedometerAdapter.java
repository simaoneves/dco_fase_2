package devices;

/**
 * A pedometer adatper
 * 
 * @author Isabel Nunes
 *
 */
public class PedometerAdapter extends AbstractDeviceAdapter {
    // attributes to connect to the concrete adapter
    
	public PedometerAdapter () {
		super("Pedometer");
	}
	
	public double getObservations() {
		// the code to interact with the concrete device
		return 3.5;
	}
}

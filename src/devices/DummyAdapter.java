package devices;

/**
 * An example adatper

 * @author Isabel Nunes
 *
 */
public class DummyAdapter extends AbstractDeviceAdapter {
    // attributes to connect to the concrete adapter
    
	public DummyAdapter () {
		super("Dummy");
	}
	
	public double getObservations() {
		// the code to interact with the concrete device
		return 0;
	}

}

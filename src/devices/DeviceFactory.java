package devices;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

/**
 * A factory to create observation reader adapters 
 * Notice a different way of implementing a singleton. 
 * Confront with the services.SessionManager class.
 * 
 * @author Isabel Nunes
 *
 */
public enum DeviceFactory {
	INSTANCE;

	// the know device adapters
	private Map<String,IDeviceAdapter> devices;
	
	/**
	 * Constructs the factory loading the available device classes from
	 * folder bin/devices. The loader checks that the devices implement
	 * the AbstractDeviceAdapter interface. 
	 *
	 */
	private DeviceFactory() {
		devices = new HashMap<String, IDeviceAdapter> ();
		loadDevices();
	}
		
	/**
	 * Loads all classes from folder /bin/devices that descend from 
	 * AbstractDeviceAdapter and creates an instance of each one.
	 */
	private void loadDevices() {
		// add filters in the filters folder
		String separator = System.getProperty("file.separator");
		File filtersFolder = new File(System.getProperty("user.dir") + separator + "bin" + 
				separator + "devices");
		File [] classes = filtersFolder.listFiles(new FilenameFilter () {
			public boolean accept(File dir, String name) {
				return name.endsWith(".class");
			}
		});
		devices.put("Dummy", new DummyAdapter());
		for (File className : classes) {
			try {
				String s = className.getName();
				Class<?> deviceClass = 
					Class.forName("devices." + s.substring(0, s.lastIndexOf('.')));
		    	if (deviceClass.getGenericSuperclass() == AbstractDeviceAdapter.class &&
		    			!className.equals("Dummy")) {
					IDeviceAdapter device = (IDeviceAdapter) deviceClass.newInstance();
					devices.put( device.getName(), device);
				}		    
			} catch (Exception e) {
				// Do nothing! Just ignore the class;
			}
		}
	}
	
	/**
	 * Find a device adapter by name
	 * 
	 * @param name The name of the adapter to look for
	 * @requires name != null
	 * @return The adapter with the specified name, in case it exists; 
	 *         otherwise a DummyAdapter instance is returned
	 */
	public IDeviceAdapter getDeviceAdapter(String name) {
		try {
		    return devices.get(name);
		} catch (Exception e) {
			return devices.get("Dummy");
		}
	}

	/**
	 * @return The collection of available adapter names
	 */
	public Iterable<String> deviceAdaptersList() {
		System.out.println("TESTE" + devices);
		return devices.keySet();
	}
}

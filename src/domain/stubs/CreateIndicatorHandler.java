package domain.stubs;

import java.util.Arrays;

import domain.interfaces.ICreateIndicatorHandler;

/**
 * A create indicator handler used for building the user interface
 * 
 * @author fmartins
 *
 */
public class CreateIndicatorHandler extends ObtainUnitsHandler 
						implements ICreateIndicatorHandler {

	@Override
	public void newIndicator() {
		System.out.println("CreateIndicator: newIndicator()");
	}
			
	@Override 
	public boolean supplyNameAndMode(String name, String mode) {
		System.out.println("CreateIndicator: supplyNameAndMode(\"" + name + "\", \"" + mode + "\")");
		return !name.equals("fail");
	}
	
	@Override 
	public Iterable<String> getDeviceNames() {
		System.out.println("CreateIndicator: getDeviceNames()");
		return Arrays.asList("Samesungue heartbeat meter", "iFone GPS system");		
	}

	@Override
	public void selectDevice(String name) {
		System.out.println("CreateIndicator: selectDevice(\"" + name + "\")");
	}	

	@Override
	public void cancel() {
		System.out.println("CreateIndicator: cancel()");
	}

	@Override
	public void selectUnit(String name) {
		System.out.println("CreateIndicator: selectUnit(\"" + name + "\")");
	}

	@Override
	public void confirm() {
		System.out.println("CreateIndicator: confirm()");
	}
}

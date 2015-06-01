package startup;

import domain.interfaces.IDCO;
import domain.DCO;

/**
 * Executes the startup use case
 * 
 * @author fmartins
 *
 */
public class StartUp {
    
	/**
	 * Fire in the hole ;)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		IDCO app = new DCO();
		new view.StartUp(app);
	}

}

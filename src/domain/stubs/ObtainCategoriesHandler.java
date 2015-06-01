package domain.stubs;

import java.util.Arrays;

import domain.interfaces.IObtainCategoriesHandler;

/**
 * An obtain categories handler user for building the user interface
 * 
 * @author fmartins
 *
 */
public class ObtainCategoriesHandler implements IObtainCategoriesHandler {

	// This is a stub, so there is no need for attributes.
	// I guess you will need, maybe, the current user ;)
	
	@Override
	public Iterable<String> getCategoriesAuthenticatedUser() {
		System.out.println("ObtainCategories: getCategoriesAuthenticatedUser()");
		return Arrays.asList("Saúde","Desporto","Estudo");
	}
}

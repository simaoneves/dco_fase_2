package domain.stubs;

import java.util.Arrays;

import domain.interfaces.IObtainCategoriesHandler;

/**
 * An obtain categories handler used for building the user interface
 * 
 * @author fmartins
 *
 */
public class ObtainCategoriesHandlerStub implements IObtainCategoriesHandler {

	@Override
	public Iterable<String> getCategoriesAuthenticatedUser() {
		System.out.println("ObtainCategories: getCategoriesAuthenticatedUser()");
		return Arrays.asList("Saúde","Desporto","Estudo");
	}


}

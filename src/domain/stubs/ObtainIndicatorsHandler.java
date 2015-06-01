package domain.stubs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.interfaces.IObtainIndicatorsHandler;

/**
 * An obtained indicators handler used for building the user interface
 * 
 * @author fmartins
 *
 */
public class ObtainIndicatorsHandler extends ObtainCategoriesHandler 
							implements IObtainIndicatorsHandler {

	private Map<String, List<String>> indicatorsByCategory;
	private String selectedCategory;
	
	public ObtainIndicatorsHandler() {
		indicatorsByCategory = new HashMap<>();
		indicatorsByCategory.put("Saúde", Arrays.asList("Peso", "Glicémia", "Batimento Cardiaco"));
		indicatorsByCategory.put("Desporto", Arrays.asList("Km percorridos", "Tempo nas máquinas"));		
		indicatorsByCategory.put("Estudo", Arrays.asList("Páginas lidas", "Tempo de estudo"));		
	}
	
	@Override
	public void selectCategory(String name) {
		System.out.println("ObtainIndicators: selectCategory(\"" + name + "\")");
		selectedCategory = name;
	}

	@Override
	public Iterable<String> getIndicatorsAuthenticatedUser() {
		System.out.println("ObtainIndicators: getIndicatorsAuthenticatedUser()");
		if (!indicatorsByCategory.containsKey(selectedCategory))
			return new ArrayList<>(0);
		else
			return indicatorsByCategory.get(selectedCategory);
	}
}

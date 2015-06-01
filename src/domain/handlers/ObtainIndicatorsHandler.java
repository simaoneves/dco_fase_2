package domain.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import domain.Indicator;
import domain.User;
import domain.Category;

import java.util.LinkedList;

import domain.interfaces.IObtainIndicatorsHandler;

/**
 * An obtained indicators handler used for building the user interface
 * 
 * @author fmartins
 *
 */
public class ObtainIndicatorsHandler extends ObtainCategoriesHandler 
							implements IObtainIndicatorsHandler {

	protected Map<String, List<String>> indicatorsByCategory;
	private String selectedCategory;
	protected Category currentCat;
	
	public ObtainIndicatorsHandler(User authenticatedUser) {
		super(authenticatedUser);
		indicatorsByCategory = new HashMap<>();		
	}
	
	@Override
	public void selectCategory(String name) {
		System.out.println("ObtainIndicators: selectCategory(\"" + name + "\")");
		this.selectedCategory = name;
		// MALLLLL
		indicatorsByCategory.put(name, createIndicatorsList(name));
	}
	
	public LinkedList<String> createIndicatorsList(String catId) {
		this.currentCat = this.currentUser.getCategory(catId);	
		LinkedList<String> indicatorsList = new LinkedList<String>();
		Iterator<Indicator> categoryIndicatorsIterator = currentCat.getIndicators();
		
		/// MALLLLLLLLL
		preencheIndicatorNames(categoryIndicatorsIterator, indicatorsList);
		return indicatorsList;
	}
	
	// MALLLLLLLLL
	private void preencheIndicatorNames(Iterator<Indicator> categoryIndicatorsIterator, LinkedList<String> indicatorsList) {
		while (categoryIndicatorsIterator.hasNext()) {
			Indicator i = categoryIndicatorsIterator.next();
			String name = i.getName();
			indicatorsList.add(name);
		}
		
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

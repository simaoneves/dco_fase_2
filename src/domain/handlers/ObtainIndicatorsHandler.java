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

import domain.interfaces.ICreateIndicatorHandler;
import domain.interfaces.IObtainIndicatorsHandler;

/**
 * An obtained indicators handler used for building the user interface
 * 
 * @author Joao R. && Simao N. && Miguel V.
 * @author fc45582 && fc45681 && fc39279
 *
 */
public class ObtainIndicatorsHandler extends ObtainCategoriesHandler 
							implements IObtainIndicatorsHandler {

	/**
	 * attributes
	 */
	protected Map<String, List<String>> indicatorsByCategory;
	private String selectedCategory;
	protected Category currentCat;
	
	/**
	 * constructor
	 * 
	 * @param authenticatedUser
	 * @see ObtainCategoriesHandler#ObtainCategoriesHandler(User)
	 * @ensures indicatorsByCategory != null
	 */
	public ObtainIndicatorsHandler(User authenticatedUser) {
		super(authenticatedUser);
		indicatorsByCategory = new HashMap<>();		
	}
	
	/**
	 * @see IObtainIndicatorsHandler#selectCategory(String)
	 * @ensures selectedCategory.equals(name)
	 */
	@Override
	public void selectCategory(String name) {
		//System.out.println("ObtainIndicators: selectCategory(\"" + name + "\")");
		this.selectedCategory = name;
		
		indicatorsByCategory.put(name, createIndicatorsList(name));
	}
	
	/**
	 * get all indicators of the received category name
	 * 
	 * @param catId
	 * 			category name to be considered
	 * @return
	 * 			all category indicators
	 */
	public LinkedList<String> createIndicatorsList(String catId) {
		this.currentCat = this.currentUser.getCategory(catId);	
		LinkedList<String> indicatorsList = new LinkedList<String>();
		Iterator<Indicator> categoryIndicators = currentCat.getIndicators();
		
		
		preencheIndicatorNames(categoryIndicators, indicatorsList);
		return indicatorsList;
	}
	
	private <T extends Iterator<Indicator>> void preencheIndicatorNames(T categoryIndicatorsIterator, LinkedList<String> indicatorsList) {
		while (categoryIndicatorsIterator.hasNext()) {
			Indicator i = categoryIndicatorsIterator.next();
			String name = i.getName();
			indicatorsList.add(name);
		}
		
	}

	/**
	 * get indicators by given category name
	 * 
	 * @see ICreateIndicatorHandler#getIndicatorsAuthenticatedUser()
	 */
	@Override
	public Iterable<String> getIndicatorsAuthenticatedUser() {
		//System.out.println("ObtainIndicators: getIndicatorsAuthenticatedUser()");
		if (!indicatorsByCategory.containsKey(selectedCategory))
			return new ArrayList<>(0);
		else
			return indicatorsByCategory.get(selectedCategory);
	}
}

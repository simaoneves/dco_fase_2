package domain;

/**
 * This class represents a pair
 * 
 * @author Joao R. && Simao N.
 *
 * @param <E>
 * @param <F>
 */
public class Pair<E, F> {
	
	/**
	 * attributes
	 */
	private E e;
	private F f;
	
	/**
	 * get the first element of pair
	 * 
	 * @return
	 * 		first elem of pair
	 */
	public E getFirst() {
		return e;
	}
	
	/**
	 * get the second element of pair
	 * 
	 * @return
	 * 		second elem of pair
	 */
	public F getSecond() {
		return f;
	}

}

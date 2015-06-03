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
	private E fst;
	private F snd;
	

	/**
	 * Constructor
	 * 
	 * @param fst
	 * @param snd
	 */
	public Pair(E fst, F snd){
		this.fst = fst;
		this.snd = snd;
	}
	
	/**
	 * get the first element of pair
	 * 
	 * @return
	 * 		first elem of pair
	 */
	public E getFirst() {
		return this.fst;
	}
	
	/**
	 * get the second element of pair
	 * 
	 * @return
	 * 		second elem of pair
	 */
	public F getSecond() {
		return this.snd;
	}

}

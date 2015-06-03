package domain;

/**
 * This class represents a pair
 * 
 * @author Joao R. && Simao N. && Miguel V.
 * @author fc45582 && fc45681 && fc39279
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
	 * 		first element of pair
	 * @param snd
	 * 		second element of pair
	 */
	public Pair(E fst, F snd){
		this.fst = fst;
		this.snd = snd;
	}
	
	/**
	 * get the first element of pair
	 * 
	 * @return
	 * 		first element of pair
	 */
	public E getFirst() {
		return this.fst;
	}
	
	/**
	 * get the second element of pair
	 * 
	 * @return
	 * 		second element of pair
	 */
	public F getSecond() {
		return this.snd;
	}

}

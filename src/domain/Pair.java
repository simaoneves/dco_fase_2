package domain;

public class Pair<E, F> {
	
	private E e;
	private F f;
	
	public Pair(E fst, F snd){
		this.e = fst;
		this.f = snd;
	}
	
	public E getFirst() {
		return e;
	}
	
	public F getSecond() {
		return f;
	}

}

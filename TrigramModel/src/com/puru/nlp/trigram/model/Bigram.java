package com.puru.nlp.trigram.model;

public class Bigram {

	public Bigram(WordPair pair, double q_current) {

		this.prev = new Word(pair.getPrev());
		this.current = new Word(pair.getCurrent());
		this.q_current = q_current;
	}

	private Word prev;

	private Word current;

	private double q_current;

	public Word getPrev() {
		return prev;
	}

	public void setPrev(Word prev) {
		this.prev = prev;
	}

	public Word getCurrent() {
		return current;
	}

	public void setCurrent(Word current) {
		this.current = current;
	}

	public double getQ_current() {
		return q_current;
	}

	public void setQ_current(double q_current) {
		this.q_current = q_current;
	}

	@Override
	public String toString() {

		return current+" = " + prev + " = " + q_current;
	}

}

package com.puru.nlp.trigram.model;

public class Trigram {

	public Trigram(WordTrio trio, double q_current) {

		this.doublePrev = new Word(trio.getDoublePrev());
		this.prev = new Word(trio.getPrev());
		this.current = new Word(trio.getCurrent());
		this.q_current = q_current;
	}

	private Word prev;

	private Word current;

	private Word doublePrev;

	public Word getDoublePrev() {
		return doublePrev;
	}

	public void setDoublePrev(Word doublePrev) {
		this.doublePrev = doublePrev;
	}

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

		return current + " = " + prev + " = " + q_current;
	}

}

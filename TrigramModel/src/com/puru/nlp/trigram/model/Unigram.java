package com.puru.nlp.trigram.model;

public class Unigram {

	private Word u;

	private double p_u;

	public Unigram(Word u, double p_u) {

		this.u = u;
		this.p_u = p_u;
	}

	public double getP_u() {
		return p_u;
	}

	public void setP_u(double p_u) {
		this.p_u = p_u;
	}

	public Word getU() {
		return u;
	}

	public void setU(Word u) {
		this.u = u;
	}

	@Override
	public String toString() {

		return "[Word= " + this.u + ", p(u)= " + this.p_u + "]";
	}

}

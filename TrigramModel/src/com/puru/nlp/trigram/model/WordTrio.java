package com.puru.nlp.trigram.model;

public class WordTrio {

	private String doublePrev;
	/**
	 * Current word in the sequence i.
	 */
	private String current;
	/**
	 * Previous word in the sequence. i-1
	 */
	private String prev;

	public WordTrio(String doublePrev, String prev, String current) {
		this.doublePrev = doublePrev;
		this.prev = prev;
		this.current = current;
	}

	@Override
	public boolean equals(Object obj) {

		/** Bloch,J - Effective Java pp33 pt.1 */
		if (obj == this) {
			return true;
		}
		/** Bloch,J - Effective Java pp33 pt.2 */
		if (!(obj instanceof WordTrio)) {
			return false;
		}
		/** Bloch,J - Effective Java pp33 pt.3 */
		WordTrio compareTo = (WordTrio) obj;
		/** Bloch,J - Effective Java pp33 pt.4 */
		return (this.doublePrev.equals(compareTo.getDoublePrev()))
				&& (this.prev.equals(compareTo.getPrev()))
				&& (this.current.equals(compareTo.getCurrent()));
	}

	public String getCurrent() {
		return current;
	}

	public String getPrev() {
		return prev;
	}

	@Override
	public int hashCode() {

		/** Bloch,J - Effective Java pp37 pt.1 */
		int result = 17; // guarenteed to be prime ;D

		result = result * 37 + this.getPrev().hashCode();
		result = result * 37 + this.getCurrent().hashCode();
		result = result * 37 + this.getDoublePrev().hashCode();

		return result;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public void setPrev(String prev) {
		this.prev = prev;
	}

	@Override
	public String toString() {

		return "[ " + doublePrev + ", " + prev + ", " + current + " ]";
	}

	public String getDoublePrev() {
		return doublePrev;
	}

	public void setDoublePrev(String doublePrev) {
		this.doublePrev = doublePrev;
	}

}

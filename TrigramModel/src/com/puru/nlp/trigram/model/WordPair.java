package com.puru.nlp.trigram.model;

/**
 * Provides a String pair. Hashsafe.
 * 
 * @author guruguha
 * 
 */
public class WordPair {

	/**
	 * Current word in the sequence i.
	 */
	private String current;
	/**
	 * Previous word in the sequence. i-1
	 */
	private String prev;

	public WordPair(String prev, String current) {

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
		if (!(obj instanceof WordPair)) {
			return false;
		}
		/** Bloch,J - Effective Java pp33 pt.3 */
		WordPair compareTo = (WordPair) obj;
		/** Bloch,J - Effective Java pp33 pt.4 */
		return (this.prev.equals(compareTo.getPrev()))
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

		return "[ " + prev + ", " + current + " ]";
	}
}

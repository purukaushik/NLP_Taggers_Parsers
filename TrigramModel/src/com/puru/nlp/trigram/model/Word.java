package com.puru.nlp.trigram.model;

/**
 * Wraps around a String word.
 * Provides hashsafe string implementation.
 * 
 * @author guruguha
 * 
 */
public class Word {

	private String word;

	public Word(String word) {

		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public boolean equals(Object obj) {

		/** Bloch,J - Effective Java pp33 pt.1 */
		if (obj == this) {
			return true;
		}
		/** Bloch,J - Effective Java pp33 pt.2 */
		if (!(obj instanceof Word)) {
			return false;
		}
		/** Bloch,J - Effective Java pp33 pt.3 */
		Word compareTo = (Word) obj;
		/** Bloch,J - Effective Java pp33 pt.4 */
		return this.word.equals(compareTo.getWord());
	}

	@Override
	public int hashCode() {

		/** Bloch,J - Effective Java pp37 pt.1 */
		int result = 17; // guarenteed to be prime ;D

		result = result * 37 + this.getWord().hashCode();

		return result;
	}
	@Override
	public String toString() {

		return this.getWord();
	}
}

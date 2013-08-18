package com.puru.nlp.ne.tagging.model;

/**
 * Provides a String pair. Hashsafe.
 * 
 * @author guruguha
 * 
 */
public class WordTagPair {

	/**
	 * Tag for the word in the sequence Yi.
	 */
	private Tag tag;
	/**
	 * Word in the sequence Xi.
	 */
	private String word;

	public WordTagPair(String tag, String word) {

		this.tag = Tag.getValue(tag);
		this.word = word;
	}

	@Override
	public boolean equals(Object obj) {

		/** Bloch,J - Effective Java pp33 pt.1 */
		if (obj == this) {
			return true;
		}
		/** Bloch,J - Effective Java pp33 pt.2 */
		if (!(obj instanceof WordTagPair)) {
			return false;
		}
		/** Bloch,J - Effective Java pp33 pt.3 */
		WordTagPair compareTo = (WordTagPair) obj;
		/** Bloch,J - Effective Java pp33 pt.4 */
		return (this.tag.equals(compareTo.getTag()))
				&& (this.word.equals(compareTo.getWord()));
	}

	public Tag getTag() {
		return tag;
	}

	public String getWord() {
		return word;
	}

	@Override
	public int hashCode() {

		/** Bloch,J - Effective Java pp37 pt.1 */
		int result = 17; // guarenteed to be prime ;D

		result = result * 37 + this.getTag().hashCode();
		result = result * 37 + this.getWord().hashCode();

		return result;
	}

	public void setTag(String tag) {
		this.tag = Tag.getValue(tag);
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public String toString() {

		return "[" + word + ", " + tag + "]";
	}
}

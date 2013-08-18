package com.puru.nlp.ne.tagging.algo;

import com.puru.nlp.ne.tagging.model.Tag;

public class JunkishTagStore implements Comparable<JunkishTagStore> {

	private Tag matchingTag = null;

	private double value = 0;

	public JunkishTagStore(double value, Tag matchingTag) {

		this.value = value;
		this.matchingTag = matchingTag;
	}

	@Override
	public int compareTo(JunkishTagStore o) {

		if (o.getValue() > this.value) {

			return -1;
		} else if (this.value > o.getValue()) {

			return 1;
		} else if (this.value == o.getValue()) {

			return 0;
		}

		return 0;
	}

	public Tag getMatchingTag() {
		return matchingTag;
	}

	public double getValue() {
		return value;
	}

	@Override
	public String toString() {

		return "[  " + matchingTag + ", " + value + " ]";
	}
}

package com.puru.nlp.ne.tagging.model;

public enum Tag {

	I_GENE("I-GENE"), O("O");

	private String value;

	private Tag(String value) {
		this.value = value;
	}

	@Override
	public String toString() {

		return value;
	}

	public static Tag getValue(String value) {
		return valueOf(value.replaceAll("-", "_"));
	}
}

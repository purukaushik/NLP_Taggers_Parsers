package com.puru.nlp.ne.tagging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puru.nlp.ne.tagging.model.WordTagPair;
import com.puru.nlp.ne.tagging.utils.AnnotatedExtractor;
import com.puru.nlp.trigram.model.Word;
import com.puru.nlp.trigram.model.WordPair;
import com.puru.nlp.trigram.model.WordTrio;

public class AnnotatedAssimilator {

	/**
	 * Count(Y_i, Y_i_1, Y_i_2)
	 */
	private Map<WordTrio, Integer> yTrioMap = new HashMap<WordTrio, Integer>();

	/**
	 * Count(X_i)
	 */
	private Map<Word, Integer> wordXMap = new HashMap<Word, Integer>();

	/**
	 * Count(X_i,Y_i).
	 */
	private Map<WordTagPair, Integer> xyMap = new HashMap<WordTagPair, Integer>();

	/**
	 * Count(Y_i)
	 */
	private Map<Word, Integer> yMap = new HashMap<Word, Integer>();

	/**
	 * Count(Y_i,Y_i_1)
	 */
	private Map<WordPair, Integer> yPairsMap = new HashMap<WordPair, Integer>();

	public AnnotatedAssimilator(List<WordTagPair> pairs) {
		AnnotatedExtractor.extractFromPairs(yMap, wordXMap, yTrioMap, xyMap,
				pairs, yPairsMap);
	}

	public Map<WordTrio, Integer> getYTrioMap() {
		return yTrioMap;
	}

	public Map<Word, Integer> getWordXMap() {
		return wordXMap;
	}

	// /**
	// * Word Count of all unigram.
	// */
	// private int wordCount;

	public Map<WordTagPair, Integer> getXYMap() {
		return xyMap;
	}

	public Map<Word, Integer> getYMap() {
		return yMap;
	}

	public Map<WordPair, Integer> getYPairsMap() {
		return yPairsMap;
	}

}

package com.puru.nlp.ne.tagging.utils;

import java.util.List;
import java.util.Map;

import com.puru.nlp.ne.tagging.model.WordTagPair;
import com.puru.nlp.trigram.model.Word;
import com.puru.nlp.trigram.model.WordPair;
import com.puru.nlp.trigram.model.WordTrio;

public class AnnotatedExtractor {

	// private static final String SYMBOL_PATTERN = "[\\-\\.;:\"\\(\\)\\{\\}]";

	public static void extractFromPairs(Map<Word, Integer> wordYMap,
			Map<Word, Integer> wordXMap, Map<WordTrio, Integer> wordTrioMap,
			Map<WordTagPair, Integer> wordXYMap, List<WordTagPair> pairs,
			Map<WordPair, Integer> wordYPairs) {

		int wordCount = 0;

		String doublePrev = "*";

		String prev = "*";

		for (WordTagPair pair : pairs) {

			String current = "";

			/* X Unigram count */
			wordCount = extractAsUnigramCount(wordXMap, wordCount,
					pair.getWord());
			/* Y Unigram count */
			int sink = extractAsUnigramCount(wordYMap, wordCount, pair.getTag()
					.toString());

			WordPair pair2 = new WordPair(prev, pair.getTag().toString());

			extractAsBigramCount(wordXYMap, pair);

			extractAsBigramCount(wordYPairs, pair2);
			/**
			 * BEGIN: y trigram processing.
			 * */
			String y = pair.getTag().toString();

			current = y;

			WordTrio trio = new WordTrio(doublePrev, prev, current);

			if (wordTrioMap.containsKey(trio)) {

				int count = (Integer) wordTrioMap.get(trio);

				count++;

				wordTrioMap.put(trio, count);

			} else {

				wordTrioMap.put(trio, 1);
			}

			doublePrev = prev;
			prev = y;
			/**
			 * END: y trigram processing.
			 * */

		}

	}

	private static void extractAsBigramCount(Map<WordPair, Integer> wordYPairs,
			WordPair pair2) {

		int count = 1;

		if (wordYPairs.containsKey(pair2)) {
			count = wordYPairs.get(pair2);
			count++;

		}
		wordYPairs.put(pair2, count);

	}

	private static void extractAsBigramCount(
			Map<WordTagPair, Integer> wordXYMap, WordTagPair pair) {

		int count = 1;
		if (wordXYMap.containsKey(pair)) {
			count = wordXYMap.get(pair);
			count++;

		}
		wordXYMap.put(pair, count);

	}

	protected static int extractAsUnigramCount(Map<Word, Integer> wordMap,
			int wordCount, String wordOrTag) {
		/**
		 * BEGIN: "x/y as unigram" counts.
		 * */

		Word key = new Word(wordOrTag);

		if (wordMap.containsKey(key)) {

			int count = (Integer) wordMap.get(key);

			count++;

			wordMap.put(key, count);

		} else {

			wordMap.put(key, 1);
		}
		wordCount++;
		/**
		 * END: "x/y as unigram" counts.
		 * */
		return wordCount;
	}
}

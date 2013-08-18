package com.puru.nlp.trigram.vocab.utils;

import java.util.Map;

import com.puru.nlp.trigram.model.Word;
import com.puru.nlp.trigram.model.WordPair;
import com.puru.nlp.trigram.model.WordTrio;

public class WordExtractor {

	private static final String SYMBOL_PATTERN = "[\\-\\.;:\"\\(\\)\\{\\}]";

	public static int extractWordsIntoMap(Map<Word, Integer> wordMap,
			String[] sentences) {

		int wordCount = 0;

		for (String sen : sentences) {

			sen = sen.replaceAll(SYMBOL_PATTERN, " ");

			String[] cutout = sen.split("\\b");

			for (String cut : cutout) {

				cut = cut.trim();

				if (cut.length() == 0) {
					continue;
				}

				cut = cut.toLowerCase();
				// System.out.println(cut);
				Word key = new Word(cut);

				if (wordMap.containsKey(key)) {

					int count = (Integer) wordMap.get(key);

					count++;

					wordMap.put(key, count);

				} else {

					wordMap.put(key, 1);
				}
				wordCount++;
			}

		}
		int starts = sentences.length;
		wordMap.put(new Word("*"), starts);
		return wordCount + starts;
	}

	public static void extractWordPairsIntoMap(
			Map<WordPair, Integer> wordPairMap, String[] sentences) {

		for (String sen : sentences) {

			sen = sen.replaceAll(SYMBOL_PATTERN, " ");

			String[] cutout = sen.split("\\b");

			String prev = "*";

			String current = "";

			for (String cut : cutout) {

				cut = cut.trim();

				if (cut.length() == 0) {

					continue;

				}

				cut = cut.toLowerCase();

				current = cut;

				WordPair pair = new WordPair(prev, current);

				if (wordPairMap.containsKey(pair)) {

					int count = (Integer) wordPairMap.get(pair);

					count++;

					wordPairMap.put(pair, count);

				} else {

					wordPairMap.put(pair, 1);
				}

				// System.out.println(pair);
				prev = cut;

			}

		}
		int starts = sentences.length;
		wordPairMap.put(new WordPair("*", "*"), starts);
	}

	public static void extractWordTriosIntoMap(
			Map<WordTrio, Integer> wordTrioMap, String[] sentences) {

		for (String sen : sentences) {

			sen = sen.replaceAll(SYMBOL_PATTERN, " ");

			String[] cutout = sen.split("\\b");

			String doublePrev = "*";

			String prev = "*";

			String current = "";

			for (String cut : cutout) {

				cut = cut.trim();

				if (cut.length() == 0) {

					continue;

				}

				cut = cut.toLowerCase();

				current = cut;

				WordTrio trio = new WordTrio(doublePrev, prev, current);

				if (wordTrioMap.containsKey(trio)) {

					int count = (Integer) wordTrioMap.get(trio);

					count++;

					wordTrioMap.put(trio, count);

				} else {

					wordTrioMap.put(trio, 1);
				}

				// System.out.println(pair);
				doublePrev = prev;
				prev = cut;

			}

		}

	}

}

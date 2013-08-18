package com.puru.nlp.trigram.algo;

import java.util.Map;
import java.util.Map.Entry;

import com.puru.nlp.trigram.Assimilator;
import com.puru.nlp.trigram.model.Word;
import com.puru.nlp.trigram.model.WordPair;
import com.puru.nlp.trigram.model.WordTrio;

public class TrigramEstimator {

	public static double bigramEstimate(String sentence, int wordCount,
			Map<Word, Integer> wordMap, Map<WordPair, Integer> wordPairMap,
			EstimationType estimationType) {

		double discount = 0.5;
		// AN Unreasonable count: 1 / (wordCount + 0.0d);
		double alpha = computeAlpha(discount, wordMap, wordCount);

		double pdt_q = 1d;

		// double lambda1 = 1 / (3 + 0.0d);
		//
		// double lambda2 = 1 / (3 + 0.0d);
		//
		// double lambda3 = 1 / (3 + 0.0d);

		String prev = "*";

		String[] sentences = sentence.split("\\b");

		for (String current : sentences) {

			if (current.trim().length() == 0) {
				continue;
			}

			current = current.toLowerCase();

			Word word = new Word(prev);

			WordPair pair = new WordPair(prev, current);

			if (!(wordMap.containsKey(word))
					|| !(wordPairMap.containsKey(pair))) {

				if (estimationType.equals(EstimationType.DISCOUNTED)) {
					Word currentWord = new Word(current);
					int unigramCount = wordMap.get(currentWord);
					pdt_q = pdt_q * (alpha * unigramCount / wordCount);
				} else if (estimationType
						.equals(EstimationType.LINEAR_INTERPOLATED)) {
					// TODO: TRICKY BIT
				}

				prev = current;
				continue;
			}

			int unigramCount = wordMap.get(word);

			int bigramCount = wordPairMap.get(pair);

			double q = 0.0d;

			switch (estimationType) {
				case NAIVE :
					q = bigramCount / (unigramCount + 0.0d);
					break;
				case DISCOUNTED :
					q = (bigramCount - discount) / (unigramCount + 0.0d);
					break;
				default :
					break;
			}

			pdt_q = pdt_q * q;

			prev = current;

		}
		return pdt_q;
	}

	public static double trigramEstimate(String sentence,
			Assimilator assimilator, EstimationType estimationType) {

		Map<Word, Integer> wordMap = assimilator.getWordMap();
		Map<WordPair, Integer> wordPairMap = assimilator.getWordPairMap();
		Map<WordTrio, Integer> wordTrioMap = assimilator.getWordTrioMap();
		int wordCount = assimilator.getWordCount();

		double discount = 0.5;
		// An Unreasonable count: 1 / (wordCount + 0.0d);
		double alpha = computeAlpha(discount, wordMap, wordCount);

		double pdt_q = 1d;

		String prev = "*";

		String doublePrev = "*";

		String[] sentences = sentence.split("\\b");

		for (String current : sentences) {

			if (current.trim().length() == 0) {
				continue;
			}

			current = current.toLowerCase();

			WordPair pair = new WordPair(doublePrev, prev);

			WordTrio trio = new WordTrio(doublePrev, prev, current);

			if (!(wordPairMap.containsKey(pair))
					|| !(wordTrioMap.containsKey(trio))) {

				if (estimationType.equals(EstimationType.DISCOUNTED)) {
					// TODO:Find out how to do a discount over a trigram
					/*
					 * WordPair currentWordPair = new WordPair(prev, current);
					 * int unigramCount = wordMap.get(currentWord); pdt_q =
					 * pdt_q * (alpha * unigramCount / wordCount);
					 */
				}
				// System.out.println("TONG!");
				doublePrev = prev;
				prev = current;

				pdt_q = pdt_q * 1 / (wordCount + 0.0d);
				continue;
			}

			int bigramCount = wordPairMap.get(pair);

			int trigramCount = wordTrioMap.get(trio);

			double q = 0.0d;

			switch (estimationType) {
				case NAIVE :
					q = trigramCount / (bigramCount + 0.0d);
					break;
				case DISCOUNTED :
					/*
					 * TODO:q = (trigramCount - discount) / (bigramCount +
					 * 0.0d);
					 */
					break;
				default :
					break;
			}

			pdt_q = pdt_q * q;
			// System.out.println(wordPairMap.get(pair) + " & "
			// + wordTrioMap.get(trio) + " = " + q);
			doublePrev = prev;
			prev = current;

		}
		return pdt_q;

	}
	private static double computeAlpha(double discount,
			Map<Word, Integer> wordMap, int wordCount) {
		double q = 0;
		for (Entry<Word, Integer> word : wordMap.entrySet()) {
			if (word.getKey() == null) {
				continue;
			}
			q = q + ((word.getValue() + 0.0d - discount) / wordCount);

		}
		// System.out.println(q);
		return 1 - q;
	}
}

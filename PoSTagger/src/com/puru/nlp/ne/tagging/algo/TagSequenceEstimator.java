package com.puru.nlp.ne.tagging.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.puru.nlp.ne.tagging.AnnotatedAssimilator;
import com.puru.nlp.ne.tagging.model.Tag;
import com.puru.nlp.ne.tagging.model.WordTagPair;
import com.puru.nlp.trigram.model.Word;
import com.puru.nlp.trigram.model.WordPair;
import com.puru.nlp.trigram.model.WordTrio;

public class TagSequenceEstimator {

	public static List<WordTagPair> spitBestTagSequence(List<String> sentence,
			AnnotatedAssimilator assimilator) {

		List<WordTagPair> result = new ArrayList<WordTagPair>();

		String doublePrev = "*";
		String prev = "*";

		/* pi(0,*,*) = pi(-1,*,*) =1. pi(1,*,*) is where we start. */
		double pi_kuv = 1;

		double q_yi = 1;

		double e_xy = 1;

		double p_final = 1;

		for (String word : sentence) {

			List<JunkishTagStore> listOfQObjPairs = new ArrayList<JunkishTagStore>();

			for (Tag tag : Tag.values()) {

				/**
				 * Three parameters:
				 * <ol>
				 * <li>1. pi(0,*,*) =1;</li>
				 * <li>2. q(tag|*,*) = trigram probability of tag given *,*</li>
				 * <li>3. e(x|tag) = count(bigram tag,x)/count(tag) ;</li>
				 * </ol>
				 */

				/* 2. */

				// pi_kuv = p_final;// whatever we computer just before this
				// one.

				q_yi = getTrigramEstimate(tag.toString(), doublePrev, prev,
						assimilator);

				e_xy = getPairEstimate(tag.toString(), word, assimilator);

				p_final = pi_kuv * q_yi * e_xy;

				JunkishTagStore junkishObjectStore = new JunkishTagStore(
						p_final, tag);

				listOfQObjPairs.add(junkishObjectStore);

			}

			// System.out.println("before sort");
			// System.out.println(listOfQObjPairs);

			Collections.sort(listOfQObjPairs);

			Collections.reverse(listOfQObjPairs);

			WordTagPair pair = new WordTagPair(listOfQObjPairs.get(0)
					.getMatchingTag().toString(), word);

			result.add(pair);

			pi_kuv = p_final;

			doublePrev = prev;
			prev = pair.getTag().toString();
			// System.out.println("after sort");
			// System.out.println(listOfQObjPairs);
			//
			// System.out.println("magic resutl is: ");
			// System.out.println(listOfQObjPairs.get(0));

		}

		return result;
	}

	private static double getPairEstimate(String tag, String word,
			AnnotatedAssimilator assimilator) {

		WordTagPair wordTagPair = new WordTagPair(tag, word);

		Map<WordTagPair, Integer> xyMap = assimilator.getXYMap();

		Map<Word, Integer> yMap = assimilator.getYMap();

		if (!(xyMap.containsKey(wordTagPair))) {
			// NAIVE
			// System.out.println("Naived - " + wordTagPair);
			return 1 / (xyMap.size() + 0.0d);
		}

		int countXY = xyMap.get(wordTagPair);

		int countY = yMap.get(new Word(tag));

		return (countXY / (countY + 0.0d));
	}

	public static double getTrigramEstimate(String current, String doublePrev,
			String prev, AnnotatedAssimilator annotatedAssimilator) {

		Map<WordPair, Integer> yPairMap = annotatedAssimilator.getYPairsMap();

		Map<WordTrio, Integer> yTrioMap = annotatedAssimilator.getYTrioMap();

		WordTrio trio = new WordTrio(doublePrev, prev, current);

		WordPair tagPair = new WordPair(prev, current);

		if (!(yPairMap.containsKey(tagPair) && yTrioMap.containsKey(trio))) {
			/*
			 * Ideally we would never reach here as we would know all tags
			 * prior, and the combinations must work out in such a way that we
			 * always have a count.
			 */
			// System.out.println(tagPair
			// + " - The muntam has come here. Saniyan!");
			return 0;

		}

		int bigramCount = yPairMap.get(tagPair);

		int trigramCount = 0;

		try {
			trigramCount = yTrioMap.get(trio);

		} catch (Exception e) {
			System.out.println(trio);
			throw new RuntimeException();
		}
		return trigramCount / (bigramCount + 0.0d);
	}
}

package com.puru.nlp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.puru.nlp.ne.tagging.AnnotatedAssimilator;
import com.puru.nlp.ne.tagging.algo.TagSequenceEstimator;
import com.puru.nlp.ne.tagging.model.WordTagPair;
import com.puru.nlp.text.utils.TextUtils;

public class Client {
	public static void main(String[] args) throws FileNotFoundException,
			IOException {

		File file = new File("gene.train");

		List<WordTagPair> pairs = TrainFileUtil.readFromTrainFile(file);

		AnnotatedAssimilator assimilator = new AnnotatedAssimilator(pairs);

		List<String> sentences = getSentencesFromString(TextUtils
				.extractTextFromFile(new File("test.txt")));
		System.out.println(sentences);
		List<WordTagPair> list = TagSequenceEstimator.spitBestTagSequence(
				sentences, assimilator);

		System.out.println(list);
		// System.out.println(assimilator.getXYMap().size());

		// TODO: map of wordTagPairs and their counts
		// System.out.println(pairs);

		// for(Entry<WordTagPair, Integer> entry: pairMap.entrySet()){
		// //System.out.println(entry);
		// }

	}

	/**
	 * @param string
	 * @return
	 */
	private static List<String> getSentencesFromString(String string) {

		List<String> list = new ArrayList<String>();
		String[] sentence = (string.split("\\b"));
		for (String word : sentence) {
			if (word.trim().length() == 0) {
				continue;
			}
			word = word.trim();
			word = word.toLowerCase();
			list.add(word);
		}
		return list;
	}
}

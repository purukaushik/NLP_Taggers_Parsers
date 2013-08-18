package com.puru.nlp.trigram;

import java.io.File;
import java.io.IOException;

import com.puru.nlp.text.utils.TextUtils;
import com.puru.nlp.trigram.algo.EstimationType;
import com.puru.nlp.trigram.algo.TrigramEstimator;

public class Client {

	public static void main(String[] args) throws IOException {

		File file = new File(args[0]);

		String text = TextUtils.extractTextFromFile(file);

		// System.out.println(text);

		String[] sentences = text.split("\\.");

		Assimilator assimilator = new Assimilator(sentences);

		String printableWordMap = assimilator.getPrintableWordMap();

		String fileName = "wordMap.txt";
		TextUtils.writeToFile(printableWordMap, fileName);

		fileName = "wordPairMap.txt";
		String printableWordPairMap = assimilator.getPrintableWordPairMap();
		TextUtils.writeToFile(printableWordPairMap, fileName);

		fileName = "wordTrioMap";
		TextUtils.writeToFile(assimilator.getPrintableWordTrioMap(), fileName);

		String sentence = args[1];

		double q = TrigramEstimator.trigramEstimate(sentence, assimilator,
				EstimationType.NAIVE);

		System.out.println(q);
	}
}

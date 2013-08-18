package com.puru.nlp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.puru.nlp.ne.tagging.model.WordTagPair;

public class TrainFileUtil {
	public static List<WordTagPair> readFromTrainFile(File trainFile)
			throws IOException {

		FileInputStream fileInputStream = new FileInputStream(trainFile);

		InputStreamReader inputStreamReader = new InputStreamReader(
				fileInputStream);

		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		String line = bufferedReader.readLine();

		List<WordTagPair> wordTagPairs = new ArrayList<WordTagPair>();

		while (line != null) {
			// if (line.equals("")) {
			// System.out.println("new line ");
			// wordTagPairs.add(new WordTagPair("O", "*"));
			// }
			if (line.trim().length() == 0) {
				// System.out.println("!hitting!");
				line = bufferedReader.readLine();
				continue;
			}
			// text = text + line;
			String[] splice = line.split("\\s");

			int length = splice.length;
			if (length > 1) {
				String word = splice[0];
				String tag = splice[1];

				WordTagPair pair = new WordTagPair(tag, word);
				wordTagPairs.add(pair);
			}
			line = bufferedReader.readLine();

		}
		bufferedReader.close();
		return wordTagPairs;
	}

}

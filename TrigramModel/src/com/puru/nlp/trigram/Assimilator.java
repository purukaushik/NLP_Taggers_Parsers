package com.puru.nlp.trigram;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.puru.nlp.trigram.model.Word;
import com.puru.nlp.trigram.model.WordPair;
import com.puru.nlp.trigram.model.WordTrio;
import com.puru.nlp.trigram.vocab.utils.WordExtractor;

/**
 * Collects maps of unigrams, bigrams and trigrams.
 * 
 * @author guruguha
 * 
 */
public class Assimilator {

	/**
	 * Word, its count in the training sample.
	 */
	private Map<Word, Integer> wordMap = new HashMap<Word, Integer>();

	/**
	 * Word, its predecessor, count of the pair in the training sample.
	 */
	private Map<WordPair, Integer> wordPairMap = new HashMap<WordPair, Integer>();

	/**
	 * Word and its two predecessors.
	 */
	private Map<WordTrio, Integer> wordTrioMap = new HashMap<WordTrio, Integer>();

	/**
	 * Word Count of all unigram.
	 */
	private int wordCount;

	public Assimilator(String[] sentences) {

		this.wordCount = WordExtractor.extractWordsIntoMap(wordMap, sentences);

		WordExtractor.extractWordPairsIntoMap(wordPairMap, sentences);

		WordExtractor.extractWordTriosIntoMap(wordTrioMap, sentences);

	}

	public Map<WordTrio, Integer> getWordTrioMap() {
		return wordTrioMap;
	}
	public Map<Word, Integer> getWordMap() {
		return wordMap;
	}

	public Map<WordPair, Integer> getWordPairMap() {
		return wordPairMap;
	}

	public int getWordCount() {
		return wordCount;
	}

	public String getPrintableWordMap() {
		String printableReturn = "";

		for (Entry<Word, Integer> entry : wordMap.entrySet()) {

			printableReturn = printableReturn + "\n" + entry;

		}

		return printableReturn;
	}
	public String getPrintableWordPairMap() {
		String printableReturn = "";

		for (Entry<WordPair, Integer> entry : wordPairMap.entrySet()) {

			printableReturn = printableReturn + "\n" + entry;

		}

		return printableReturn;
	}

	public String getPrintableWordTrioMap() {
		String printableReturn = "";

		for (Entry<WordTrio, Integer> entry : wordTrioMap.entrySet()) {

			printableReturn = printableReturn + "\n" + entry;

		}

		return printableReturn;
	}
}

package com.puru.nlp.text.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;

/**
 * Provides standard text file processing.
 * 
 * @author guruguha
 * 
 */
public class TextUtils {

	/**
	 * Extracts the text from file provided. Uses buffered reading.
	 * 
	 * @param file
	 *            to be read
	 * @return the string text from file
	 * @throws FileNotFoundException
	 *             when there is no such file in the location
	 * @throws IOException
	 *             IO Crash. Contact Bill Gates if you're using Windows, Linus
	 *             Torvalds, if you're using Linux/Ubuntu.
	 */
	public static String extractTextFromFile(File file)
			throws FileNotFoundException, IOException {
		FileInputStream fileInputStream = new FileInputStream(file);

		InputStreamReader inputStreamReader = new InputStreamReader(
				fileInputStream);

		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		String line = bufferedReader.readLine();

		String text = "";

		while (line != null) {

			text = text + line;
			line = bufferedReader.readLine();
		}
		return text;
	}
	/**
	 * Writes a text onto the file provided in fileName
	 * 
	 * @param printableMap
	 *            usually this is a printable version of a {@link Map}
	 * @param fileName the fileName of output file.
	 * @throws IOException Contact the thugs at Microsoft!
	 */
	public static void writeToFile(String printableMap, String fileName)
			throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(new File(
				fileName));

		BufferedWriter bufferedWriter = new BufferedWriter(
				new OutputStreamWriter(fileOutputStream));

		bufferedWriter.write(printableMap);

		bufferedWriter.close();
	}
}

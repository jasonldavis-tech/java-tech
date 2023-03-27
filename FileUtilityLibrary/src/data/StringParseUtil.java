package data;

import java.util.List;

public class StringParseUtil {
	public static List<String> sentenceToWordsList(String sentence) {
		List<String> words = List.of((sentence).split(" "));
		return words;
	}
	
	public static String removePeriod(String sentence) {
		return sentence.trim().replaceFirst(".", "");
	}
	
	public static int getWordCount(String sentence) {
		sentence = sentence.trim();
		String words[] = sentence.split(" ");
		return words.length;
	}

}

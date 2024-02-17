package data;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
	
	public static InputStream convertStringToInputStream(String data) {
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data.getBytes());
			return byteArrayInputStream;
	}

}

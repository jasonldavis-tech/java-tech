package data;

import java.util.List;

public class InputValidationUtil {
	
	public static String flagPotentialMismatches(String sentence, String input) {
		List<String> wordsInSentence = StringParseUtil.sentenceToWordsList(sentence);
		List<String> wordsInInput = StringParseUtil.sentenceToWordsList(input);
		
		for (int i=0; i<wordsInSentence.size(); i++) {
			String word = wordsInSentence.get(i);
			if (i<wordsInInput.size()) {
				if (word.compareTo(wordsInInput.get(i))!=0) {
					return word;
				}
			} else {
				return "";
			}
		}
		
		return "";	    
	}
}

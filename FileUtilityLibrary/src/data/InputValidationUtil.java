package data;

import java.io.StringWriter;
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
	
	public static MismatchedSentenceWord flagPotentialMismatchesWithSurrounding(String sentence, String input) {
		sentence = InputValidationUtil.getSingleSpacedSentence(sentence);
		input = InputValidationUtil.getSingleSpacedSentence(input);
		List<String> wordsInSentence = StringParseUtil.sentenceToWordsList(sentence);
		List<String> wordsInInput = StringParseUtil.sentenceToWordsList(input);
		
		String word = "";
		int index = -1;
		int stringIndex=0;
		
		for (int i=0; i<wordsInSentence.size(); i++) {
			word = wordsInSentence.get(i);
			if (i<wordsInInput.size()) {
				stringIndex+=wordsInInput.get(i).length()+1;
				if (word.compareTo(wordsInInput.get(i))!=0) {
					index=i;
					stringIndex-=wordsInInput.get(i).length();
					break;
				}
			} else {
				break;
			}
		}
		
		stringIndex-=1;
		if (stringIndex<0) {
			stringIndex=0;
		}
		
		String wordBefore = "";
		String wordAfter = "";
		
		if (index==-1) {
			if (wordsInInput.size()<wordsInSentence.size()) {
				index = wordsInInput.size()-1;				
			}
		}
		
		if (index>0) {
			wordBefore = wordsInSentence.get(index-1);			
		}
		
		if (index<(wordsInSentence.size()-1)) {
			wordAfter = wordsInSentence.get(index+1);
		}
		
		MismatchedSentenceWord mismatchedSentenceWord = new MismatchedSentenceWord(word, wordBefore, wordAfter, stringIndex);
		
		return mismatchedSentenceWord;		
	}	
	
	public static String getSingleSpacedSentence(String sentence) {
		sentence = sentence.trim();
		
		final StringWriter stringWriter = new StringWriter();
		String words[] = sentence.split(" ");
		for (int i=0; i<words.length; i++) {
			if (words[i].compareTo("")!=0) {
				stringWriter.append(words[i]+" ");
			}
		}
		String singleSpacedString = stringWriter.toString().trim();
		
		return singleSpacedString;
	}
}

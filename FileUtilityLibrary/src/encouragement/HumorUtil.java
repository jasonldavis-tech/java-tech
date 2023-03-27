package encouragement;

import java.util.ArrayList;
import java.util.Arrays;

import random.RandomUtil;

public class HumorUtil {
	private static ArrayList<String> seriousPhrases;
	private static ArrayList<String> jokeOrEncouragingPhrases;
	
	public static ArrayList<String> getSeriousPhrases() {
		if (null != seriousPhrases) {
			return seriousPhrases;
		}
		
		String arrayOfSeriousPhrases[] = { 
			"Eat your vegetables",
			"Clean your room",
			"Do the dishes",
			"Go pick up some important documents from work",
			"Always be safe in the lab"
		};
		
		
		seriousPhrases = new ArrayList<>(Arrays.asList(arrayOfSeriousPhrases));
		
		return seriousPhrases;
	}
	
	public static ArrayList<String> getJokeOrEncouragingPhrases() {
		if (null != jokeOrEncouragingPhrases) {
			return jokeOrEncouragingPhrases;
		}
		
		String arrayOfJokeOrEncouragingPhrases[] = { 
			"Hug a kitten",
			"Never Say Die!",
			"The prettiest flowers have the most leaves",
			"you hug the lovable dictator"
		};
		
		
		jokeOrEncouragingPhrases = new ArrayList<>(Arrays.asList(arrayOfJokeOrEncouragingPhrases));
		
		return jokeOrEncouragingPhrases;
	}	
	
	public static String ruleOfThreeGenerator() {
		String seriousPhrase1 = RandomUtil.getRandomElementInListOfStrings(getSeriousPhrases());
		String seriousPhrase2 = RandomUtil.getRandomElementInListOfStrings(getSeriousPhrases());
		String jokeOrEncouragingPhrase = RandomUtil.getRandomElementInListOfStrings(getJokeOrEncouragingPhrases());
		return seriousPhrase1+", "+seriousPhrase2+", "+jokeOrEncouragingPhrase;
	}
}



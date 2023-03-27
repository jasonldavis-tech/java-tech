package encouragement;

import java.util.ArrayList;
import java.util.Arrays;
import random.RandomUtil;

public class EncouragementUtil {
	private static ArrayList<String> encouragingPhrases;
	
	public static ArrayList<String> getEncouragingPhrases() {
		if (null != encouragingPhrases) {
			return encouragingPhrases;
		}
		
		String arrayOfEncouragingPhrases[] = { 
			"Never Say Die!", "You can do it!", "You will Prevail!", 
			"Nsdtp! Never Say Die Throughput!", "Small changes do add up",
			"Possible is Power", "Possible has yet to be defined",
			"There is hope in a New Day", "The future has yet to be written",
			"Initial conditions can mean a lot", "Encouragement Throughput has yet to be maximized",
			"Customer Experience and Quality can be Improved", "Don't forget an extra bottle of water can change a lot",
			"Past activation energy can be clear sailing", "Others do not get to claim ground on what you can and cannot do"
		};
		
		
		encouragingPhrases = new ArrayList<>(Arrays.asList(arrayOfEncouragingPhrases));
		
		return encouragingPhrases;
	}
	
	public static String getRandomEncouragingPhrase() {
		String encouragingPhrase = RandomUtil.getRandomElementInListOfStrings(getEncouragingPhrases());
		return encouragingPhrase;
	}
}

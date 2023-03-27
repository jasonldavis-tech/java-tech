package story;

import java.util.ArrayList;
import java.util.Arrays;

public class ThemeUtil {
	private static ArrayList<String> themes;
	
	public static ArrayList<String> getThemes() {
		if (null != themes) {
			return themes;
		}
		
		String arrayOfThemes[] = { 
			"Love conquers all", "Seize the day", "We all have a bit of wisdom inside us",
			"Oppression is wrong", "Dangers of Progress"
		};
		
		
		themes = new ArrayList<>(Arrays.asList(arrayOfThemes));
		
		return themes;
	}
	
	public static String getRandomTheme() {
		String randomTheme = RandomUtil.getRandomElementInListOfStrings(getThemes());
		return randomTheme;
	}
}

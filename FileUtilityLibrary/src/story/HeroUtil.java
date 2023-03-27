package story;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.DoubleStream;

public class HeroUtil {
	private static ArrayList<String> heroes;
	private static ArrayList<String> herosJourney;
	private static ArrayList<String> heroAssistance;
	
	public static ArrayList<String> getHeroes() {
		if (null != heroes) {
			return heroes;
		}
		
		String arrayOfHeroes[] = { 
			"Fighter", "Knight", "Wizard", "Sorcerer", "Rogue", "Bard"
		};
		
		
		heroes = new ArrayList<>(Arrays.asList(arrayOfHeroes));
		
		return heroes;
	}
	
	public static ArrayList<String> getHerosJourney() {
		if (null != herosJourney) {
			return herosJourney;
		}
		
		String arrayOfHerosJourney[] = { 
			"Status Quo", "Assistance", "Call to Adventure", "Trials", "Crisis",
			"Treasure", "Resolution"
		};
		
		
		herosJourney = new ArrayList<>(Arrays.asList(arrayOfHerosJourney));
		
		return herosJourney;
	}	
	
	public static ArrayList<String> getHeroAssistance() {
		if (null != heroAssistance) {
			return heroAssistance;
		}
		
		String arrayOfHerosJourney[] = { 
			"Tool", "Weapon", "Lesson", "Magical Trinket"
		};
		
		
		herosJourney = new ArrayList<>(Arrays.asList(arrayOfHerosJourney));
		
		return heroAssistance;
	}	
	
	
	public static String getRandomHero() {
		if (null == heroes) {
			getHeroes();
		}
		
		RandomGenerator randomGenerator = RandomGeneratorFactory.getDefault().create();
		DoubleStream doubleStream = randomGenerator.doubles(1);
		
		OptionalDouble random = doubleStream.findFirst();
		
		int value = Double.valueOf(heroes.size()*random.getAsDouble()).intValue();
		String hero = heroes.get(value);
		return hero;		
	}
}

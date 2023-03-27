package story;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.DoubleStream;

public class VillainUtil {
	private static ArrayList<String> villains;
	
	public static ArrayList<String> getVillains() {
		if (null != villains) {
			return villains;
		}
		
		String arrayOfVillains[] = { 
			"Vampire", "Dragon", "Lich", "Ghost", "Kraken", "Giant",
			"Demon", "Banshee", "Siren", "Skeleton", "Troll",
			"Bandit", "Shark", "Giant Spider", "Stone Elemental", "Fire Elemental",
			"Ice Elemental", "Water Elemental", "Hurricane", "Tornado", "Earthquake",
			"Forest Fire", "Tyrannical King", "Necromancer", "Poltergeist", "Witch",
			"Warlock", "Cult Leader", "Psychopath", "Deranged Sniper", 
			"Mislead Revolutionary", "Artificial Intelligence", "Big Brother System",
			"Plague", "Famine", "Swarm", "Gas Leak", "Hostile Alien Spaceship",
			"Gang", "Corrupt Politician", "Unstable Future Tech", "Riot", "Martial Law"
		};
		
		
		villains = new ArrayList<>(Arrays.asList(arrayOfVillains));
		
		return villains;
	}
	
	public static String getRandomVillain() {
		if (null == villains) {
			getVillains();
		}
		
		RandomGenerator randomGenerator = RandomGeneratorFactory.getDefault().create();
		DoubleStream doubleStream = randomGenerator.doubles(1);
		
		OptionalDouble random = doubleStream.findFirst();
		
		int value = Double.valueOf(villains.size()*random.getAsDouble()).intValue();
		String villain = villains.get(value);
		return villain;		
	}
}

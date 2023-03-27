package story;

import java.util.ArrayList;
import java.util.Arrays;

public class DescriptiveWritingUtil {
	private static ArrayList<String> vividVerbs;
	private static ArrayList<String> preciseAdjectives;
	private static ArrayList<String> spectacularNouns;
	
	public static ArrayList<String> getVividVerbs() {
		if (null != vividVerbs) {
			return vividVerbs;
		}
		
		String arrayOfVividVerbs[] = { 
			"shouted", "dashed", "sprinted", "gnashed", "pulverized", "evoked", "conjured",
			"amplified", "magnified", "infused", "inspired", "encouraged", "provided", "shifted",
			"directed", "commanded", "whispered", "enlightened", "opened", "closed", "delivered",
			"transported", "refined", "relayed", "communicated", "eroded", "halted", "blocked",
			"accelerated", "evaded", "dodged", "unfurled", "typed", "wrote", "painted", "sculpted"
		};
		
		
		vividVerbs = new ArrayList<>(Arrays.asList(arrayOfVividVerbs));
		
		return vividVerbs;
	}
	
	public static ArrayList<String> getPreciseAdjectives() {
		if (null != preciseAdjectives) {
			return preciseAdjectives;
		}
		
		String arrayOfPreciseAdjectives[] = {
			"colossal", "monumental", "magnificent", "gargantuan", "minuscule", "microscopic",
			"loud", "soft", "sour", "sweet", "rough", "smooth", "brilliant", "daunting", "towering",
			"potential", "efficient", "kinetic", "glittering", "sweet", "fluffy", "delicate", "calm",
			"tranquil", "placid", "blue", "orange", "red", "green", "yellow", "purple", "shaded",
			"tinted", "saturated", "toned", "angry", "sad", "happy", "joyful", "delightful", "blissful",
			"memorable", "diminished", "exalted", "beguiling", "critical", "important", "proper", "biased",
			"pink", "violet", "cyan", "beige", "gray", "black", "white", "vibrant", "resonant", "harmonious"
		};
		
		
		preciseAdjectives = new ArrayList<>(Arrays.asList(arrayOfPreciseAdjectives));
		
		return preciseAdjectives;
	}
	
	public static ArrayList<String> getSpectacularNouns() {
		if (null != spectacularNouns) {
			return spectacularNouns;
		}
		
		String arrayOfSpectacularNouns[] = {
			"ruby", "sapphire", "emerald", "amber", "amethyst", "gold", "silver", "titanium",
			"aluminum", "steel", "neon", "zeon", "oxygen", "nitrogen", "carbon", "lightning",
			"wave", "photon", "spectrum", "energy", "watts", "joules", "kilogram", "meter",
			"kilometer", "blackhole", "neutron", "star", "neutron star", "gas giant", "moon",
			"jet", "rocket", "tank", "ship", "super-car", "beach", "desert", "imagination",
			"mountain", "lake", "woods", "forest", "city", "country", "skyscraper", "suburb",
			"sugar", "cream", "ice cream", "water", "wind", "fire", "earth", "emotion", "gear",
			"ratio", "beam", "bridge", "pendulum", "scale", "cliff", "rope", "chain", "cable",
			"castle", "villa", "hut", "farm", "earthquake", "hurricane", "tornado", "blizzard",
			"power", "game", "puzzle", "problem", "thought", "joy", "ethics", "peace", "battle",
			"war", "fight", "wolf", "tiger", "rhino", "hippopotamus", "scorpion", "spider",
			"snake", "lizard", "iguana", "puppy", "kitten", "bird", "parrot", "monkey", "marmoset",
			"dear", "elk", "jaguar", "hyena", "zebra", "angel", "demon", "monster", "hero",
			"velocity", "momentum", "nitro", "canvas", "dimension", "flamingo"
		};
		
		
		spectacularNouns = new ArrayList<>(Arrays.asList(arrayOfSpectacularNouns));
		
		return spectacularNouns;
	}
	
	public static String getDescriptivePhrase() {
		ArrayList<String> randomAdjectives = RandomUtil.getRandomList(DescriptiveWritingUtil.getPreciseAdjectives(), 3);
		ArrayList<String> randomNouns = RandomUtil.getRandomList(DescriptiveWritingUtil.getSpectacularNouns(), 1);
		ArrayList<String> randomVerbs = RandomUtil.getRandomList(DescriptiveWritingUtil.getVividVerbs(), 1);
		String phrase = randomAdjectives.toString()+" "+randomNouns.get(0)+" "+randomVerbs.get(0);
		return phrase;
	}
}

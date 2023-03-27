package story;

import java.util.ArrayList;
import java.util.Arrays;

public class LiteraryDevicesUtils {
	private static ArrayList<String> literaryDevices;
	
	public static ArrayList<String> getLiteraryDevices() {
		if (null != literaryDevices) {
			return literaryDevices;
		}
		
		String arrayOfLiteraryDevices[] = { 
			"Allusion", "Formal Diction", "Informal Diction", "Alliteration", "Imagery",
			"Symbolism", "Foreshadowing", "Flashback", "Allegory", "Tone", "Metaphor", "Simile",
			"Personification", "Juxtaposition", "Onomatopoeia", "Colloquialism", "Euphemism"
		};
		
		
		literaryDevices = new ArrayList<>(Arrays.asList(arrayOfLiteraryDevices));
		
		return literaryDevices;
	}
}

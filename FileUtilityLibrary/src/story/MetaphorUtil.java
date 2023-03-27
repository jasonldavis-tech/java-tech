package story;

import java.util.ArrayList;
import java.util.Arrays;

public class MetaphorUtil {
	private static ArrayList<String> metaphors;
	
	public static ArrayList<String> getMetaphors() {
		if (null != metaphors) {
			return metaphors;
		}
		
		String arrayOfMetaphors[] = { 
			"the world is a stage", "there is an elephant in the room", "drowning in a sea of paperwork"
		};
		
		
		metaphors = new ArrayList<>(Arrays.asList(arrayOfMetaphors));
		
		return metaphors;
	}
}

package story;

import java.util.ArrayList;
import java.util.Arrays;

public class SimileUtil {
	private static ArrayList<String> similes;
	
	public static ArrayList<String> getSimiles() {
		if (null != similes) {
			return similes;
		}
		
		String arrayOfSimiles[] = { 
			"as good as gold", "as cute as a button", "like light after the darkest storm",
			"like the wind of a tornado", "like thunder that makes the skin tingle"
		};
		
		
		similes = new ArrayList<>(Arrays.asList(arrayOfSimiles));
		
		return similes;
	}
}

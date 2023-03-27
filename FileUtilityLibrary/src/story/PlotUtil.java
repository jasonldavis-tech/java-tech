package story;

import java.util.ArrayList;
import java.util.Arrays;

public class PlotUtil {
	private static ArrayList<String> plots;
	public static ArrayList<String> getPlots() {
		if (null != plots) {
			return plots;
		}
		
		String arrayOfPlots[] = { 
			"Overcoming the Monster","Quest","Tragedy","Comedy"
		};
		
		
		plots = new ArrayList<>(Arrays.asList(arrayOfPlots));
		
		return plots;
	}
	
	public static String getRandomPlot() {
		String randomPlot = RandomUtil.getRandomElementInListOfStrings(getPlots());
		return randomPlot;
	}	
}

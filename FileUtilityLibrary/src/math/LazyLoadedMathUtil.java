package math;

import java.util.HashMap;

public class LazyLoadedMathUtil {
	private static int decimalPlacesOfPrecision=3;
	private static HashMap<String, Double> sineMap = new HashMap<>();
	private static HashMap<String, Double> cosineMap = new HashMap<>();
	private static HashMap<String, Double> tanMap = new HashMap<>();
	private static String decimalFormatPattern = "%."+decimalPlacesOfPrecision+"f";
	
	public static double sine(double radians) {
		String parameter = String.format(decimalFormatPattern,radians);
		Double value = sineMap.get(parameter);
		if (null==value) {
			value = Math.sin(radians);
			sineMap.put(parameter, value);
		}

		return value;		
	}	

	public static double cos(double radians) {
		String parameter = String.format(decimalFormatPattern,radians);
		Double value = cosineMap.get(parameter);
		if (null==value) {
			value = Math.cos(radians);
			sineMap.put(parameter, value);
		}

		return value;		
	}
	
	public static double tan(double radians) {
		String parameter = String.format(decimalFormatPattern,radians);
		Double value = tanMap.get(parameter);
		if (null==value) {
			value = Math.tan(radians);
			sineMap.put(parameter, value);
		}

		return value;		
	}	
}

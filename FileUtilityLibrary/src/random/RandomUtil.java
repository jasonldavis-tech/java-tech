package random;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.OptionalDouble;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.DoubleStream;

public class RandomUtil {
	private static RandomGenerator randomGenerator = RandomGeneratorFactory.getDefault().create();
	public static ArrayList<String> getRandomList(ArrayList<String> listOfStrings, int size) {
		DoubleStream doubleStream = randomGenerator.doubles(size);
		
		ArrayList<String> randomList = new ArrayList<>();
		
		doubleStream.forEach((random) -> {
			int value = Double.valueOf(listOfStrings.size()*random).intValue();
			randomList.add(listOfStrings.get(value));
		});
		
		return randomList;
	}
	
	public static String getRandomElementInListOfStrings(ArrayList<String> listOfStrings) {
		DoubleStream doubleStream = randomGenerator.doubles(1);
		
		OptionalDouble random = doubleStream.findFirst();
		int value = Double.valueOf(listOfStrings.size()*random.getAsDouble()).intValue();
		String randomElementString = listOfStrings.get(value);		
		return randomElementString;		
	}
	
	public static int getRandomNumber(int lowerBound, int upperBound) {
		return randomGenerator.nextInt(lowerBound, upperBound);
	}
	
	public static String getRandomNumberString(int size) {
		StringWriter token = new StringWriter();
		
		for (int i=0; i<size; i++) {
			token.append(""+getRandomNumber(0, 9));
		}
		
		return token.toString();
	}
}

package math;

import java.util.ArrayList;
import java.util.logging.Logger;

import testing.TestLogger;

public class AdditionUtil {
	private static TestLogger logger = new TestLogger(Logger.getLogger(AdditionUtil.class.getName()));
	
	public static int sumListOfIntegers1(ArrayList<Integer> list) {
		logger.info("Running method 1");
		int sum=0;
		sum = list.stream().mapToInt(value -> value).sum();		
		return sum;
	}
	
	public static int sumListOfIntegers2(ArrayList<Integer> list) {
		logger.info("Running method 2");
		var object = new Object() { int sum=0; };
		list.forEach((value) -> {
			object.sum+=value;
		});
		return object.sum;
	}
	
	public static int sumListOfIntegers3(ArrayList<Integer> list) {
		logger.info("Running method 3");
		
		int sum=0;
		for (int i=0; i<list.size(); i++) {
			sum+=list.get(i);
		}
		
		return sum;
	}		
}

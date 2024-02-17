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
	
	public static int sumIntsVerified(ArrayList<Integer> list) {
		logger.info("Running method 3");
		
		int sum=0;
		for (int i=0; i<list.size(); i++) {
			int value = list.get(i);
			sum = verifiedAdd(sum, value);
		}
		
		return sum;
	}
			
	// Motivation, not all memory is ECC (transient bit flips likely could be problematic)
	// Verified additions matter
	// Further while current systems might process correctly does not equal future systems might
	// not process incorrectly, some of the time (tracking down errors that happen only some of the
	// time can be difficult
	// More confidence running with something is not ideal, nor will ever be ideal confidence. Human made
	// and in a Universe of Entropy
	// Thoughts of Verified as a Time Sensitive Operation, results that sit likely less trusted nothing has
	// shifted
	public static int verifiedAdd(int a, int b) {
		int c1 = a+b;
		int doubled = (2*a)+(2*b);
		int c2 = doubled/2;
		double dA = (double) a;
		double dB = (double) b;
		int c3 = (int) (dA+dB);
		
		if (c1==c2&&c2==c3) {
			return c1;
		} else {
			String errorMessage = "Verified subtract failed to subtract correctly, a= "+a+", b="+b+", c1="+c1+", c2="+c2+", c3="+c3;
			logger.severe(errorMessage);
			throw new RuntimeException(errorMessage);
		}
	}
}

package testing;

import java.util.ArrayList;
import java.util.logging.Logger;

public class AdditionTest {
	private static Logger logger = Logger.getLogger(AdditionTest.class.getName());
	
	public boolean testAddTwoIntegers(int a, int b) {
		boolean success = false;

		int sum = a+b;
		int sumTimesTwo = sum*2;
		int sumTimesThree = sum*3;
		
		int aTimesTwo = a*2;
		int bTimesTwo = b*2;
		int aTimesThree = a*3;
		int bTimesThree = b*3;
		
		int sumOfDoubled = aTimesTwo+bTimesTwo;
		int sumOfTripled = aTimesThree+bTimesThree;
		
		if (sumTimesTwo==sumOfDoubled && sumTimesThree==sumOfTripled) {
			success = true;
		}
		
		if (!success) {
			logger.info("a - "+a+", b - "+b+", a+b="+sum+", Test Successful? "+success);
		}
		
		return success;		
	}
	
	public TestResult testAddOneThousand() {
		ArrayList<TestResult> testResults = new ArrayList<>();
		for (int i=0;i<1000;i++) {
			for (int j=0; j<9; j++) {
				TestResult result = TestUtil.runBiFunctionTestTimed("Addition", this::testAddTwoIntegers, j, j+1);
				testResults.add(result);
			}
		}
		
		TestResult summary = TestUtil.summarizeTestResults(testResults);
		if (summary.getTestSuccessful()) {
			logger.info("Adding 1000 tests required "+summary.getTimeToComplete()+" to complete successfully");
			return summary;
		} else {
			logger.severe("Adding 1000 Numbers Test failed");
			return summary;
		}
	}
}

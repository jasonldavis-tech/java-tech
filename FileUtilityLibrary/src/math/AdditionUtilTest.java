package math;

import java.util.ArrayList;

import testing.TestCase;
import testing.TestResult;

public class AdditionUtilTest extends TestCase {
	
	public AdditionUtilTest() {
		super(AdditionUtilTest.class.getName());
	}

	
	public TestResult testSumListOfIntegers() {
		TestResult testResult = new TestResult("testSumListOfIntegers()");
		boolean testCase1 = false;
		boolean testCase2 = false;
		boolean testCase3 = false;
		
		int validValue = 10;
		
		ArrayList<Integer> listOfIntegers = new ArrayList<>();
		listOfIntegers.add(1);
		listOfIntegers.add(2);
		listOfIntegers.add(3);
		listOfIntegers.add(4);
		
		int computedSum = AdditionUtil.sumListOfIntegers1(listOfIntegers);		
		logger.info("Computed sum = "+computedSum+" for list "+listOfIntegers);
		if (validValue==computedSum) {
			logger.logSuccess("Test succeed for method 1");
			testCase1=true;
		} else {
			logger.logFailure("Test failed for method 1");
		}
		
		computedSum = 0;
		computedSum = AdditionUtil.sumListOfIntegers2(listOfIntegers);		
		logger.info("Computed sum = "+computedSum+" for list "+listOfIntegers);
		if (validValue==computedSum) {
			logger.logSuccess("Test succeed for method 2");
			testCase2=true;
		} else {
			logger.logFailure("Test failed for method 2");
		}
		
		computedSum = 0;
		computedSum = AdditionUtil.sumListOfIntegers3(listOfIntegers);		
		logger.info("Computed sum = "+computedSum+" for list "+listOfIntegers);
		if (validValue==computedSum) {
			logger.logSuccess("Test succeed for method 3");
			testCase3=true;
		} else {
			logger.logFailure("Test failed for method 3");
		}
		
		boolean success = testCase1 && testCase2 && testCase3;
		testResult.setTestSuccessful(success);
		
		return testResult;

	}
}

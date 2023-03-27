package testing;

import java.util.logging.Logger;

import performance.PerformanceUtil;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class TestUtil {
	private static Logger logger = Logger.getLogger(TestUtil.class.getName());
	private static PerformanceUtil perfUtil = new PerformanceUtil();	
	
	public static TestResult summarizeTestResults(ArrayList<TestResult> results) {
		boolean success = true;
		
		long summedTime=0;
		for (int i=0; i<results.size(); i++) {
			TestResult result = results.get(i);
			if (!result.getTestSuccessful()) {
				success = false;
			}
			summedTime += result.getTimeToComplete();
		}
		
		TestResult summedResult = new TestResult("summarizeTestResults"); // TODO: Update to potentially use different class
		return summedResult;
	}
	
	public static TestResult runSupplierTestTimed(String testName, Supplier<Boolean> testMethod) {
		boolean success = false;
		perfUtil.start();
		success = testMethod.get();
		perfUtil.stop();
		
		return new TestResult("runSupplierTestTimed"); // TODO: Update to potentially use different class
	}
	
	public static <T, U> TestResult runBiFunctionTestTimed(String testName, BiFunction<T, U, Boolean> testMethod,
			T param1, U param2) {
		boolean success = false;
		perfUtil.start();
		success = testMethod.apply(param1, param2);
		perfUtil.stop();
		
		return new TestResult("runBiFunctionTestTimed"); // TODO: Update to potentially use different class
	}	
}

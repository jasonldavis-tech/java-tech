package performance;

import java.util.logging.Logger;

public class PerformanceTests {
	private static Logger logger = Logger.getLogger(PerformanceTests.class.getName());
	private static PerformanceTests instance;
	
	public static PerformanceTests getInstance() {
		if (null == instance) {
			instance = new PerformanceTests();
		}
		
		return instance;
	}
	
	public static void runPerformanceTests() {
		addTwoOneDigitNumbers_OneBillionTimes();
		addTwoOneDigitNumbers_OneBillionTimes_andCheckVerify_OneBillionTimes();
	}
	
	public static void addTwoOneDigitNumbers_OneBillionTimes() {
		logger.info("Performance Test, Adding Two One Digit Numbers 1 billion times");
		int sum;
		PerformanceUtil.getInstance().start();
		for (int i=0; i<200000000; i++) {
			for (int j=0; j<5; j++) {
				sum = j+j;
			}
			if (i==50000000) {
				logger.info("--- Processed 250,000,000 additions, current running time: "
						+PerformanceUtil.getInstance().getRunningTime());
			}
			if (i==100000000) {
				logger.info("--- Processed 500,000,000 additions, current running time: "
						+PerformanceUtil.getInstance().getRunningTime());
			}
			if (i==150000000) {
				logger.info("--- Processed 750,000,000 additions, current running time: "
						+PerformanceUtil.getInstance().getRunningTime());
			}
		}
		PerformanceUtil.getInstance().stop();
		logger.info("Test completed, recorded time in Nanoseconds: "
				+PerformanceUtil.getInstance().getRecordedTime());		
	}
	
	public static void addTwoOneDigitNumbers_OneBillionTimes_andCheckVerify_OneBillionTimes() {
		logger.info("Performance Test, Adding Two One Digit Numbers 1 billion times");
		int sum;
		int doubleTheValue;
		int doubleTheSum;
		int sumOfDoubled;
		PerformanceUtil.getInstance().start();
		for (int i=0; i<200000000; i++) {
			for (int j=0; j<5; j++) {
				sum = j+j;
				doubleTheValue = j*2;
				doubleTheSum = sum*2;
				sumOfDoubled = doubleTheValue+doubleTheValue;
				if (doubleTheSum!=sumOfDoubled) {
					PerformanceUtil.getInstance().stop();
					logger.severe("Numbers not verifying ending test at i="+i+",j="+j+" at recorded time: "
							+PerformanceUtil.getInstance().getRecordedTime());
					return;					
				}
			}
			if (i==50000000) {
				logger.info("--- Processed 250,000,000 additions, current running time: "
						+PerformanceUtil.getInstance().getRunningTime());
			}
			if (i==100000000) {
				logger.info("--- Processed 500,000,000 additions, current running time: "
						+PerformanceUtil.getInstance().getRunningTime());
			}
			if (i==150000000) {
				logger.info("--- Processed 750,000,000 additions, current running time: "
						+PerformanceUtil.getInstance().getRunningTime());
			}
		}
		PerformanceUtil.getInstance().stop();
		logger.info("Test completed, recorded time in Nanoseconds: "
				+PerformanceUtil.getInstance().getRecordedTime());				
	}
}

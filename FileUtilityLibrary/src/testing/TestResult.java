package testing;

import java.util.logging.Logger;

import performance.PerformanceUtil;

public class TestResult {
	private static Logger logger = Logger.getLogger(TestResult.class.getName());
	
	@Override
	public String toString() {
		return "TestResult [testName=" + testName + ", testMessage=" + testMessage
				+ ", timeToComplete=" + timeToComplete + ", testSuccessful=" + testSuccessful + "]";
	}

	private PerformanceUtil perfUtil = new PerformanceUtil();
	private String testName;
	private String testMessage;
	private long timeToComplete;
	private boolean testSuccessful;
	
	public TestResult(final String name) {
		this.testName = name;
	}
	
	public void startTest() {
		logger.info("Starting Test");
		perfUtil.startInstant();
	}
	
	public void stopTest() {
		perfUtil.stopInstant();
		logger.info("Test Stopped");
		timeToComplete= perfUtil.getInstantDifferenceMilliseconds();
	}		
	
	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestMessage() {
		return testMessage;
	}

	public void setTestMessage(String testMessage) {
		this.testMessage = testMessage;
	}

	public void setTestSuccessful(boolean testSuccessful) {
		this.testSuccessful = testSuccessful;
	}	
	
	public long getTimeToComplete() {
		return timeToComplete;
	}

	public boolean getTestSuccessful() {
		return testSuccessful;
	}	
}

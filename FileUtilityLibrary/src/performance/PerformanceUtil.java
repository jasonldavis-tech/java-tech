package performance;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class PerformanceUtil {
	private ArrayList<PerformanceReading> performanceReadings = new ArrayList<>();
	private boolean stopWatchStarted = false;
	public boolean isStopWatchStarted() {
		return stopWatchStarted;
	}

	private long startTime;
	private long endTime;
	private long recordedTime=0;
	private static PerformanceUtil instance;
	
	public static PerformanceUtil getInstance() {
		if (null == instance) {
			instance = new PerformanceUtil();
		}
		
		return instance;
	}
	
	private Instant start;
	private Instant stop;
	
	public void startInstant() {
		start = Instant.now();
	}
	
	public void stopInstant() {
		stop = Instant.now();
	}
	
	public long getInstantDifferenceMilliseconds() {
		return Duration.between(start, stop).toMillis();
	}
	
	public boolean start() {
		if (stopWatchStarted) {
			return false;
		} else {
			startTime=System.nanoTime();
			stopWatchStarted=true;
			return true;
		}
	}
	
	public boolean stop() {
		if (!stopWatchStarted) {
			return false;
		} else {
			endTime=System.nanoTime();
			stopWatchStarted=false;
			return true;
		}
	}
	
	public long getRunningTime() {
		long runningTime = System.nanoTime()-startTime;
		return runningTime;
	}
	
	public long getRecordedTime() {
		recordedTime=endTime-startTime;
		return recordedTime;
	}
	
	public int getRecordedTimeMilliseconds() {
		recordedTime=endTime-startTime;
		double milliseconds = recordedTime/1000000;
		return (int) milliseconds;
	}	
	
	public void clearPerformanceReadingsList() {
		performanceReadings = new ArrayList<>();		
	}
	
	public void addPerformanceReading(String performanceReadingName) {
		long recordedTime = getRecordedTime();
		PerformanceReading performanceReading = new PerformanceReading(recordedTime, performanceReadingName);
		performanceReadings.add(performanceReading);		
	}
	
	public PerformanceReading summarizePerformanceReadings(String readingName, ArrayList<PerformanceReading> readings) {
		if (readings.size()>0) {
			long sum=0;
			for (int i=0; i<readings.size(); i++) {
				sum = readings.get(i).getNanoseconds();			
			}
			long average = sum/readings.size();
			
			return new PerformanceReading(average, readingName);
		} else {
			return null;
		}
	}
	
	public ArrayList<PerformanceReading> getPerformanceReadings() {
		return performanceReadings;
	}
	
	public static PerformanceReading runAndTime(String name, Runnable method) {
		PerformanceUtil perfUtil = new PerformanceUtil();
		perfUtil.startInstant();
		method.run();
		perfUtil.stopInstant();
		PerformanceReading performanceReading = new PerformanceReading(0, name);
		String timingMessage = "System required "+perfUtil.getInstantDifferenceMilliseconds()+" ms to execute "+name;
		performanceReading.setMilliseconds(perfUtil.getInstantDifferenceMilliseconds());
		performanceReading.setMessage(timingMessage);
		return performanceReading;
	}
}

package performance;

public class PerformanceReading {
	
	public PerformanceReading(final long nanoseconds, final String name) {
		this.nanoseconds = nanoseconds;
		this.name = name;
	}
	
	private long nanoseconds;
	private String name;
	private String message;
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toString() {
		return message;
	}
	
	public long getNanoseconds() {
		return nanoseconds;
	}
	
	public long getMilliseconds() {
		return TimeMeasurementUtil.convertNanosecondsToMilliseconds(nanoseconds);
	}
	
	public void setMilliseconds(long milliseconds) {
		nanoseconds = TimeMeasurementUtil.convertMillisecondsToNanoseconds(milliseconds);
	}
	
	public String getName() {
		return name;
	}	
}

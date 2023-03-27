package performance;

public class TimeMeasurementUtil {
	public static long convertNanosecondsToMilliseconds(long nanoseconds) {
		long milliseconds = (int) (nanoseconds/1000000);
		return milliseconds;
	}
	
	public static long convertMillisecondsToNanoseconds(long milliseconds) {
		long nanoseconds = (int) (milliseconds*1000000);
		return nanoseconds;
	}
}

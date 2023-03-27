package file;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

import performance.PerformanceUtil;

public class ProcessUtil {
	private static Logger logger = Logger.getLogger(ProcessUtil.class.getName());
	
	public static void runProcessAndRedirectOutput(String commands[], int timeoutMilliseconds) {
		int maxSleeps = timeoutMilliseconds/50;
		
		try {
			ProcessBuilder processBuilder = new ProcessBuilder(Arrays.asList(commands));
			processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
			processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
			PerformanceUtil.getInstance().start();
			Process process = processBuilder.start();
			CompletableFuture<Process> processComplete = process.onExit();
			int i=0;
			while (!processComplete.isDone() || i>maxSleeps) {
				try {
					i++;
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (processComplete.isDone()) {
				PerformanceUtil.getInstance().stop();
				long timeInNanoseconds = PerformanceUtil.getInstance().getRecordedTime();
				logger.info("Finished processing"+Arrays.asList(commands).toString());
				logger.info("Time to Run Process Required:"+timeInNanoseconds+" nanoseconds");	    
			} else {
				logger.info("Process timed out");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
}

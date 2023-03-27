package performance;

import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Logger;

import file.FileUtil;

public class DiskWriteReadPerformance {
	private static Logger logger = Logger.getLogger(DiskWriteReadPerformance.class.getName());
	
	public static void testWritePerformance(String filename) {
		final StringWriter stringWriter = new StringWriter();
		for (int i=0; i<10240000; i++) {
			stringWriter.append("AAAAAaaaaaBBBBBccccc");
			stringWriter.append("CCCCCdddddEEEEEfffff");
			stringWriter.append("GGGGGhhhhhIIIIIjjjjj");
			stringWriter.append("KKKKKlllllMMMMMnnnnn");
			stringWriter.append("OOOOOpppppQQQQQrrrrr\n");
		}
		
		int numberOfBytes = stringWriter.toString().getBytes().length;
		int megabytes = numberOfBytes/1000000;
		logger.info("Bytes in String: "+megabytes+" mb");
		PerformanceUtil.getInstance().start();
		try {
			FileUtil.writeFile(filename, stringWriter.toString());
		} catch (IOException e) {
			logger.severe(e.getMessage());
		}
		PerformanceUtil.getInstance().stop();
		logger.info("Time Required to write "+megabytes+ " mb data to "+filename+": "
		  +PerformanceUtil.getInstance().getRecordedTimeMilliseconds()+" ms");
		
	}
	
	public static void testReadPerformance(String filename) {
		PerformanceUtil.getInstance().start();
		try {
			String fileAsString = FileUtil.readFileAsString(filename);
			PerformanceUtil.getInstance().stop();
			int megabytes = fileAsString.getBytes().length/1000000;
			logger.info("Time Required to read "+megabytes+" mb data from "+filename+": "
					+PerformanceUtil.getInstance().getRecordedTimeMilliseconds()+" ms");
		} catch (IOException e) {
			logger.severe(e.getMessage());
		}
	}
}

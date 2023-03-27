package compression;

import testing.TestCase;
import testing.TestResult;

public class CompressionUtilTest extends TestCase {

	public CompressionUtilTest() {
		super(CompressionUtilTest.class.getName());				
	}
	
	public TestResult testZipUnzipString() {
		TestResult testResult = new TestResult("testZipUnzipString");
		testResult.startTest();
		String originalMessage = "this is message to test";
		
		byte compressedMessage[] = CompressionUtil.zipString(originalMessage);
		
		String uncompressedMessage = CompressionUtil.unzipString(compressedMessage);
		
		if (originalMessage.compareTo(uncompressedMessage)==0) {
			logger.logSuccess("Compression Util Tested successful zipping and unzipping a String");
			testResult.setTestSuccessful(true);			
		} else {
			logger.logFailure("Compression Util Test failed, Original String and Uncompressed String are different\n"
					+"originalMessage = "+originalMessage+"\n"
					+"uncompressedMessage = "+uncompressedMessage+"\n");
			testResult.setTestSuccessful(false);					
		}
		testResult.stopTest();
		return testResult;
	}

}

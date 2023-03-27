package testing;

import java.util.logging.Logger;

import color.AsciiColorUtil;

public class TestLogger {
	private Logger logger;
	
	public TestLogger(final Logger logger) {
		this.logger=logger;
	}
	
	private void logInfo(String message) {
		logger.info(logger.getName()+" - "+message);
	}
	
	private void logSevere(String message) {
		logger.severe(logger.getName()+" - "+message);
	}	
	
	public void logSuccess() {
		logInfo(AsciiColorUtil.GREEN+"Test completed successfully"+AsciiColorUtil.DEFAULT);
	}
	
	public void logSuccess(String message) {
		logInfo(AsciiColorUtil.GREEN+message+AsciiColorUtil.DEFAULT);
	}
	
	public void info(String message) {
		logInfo(message);
	}
	
	public void severe(String message) {
		logSevere(message);
	}
	
	public void logFailure(String message) {
		logSevere(AsciiColorUtil.RED+message+AsciiColorUtil.DEFAULT);
	}
}

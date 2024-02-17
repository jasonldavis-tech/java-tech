package testing;

import java.util.logging.Logger;

import color.AnsiColorUtil;

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
		logInfo(AnsiColorUtil.GREEN+"Test completed successfully"+AnsiColorUtil.DEFAULT);
	}
	
	public void logSuccess(String message) {
		logInfo(AnsiColorUtil.GREEN+message+AnsiColorUtil.DEFAULT);
	}
	
	public void info(String message) {
		logInfo(message);
	}
	
	public void severe(String message) {
		logSevere(message);
	}
	
	public void logFailure(String message) {
		logSevere(AnsiColorUtil.RED+message+AnsiColorUtil.DEFAULT);
	}
}

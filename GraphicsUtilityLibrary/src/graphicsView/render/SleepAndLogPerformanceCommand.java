package graphicsView.render;

import java.util.logging.Logger;

import commonModel.RenderEngineModel;
import performance.PerformanceReading;
import performance.PerformanceUtil;
import performance.TimeMeasurementUtil;

public class SleepAndLogPerformanceCommand implements Runnable {
	private static Logger logger = Logger.getLogger(SleepAndLogPerformanceCommand.class.getName());
	PerformanceUtil performanceUtil;
	RenderEngineModel model;
	
	public SleepAndLogPerformanceCommand(final RenderEngineModel renderEngineModel,
			final PerformanceUtil performanceUtil) {
		
		this.model=renderEngineModel;
		this.performanceUtil=performanceUtil;		
	}

	@Override
	public void run() {
		try {
			Thread.sleep(model.getSleepTimeMilliseconds());
			
			if (model.getTimeSinceLastPerformanceReading()>5000) {
				model.setTimeSinceLastPerformanceReading(0);
				PerformanceReading reading = performanceUtil.summarizePerformanceReadings(
						"Frame Render Time Average",
						performanceUtil.getPerformanceReadings());
				if (null!=reading) {
					logger.info("Current render time average nanoseconds: "+reading.getNanoseconds());
					long milliseconds = TimeMeasurementUtil.convertNanosecondsToMilliseconds(
							reading.getNanoseconds());
					logger.info("Current render time average milliseconds: "+milliseconds);
				}
			} else {
				model.setTimeSinceLastPerformanceReading(model.getTimeSinceLastPerformanceReading()
						+model.getSleepTimeMilliseconds());
			}
		} catch (InterruptedException e) {
			// Shutdown Now will interrupt threads, thus return on Interrupt
			// stops the thread
			return;
		}		
	}
}

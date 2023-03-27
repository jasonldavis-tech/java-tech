package commonModel;

import java.awt.image.BufferedImage;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class RenderEngineModel {
	private Logger logger = Logger.getLogger(RenderEngineModel.class.getName());
	private BlockingQueue<Image> blockingImageQueue;
	private ConcurrentLinkedQueue<KeyCode> keyBoardEvent = new ConcurrentLinkedQueue<>();	

	private boolean toggleAddFilterOn = false;
	private boolean toggleSubtractFilterOn = false;
	private boolean toggleRenderingOn = true;
	int addOffset=0;
	int subtractOffset=0;
	private static boolean exitState = false;
	
	public static boolean isExitState() {
		return exitState;
	}

	public void exit() {
		logger.info("Exit started...");
		exitState = true;
	}
	
	public int getTimeSinceLastPerformanceReading() {
		return timeSinceLastPerformanceReading;
	}

	public void setTimeSinceLastPerformanceReading(int timeSinceLastPerformanceReading) {
		this.timeSinceLastPerformanceReading = timeSinceLastPerformanceReading;
	}

	public int getSleepTimeMilliseconds() {
		return sleepTimeMilliseconds;
	}

	public void setSleepTimeMilliseconds(int sleepTimeMilliseconds) {
		this.sleepTimeMilliseconds = sleepTimeMilliseconds;
	}

	int timeSinceLastPerformanceReading = 0;
	int sleepTimeMilliseconds = 15;	

	private int carVerticalOffset=0;
	
	private boolean backgroundChanged = true;
	private BufferedImage backgroundBufferedImage;
	private BufferedImage bufferedImage;
	
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

	public RenderEngineModel(BlockingQueue<Image> blockingImageQueue) {
		this.blockingImageQueue = blockingImageQueue;
	}
	
	public BlockingQueue<Image> getConcurrentImageQueue() {
		return blockingImageQueue;
	}
	
	public ConcurrentLinkedQueue<KeyCode> getKeyBoardEventQueue() {
		return keyBoardEvent;
	}
	
	public boolean isToggleAddFilterOn() {
		return toggleAddFilterOn;
	}

	public void setToggleAddFilterOn(boolean toggleAddFilterOn) {
		this.toggleAddFilterOn = toggleAddFilterOn;
	}

	public boolean isToggleSubtractFilterOn() {
		return toggleSubtractFilterOn;
	}

	public void setToggleSubtractFilterOn(boolean toggleSubtractFilterOn) {
		this.toggleSubtractFilterOn = toggleSubtractFilterOn;
	}

	public boolean isToggleRenderingOn() {
		return toggleRenderingOn;
	}

	public void setToggleRenderingOn(boolean toggleRenderingOn) {
		this.toggleRenderingOn = toggleRenderingOn;
	}

	public int getAddOffset() {
		return addOffset;
	}

	public void setAddOffset(int addOffset) {
		this.addOffset = addOffset;
	}

	public int getSubtractOffset() {
		return subtractOffset;
	}

	public void setSubtractOffset(int subtractOffset) {
		this.subtractOffset = subtractOffset;
	}
	
	public int getCarVerticalOffset() {
		return carVerticalOffset;
	}

	public void setCarVerticalOffset(int carVerticalOffset) {
		this.carVerticalOffset = carVerticalOffset;
	}

	public boolean isBackgroundChanged() {
		return backgroundChanged;
	}

	public void setBackgroundChanged(boolean backgroundChanged) {
		this.backgroundChanged = backgroundChanged;
	}

	public BufferedImage getBackgroundBufferedImage() {
		return backgroundBufferedImage;
	}

	public void setBackgroundBufferedImage(BufferedImage backgroundBufferedImage) {
		this.backgroundBufferedImage = backgroundBufferedImage;
	}
}

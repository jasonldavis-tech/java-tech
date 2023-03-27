package graphicsView.render;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import color.AsciiColorUtil;
import graphics.TwoColorImage;
import graphicsView.render.example.TwoColorDrawingExample1;
import graphicsView.render.example.TwoColorDrawingExample2;

public class RenderPartialFrame implements Runnable {
	private static Logger logger = Logger.getLogger(RenderPartialFrame.class.getName());
	int xOffset;
	int renderWidth;
	int renderHeight;
	private boolean rendered=false;
	public boolean isRendered() {
		return rendered;
	}

	private TwoColorImage twoColorImage;

	public TwoColorImage getTwoColorImage() {
		return twoColorImage;
	}

	final String name;
	
	public RenderPartialFrame(String name, int renderWidth, int renderHeight)
    {
		this.name = name;
		this.renderWidth = renderWidth;
		this.renderHeight = renderHeight;
		twoColorImage = new TwoColorImage(renderWidth,renderHeight);
	}
	
	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	@Override
	public void run() {
		renderFrame();
	}
	
	private boolean loggingEnabled = false;
	
	private void log(String message) {
		if (loggingEnabled) {
			logger.info(AsciiColorUtil.BLUE+name+" - "+message+AsciiColorUtil.DEFAULT);
		}
	}
	
	public static AtomicInteger yOffset = new AtomicInteger();
	
	public void renderFrame() {		
		TwoColorDrawingExample2.renderFrame(renderWidth, renderHeight, yOffset, twoColorImage);
		
		rendered=true;
	}
}

package graphicsView.render.example;

import java.util.concurrent.atomic.AtomicInteger;

import graphics.TwoColorImage;

public class TwoColorDrawingExample1 {
	public static void renderFrame(int renderWidth, int renderHeight, AtomicInteger yOffset, TwoColorImage twoColorImage) {		
		int y = yOffset.getAndIncrement();
		y+=10;
		yOffset.set(y);
		if (y>twoColorImage.getHeight()) {
			yOffset.set(0);
		}
		
		y=0;
		
		twoColorImage.drawLine(0, 0+y, renderWidth-10, renderHeight-10+y);
		twoColorImage.drawLine(0, 150+y, 300, 150+y);
		twoColorImage.drawLine(10, 0+y, 10, renderHeight-1+y);
		twoColorImage.drawLine(20, 0+y, 20, renderHeight-1+y);
		
		twoColorImage.drawLine(0, 10+y, renderWidth-1, 10+y);
		
		twoColorImage.drawLine(15, 150+y, renderWidth-15, 10+y);
		twoColorImage.drawLine(renderWidth-30, 150+y, 15, 150+y); // Horizontal Line
		
		twoColorImage.drawLine(15, 350+y, renderWidth-15, 210+y);
		twoColorImage.drawLine(renderWidth-30, 350+y, 15, 350+y); // Horizontal Line
		
		twoColorImage.drawLine(15, 550+y, renderWidth-15, 410+y);
		twoColorImage.drawLine(renderWidth-30, 550+y, 15, 550+y); // Horizontal Line
		
		twoColorImage.drawLine(15, 750+y, renderWidth-15, 610+y);
		twoColorImage.drawLine(renderWidth-30, 750+y, 15, 750+y); // Horizontal Line				

	    twoColorImage.drawRectangeCoordinates(0, 150, 65, 350);
	    twoColorImage.drawRectangeCoordinates(0, 350, 165, 550);
	    twoColorImage.drawRectangeCoordinates(0, 550, 265, 750);
	    twoColorImage.drawRectangeCoordinates(0, 750, 365, 950);
	    twoColorImage.drawCircle(100, 100, 100);
	    twoColorImage.drawCircle(200, 200, 200);
	    twoColorImage.drawCircle(300, 300, 300);
	    twoColorImage.drawCircle(400, 400, 400);
	}
}

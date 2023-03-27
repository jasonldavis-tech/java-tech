package graphicsView.components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.concurrent.ConcurrentHashMap;

import performance.PerformanceUtil;

public class DrawCar {		
	private int carOffset = 0;
	private int methodCalls = 0;
	private int carOffsetBlue = 200;
	private PerformanceUtil performanceUtil = new PerformanceUtil();
	private int sinceLast = 0;
	
	private static ConcurrentHashMap<String, DrawCar> instances = new ConcurrentHashMap<>();
	
	public static DrawCar getInstance(String name) {
		DrawCar drawCar = instances.get(name);
		if (null!=drawCar) {
			return drawCar;
		} else {
			drawCar = new DrawCar();
			instances.put(name, drawCar);
			return drawCar;
		}
	}
	
	
	public void drawCarRed(Graphics2D g2D, int carVerticalOffset, int screenWidth) {		
		methodCalls+=1;
		carOffset+=1;
		// Draw Body of Car, RED
		g2D.setColor(Color.RED);
		int x = carOffset;
		int y = 500+carVerticalOffset;
		int width=75;
		int height=35;		
		g2D.fillRect(x,y,width,height);
		g2D.fillRect(x,y+15,width+20,20);
		
		// Draw Wheels of Car, Black
		g2D.setColor(Color.BLACK);
		g2D.fillOval(x+15, y+20, 25, 25);
		g2D.fillOval(x+50, y+20, 25, 25);
		
		if (carOffset>screenWidth) {
			carOffset=1;
		}
		
		if (carOffset==1 && !performanceUtil.isStopWatchStarted()) {
			performanceUtil.start();
			sinceLast = methodCalls;
		}
		if (carOffset==500 && performanceUtil.isStopWatchStarted()) {
			performanceUtil.stop();
			System.out.println("Recorded time to move 500 pixels: "+performanceUtil.getRecordedTimeMilliseconds()+" ms");
			System.out.println("Method calls total: "+methodCalls);
			int difference = methodCalls-sinceLast;
			System.out.println("Method calls difference: "+difference);
		}
	}
	
	public void drawCarBlue(Graphics2D g2D, int carVerticalOffset, int screenWidth) {
		carOffsetBlue-=1;
		// Draw Body of Car, Blue
		g2D.setColor(Color.BLUE);
		int x = carOffsetBlue;
		int y = carVerticalOffset;
		int width=75;
		int height=35;		
		g2D.fillRect(x,y,width,height);
		g2D.fillRect(x-20,y+15,width,20);
		
		// Draw Wheels of Car, Black
		g2D.setColor(Color.BLACK);
		g2D.fillOval(x+15, y+20, 25, 25);
		g2D.fillOval(x+50, y+20, 25, 25);	
		
		if (carOffsetBlue<0) {
			carOffsetBlue=screenWidth-100;
		}
	}
}

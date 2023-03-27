package graphicsView.components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class DrawBackgroundImage {
	int screenWidth;
	int screenHeight;
	
	public DrawBackgroundImage(final int screenWidth, final int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}
	
	public BufferedImage drawBackgroundImage() {
		BufferedImage backgroundBufferedImage = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2D = backgroundBufferedImage.createGraphics();
		
		setSceneToBlack(g2D);
		drawBasicGreenGround(g2D);		
		drawBasicBlueSky(g2D);
		
		drawDarkerGreenGround(g2D, 600);
		drawDarkBlueSky(g2D);
		drawClouds(g2D);
		
		drawRoads(g2D);
		drawMountain(g2D);
		drawSun(g2D);
		return backgroundBufferedImage;
	}
		
	public void setSceneToBlack(Graphics2D g2D) {
		g2D.setColor(Color.BLACK);
		g2D.fillRect(0, 0, screenWidth, screenHeight);
	}
	
	public void drawBasicGreenGround(Graphics2D g2D) {
		g2D.setColor(Color.GREEN); // Draw Green Ground Height 400 to 1000
		g2D.fillRect(0, 400, screenWidth, screenHeight);
	}	
	
	public void drawBasicBlueSky(Graphics2D g2D) {
		g2D.setColor(Color.BLUE); // Draw Blue Sky Height 0 to 400
		g2D.fillRect(0, 0, screenWidth, 400);
	}
	
	public void drawClouds(Graphics2D g2D) {
		drawCloud(g2D,500,100);
		drawCloud(g2D,900,100);
		drawCloud(g2D,1100,100);
		drawCloud(g2D,1400,100);
		
		drawCloud(g2D,700,200);
		drawCloud(g2D,1050,200);
		drawCloud(g2D,1200,200);
	}
	
	public void drawRoads(Graphics2D g2D) {
		drawRoad(g2D, 500);
		drawRoad(g2D, 575);
		drawRoad(g2D, 650);
	}
	
	public void drawDarkerGreenGround(Graphics2D g2D, int y) {
		g2D.setColor(new Color(0x003300));
		int x = 0;
		int width=screenWidth;
		int height=400;		
		g2D.fillRect(x,y,width,height);	
	}	
	
	public void drawDarkBlueSky(Graphics2D g2D) {
		g2D.setColor(new Color(0x000055));
		g2D.fillRect(0, 100, screenWidth, 300);
	}	
	
	public void drawLightBlueSky(Graphics2D g2D) {
		g2D.setColor(new Color(0xDDDDFF));
		g2D.fillRect(0, 100, screenWidth, 300);
	}
	
	public void drawSun(Graphics2D g2D) {
		g2D.setColor(new Color(0x2222FF));
		g2D.fillOval(25, 25, 300, 300);
		g2D.setColor(new Color(0x5555FF));
		g2D.fillOval(75, 75, 100, 100);
		g2D.setColor(Color.YELLOW);		
		g2D.fillOval(100, 100, 30, 30);
		g2D.setColor(new Color(0xFFFFFF));
		g2D.fillOval(105, 105, 20, 20);		
	}
	
	public void drawCloud(Graphics2D g2D, int xOffset, int yOffset) {
		g2D.setColor(Color.WHITE);
		int baseX = xOffset;
		int baseY = yOffset;
		int x[] = { baseX, baseX+50, baseX+100 };
		int y[] = { baseY, baseY+50, baseY };
		g2D.fillPolygon(x, y, 3);
	}
	
	public void drawMountain(Graphics2D g2D) {
		g2D.setColor(Color.CYAN);
		int x[] = { 100, 250, 400 };
		int y[] = { 400, 250, 400 };
		g2D.fillPolygon(x, y, 3);
	}
	
	public void drawRoad(Graphics2D g2D, int y) {
		g2D.setColor(new Color(0xCCCCCC));
		int x = 0;
		int width=screenWidth;
		int height=50;		
		g2D.fillRect(x,y,width,height);
		g2D.setColor(new Color(0x000000));
		g2D.fillRect(x, y+20, width, 1);
	}

}

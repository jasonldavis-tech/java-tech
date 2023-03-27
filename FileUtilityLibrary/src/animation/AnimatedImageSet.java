package animation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Logger;

import file.FileUtil;

public class AnimatedImageSet {
	private static Logger logger = Logger.getLogger(AnimatedImageSet.class.toString());		
	
	private int width;
	private int length;
	private String directoryOfAnimation;
	private String fileName;
	
	public AnimatedImageSet(int width, int length, String fileName) {
		this.width = width;
		this.length = length;
		this.directoryOfAnimation = "animation-"+System.nanoTime();
		this.fileName = fileName;
		
		String outputDirectory = FileUtil.getWorkingPath()+"\\src\\assets\\"+directoryOfAnimation;
		File file = new File(outputDirectory);
		file.mkdir();			
	}
	
	public void generateAnimation(int length) {
		int x=0,y=0;
		for (int i=0; i<length; i++) {
			createFrame(x,y,fileName,i);
			x=i;
			y=i;
		}
	}
	
	private void createFrame(int x, int y, String fileName, int frameNumber) {
		try {
			String workingDirectory = FileUtil.getWorkingPath();
			String outputFile = workingDirectory+"\\src\\assets\\"+directoryOfAnimation+"\\"+fileName+"-Frame"+frameNumber+".bmp";
			
			BufferedImage bufferedImage = new BufferedImage(width, length, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = bufferedImage.createGraphics();
			graphics2D.setBackground(Color.BLACK);
			graphics2D.setColor(Color.CYAN);
			graphics2D.draw(new Rectangle(10, 10, x+10, y+10));
			
			FileUtil.writeBitmap(bufferedImage, outputFile);
			
		} catch (Exception e) {
			logger.severe("Exception: "+e.getMessage());
		}		
	}
	
	public static void createStringToImageTest(String filename) {		
		BufferedImage bufferedImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2D = bufferedImage.createGraphics();
		
		g2D.setBackground(Color.BLACK);
		g2D.setColor(Color.CYAN);
		g2D.drawString("Test Title", 10, 10);
		g2D.drawString("1. First Line of Content", 10, 20);
		g2D.drawString("2. First Line of Content", 10, 20);	
		
		writeImage(filename, bufferedImage);		
	}
	
	private static void writeImage(String filename, BufferedImage bufferedImage) {
		try {
		String workingDirectory = FileUtil.getWorkingPath();
		String outputFile = workingDirectory+"\\src\\assets\\StringToImageTest\\"+filename+".bmp";
		FileUtil.writeBitmap(bufferedImage, outputFile);
		} catch (Exception e) {
			logger.severe("Exception: "+e.getMessage());
		}
	}
}

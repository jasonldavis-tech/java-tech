package animation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Logger;

import file.FileUtil;

public class TextToImageUtil {
	private static Logger logger = Logger.getLogger(TextToImageUtil.class.toString());		

	public static void createStringToImageTest(String filename) {		
		BufferedImage bufferedImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2D = bufferedImage.createGraphics();
		
		g2D.setBackground(Color.BLACK);
		g2D.setColor(Color.CYAN);
	
		Font font = new Font("Arial", Font.PLAIN, 20);
		g2D.setFont(font);
		
		int offset = 40;
		
		g2D.drawString("Test Title", offset, offset);
		g2D.drawString("1. First Line of Content", offset, 2*offset);
		g2D.drawString("2. Second Line of Content", offset, 3*offset);
		g2D.drawString("3. Third Line of Content", offset, 4*offset);
		g2D.drawString("4. Fourth Line of Content", offset, 5*offset);
		
		writeImage(filename, bufferedImage);		
	}
	
	public static void createBlackCyanListImage(String filename, String title, ArrayList<String> items) {
		createNumberedListImage(filename, title, items, Color.BLACK, Color.CYAN);
	}
	
	public static void createNumberedListImage(String filename, String title, ArrayList<String> items, Color colorBackground, Color colorText) {
		BufferedImage bufferedImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2D = bufferedImage.createGraphics();
		
		g2D.setBackground(colorBackground);
		g2D.setColor(colorText);
	
		Font font1 = new Font("Arial", Font.PLAIN, 20);
		g2D.setFont(font1);
		
		int offset = 40;
		
		LineMetrics lineMetrics = font1.getLineMetrics(title, g2D.getFontRenderContext());
		
		g2D.drawString(title, offset, offset);
		
		Rectangle2D rect2D = font1.getStringBounds(title, g2D.getFontRenderContext());
		// lineWidth = 100;
		// Draw Underline
		g2D.setStroke(new BasicStroke(2));
		/* g2D.drawLine(offset, offset+7, 
				offset+(int)rect2D.getWidth(), 
				offset+7); */
		
		Font font2 = new Font("Arial", Font.PLAIN, 18);
		g2D.setFont(font2);				
		
		int maxWidth = 0;
		for (int i=0; i<items.size(); i++) {
			int position = i+1;
			g2D.drawString(""+position+". "+items.get(i), offset, (position+1)*offset);
			int currentWidth = (int)font1.getStringBounds(items.get(i), g2D.getFontRenderContext()).getWidth();
			if (currentWidth>maxWidth) {
				maxWidth = currentWidth;
			}
		}
		
		g2D.drawRect(offset-10, offset+10, offset+maxWidth+10, offset*items.size());
		
		writeImage(filename, bufferedImage);				
	}
	
	private static void writeImage(String filename, BufferedImage bufferedImage) {
		try {
		String workingDirectory = FileUtil.getWorkingPath();
		String outputFile = workingDirectory+"\\src\\assets\\"+filename+".bmp";
		FileUtil.writeBitmap(bufferedImage, outputFile);
		} catch (Exception e) {
			logger.severe("Exception: "+e.getMessage());
		}
	}
}

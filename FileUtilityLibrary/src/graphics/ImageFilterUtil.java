package graphics;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.Hashtable;
import java.util.logging.Logger;

public class ImageFilterUtil {
	private static Logger logger = Logger.getLogger(ImageFilterUtil.class.getName());
	
	public static BufferedImage copyImageStartingAtHorizontalOffset(final BufferedImage bufferedImage, 
			int offset,
			int length) {
		// Appears to work faster than setData at least currently
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		Raster raster = bufferedImage.getData();
		WritableRaster writableRaster = bufferedImage.getData().createCompatibleWritableRaster();
		for (int y=0; y<height; y++) {
			int row[] = new int[width];
			raster.getDataElements(0, y, width, 1, row);
			writableRaster.setDataElements(0,y, width,1, row);
		}
		
		BufferedImage filteredImage = new BufferedImage(bufferedImage.getColorModel(), writableRaster, false, new Hashtable<>());
		return filteredImage;
	}
	
	public static BufferedImage copyImage(final BufferedImage bufferedImage) {
		// Appears to work faster than setData at least currently
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		Raster raster = bufferedImage.getData();
		WritableRaster writableRaster = bufferedImage.getData().createCompatibleWritableRaster();
		for (int y=0; y<height; y++) {
			int row[] = new int[width];
			raster.getDataElements(0,y,width,1,row);
			writableRaster.setDataElements(0,y,width,1,row);
		}
		
		BufferedImage filteredImage = new BufferedImage(bufferedImage.getColorModel(), writableRaster, false, new Hashtable<>());
		return filteredImage;
	}
	
	public static BufferedImage filterImageAdd(final BufferedImage bufferedImage, int offset) {
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		Raster raster = bufferedImage.getData();
		WritableRaster writableRaster = bufferedImage.getData().createCompatibleWritableRaster();
		for (int y=0; y<height; y++) {
			int row[] = new int[width];
			int writeRow[] = new int[width];
			raster.getDataElements(0, y, width, 1, row);
			writableRaster.getDataElements(0, y, width, 1, writeRow);
			for (int i=0; i<row.length; i++) {
				writeRow[i] = row[i]+offset;
			}
			writableRaster.setDataElements(0,y,width,1, writeRow);
		}
		
		BufferedImage filteredImage = new BufferedImage(bufferedImage.getColorModel(), writableRaster, false, new Hashtable<>());
		return filteredImage;
	}
	
	public static BufferedImage filterImageSubtract(final BufferedImage bufferedImage, int offset) {
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		Raster raster = bufferedImage.getData();
		WritableRaster writableRaster = bufferedImage.getData().createCompatibleWritableRaster();
		for (int y=0; y<height; y++) {
			int row[] = new int[width];
			int writeRow[] = new int[width];
			raster.getDataElements(0, y, width, 1, row);
			writableRaster.getDataElements(0, y, width, 1, writeRow);
			for (int i=0; i<row.length; i++) {
				writeRow[i] = row[i]-offset;
			}
			writableRaster.setDataElements(0,y,width,1, writeRow);
		}
		
		BufferedImage filteredImage = new BufferedImage(bufferedImage.getColorModel(), writableRaster, false, new Hashtable<>());
		return filteredImage;
	}	
}

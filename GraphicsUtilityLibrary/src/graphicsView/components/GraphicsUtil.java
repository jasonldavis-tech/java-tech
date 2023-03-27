package graphicsView.components;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.formats.bmp.BmpImageParser;
import org.apache.commons.imaging.formats.bmp.BmpImagingParameters;
import org.apache.commons.imaging.formats.jpeg.JpegImageParser;
import org.apache.commons.imaging.formats.jpeg.JpegImagingParameters;
import org.apache.commons.imaging.formats.tiff.TiffImageParser;
import org.apache.commons.imaging.formats.tiff.TiffImagingParameters;
import org.apache.commons.imaging.formats.tiff.constants.TiffConstants;

import javafx.scene.image.Image;
import performance.PerformanceUtil;

public class GraphicsUtil {
	private static Logger logger = Logger.getLogger(GraphicsUtil.class.getName());
	
	
	// Method is running some what slow 60ms in a multithreaded loop, ImageIO
	// might use multithreading of its own thus extra threads might compete
	//
	// Example Output on timing
	/* 1850 ms (this method called 30 times writing out to a ConcurrentQueue */
	public static Image convertBufferedImageToImage(BufferedImage bufferedImage) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();	
			PerformanceUtil perfUtil4 = new PerformanceUtil();
			perfUtil4.startInstant();
			ImageIO.write(bufferedImage, "BMP", baos);
			perfUtil4.stopInstant();
			System.out.println("Method ImageIO.write required "+perfUtil4.getInstantDifferenceMilliseconds()+" ms to complete.");
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			Image image = new Image(bais);
			return image;
		} catch (IOException e) {
			logger.severe("Error converting BufferedImage to Image");
			return null;
		}
	}
	
	public static Image convertBufferedImageToImageUsingApacheCommons(BufferedImage bufferedImage) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();	
			PerformanceUtil perfUtil4 = new PerformanceUtil();
			perfUtil4.startInstant();
			BmpImagingParameters bmpImagingParameters = new BmpImagingParameters();
			(new BmpImageParser()).writeImage(bufferedImage, baos, bmpImagingParameters);
			
			perfUtil4.stopInstant();
			System.out.println("Method Apache Commons BmpImageParser.write() required "+perfUtil4.getInstantDifferenceMilliseconds()+" ms to complete.");
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			Image image = new Image(bais);
			return image;
		} catch (IOException e) {
			logger.severe("Error converting BufferedImage to Image");
			return null;
		} catch (ImageWriteException e) {
			logger.severe("Error writing BMP Image");
			return null;			
		}
	}
	
	public static Image convertBufferedImageToImageUsingApacheCommonsJPG(BufferedImage bufferedImage) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();	
			PerformanceUtil perfUtil4 = new PerformanceUtil();
			perfUtil4.startInstant();
			JpegImagingParameters jpegImagingParameters = new JpegImagingParameters();
			(new JpegImageParser()).writeImage(bufferedImage, baos, jpegImagingParameters);			
			perfUtil4.stopInstant();
			System.out.println("Method Apache Commons JpegImageParser.write() required "+perfUtil4.getInstantDifferenceMilliseconds()+" ms to complete.");
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			Image image = new Image(bais);
			return image;
		} catch (IOException e) {
			logger.severe("Error converting BufferedImage to Image");
			return null;
		} catch (ImageWriteException e) {
			logger.severe("Error writing Jpeg Image");
			return null;			
		}
	}
	
	public static Image convertBufferedImageToImageUsingApacheCommonsTiff(BufferedImage bufferedImage) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();	
			PerformanceUtil perfUtil4 = new PerformanceUtil();
			perfUtil4.startInstant();
			TiffImagingParameters tiffImagingParameters = new TiffImagingParameters();
			tiffImagingParameters.setCompression(TiffConstants.TIFF_COMPRESSION_UNCOMPRESSED);
			(new TiffImageParser()).writeImage(bufferedImage, baos, tiffImagingParameters);			
			perfUtil4.stopInstant();
			System.out.println("Method Apache Commons TiffImageParser.write() required "+perfUtil4.getInstantDifferenceMilliseconds()+" ms to complete.");
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			Image image = new Image(bais);
			return image;
		} catch (IOException e) {
			logger.severe("Error converting BufferedImage to Image");
			return null;
		} catch (ImageWriteException e) {
			logger.severe("Error writing Jpeg Image");
			return null;			
		}
	}
}

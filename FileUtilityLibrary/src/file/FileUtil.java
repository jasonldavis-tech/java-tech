package file;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import data.xmlImageFormat.XmlImage;

public class FileUtil {
	private static Logger logger = Logger.getLogger(FileUtil.class.toString());
	
	public static String getWorkingPath() {
		File file = new File("");
		String path = file.getAbsolutePath().toString();
		return path;
	}
	
	public static String readFileAsString(String fileName) throws FileNotFoundException, IOException {
		final StringWriter stringWriter = new StringWriter();
		
		File file = new File(fileName);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		stringWriter.append(new String(bis.readAllBytes()));
		bis.close();
		
		return stringWriter.toString();
	}
	
	public static BufferedImage readJpg(String fileName)throws IOException
	{
		File file = new File(fileName);
		BufferedImage bufferedImage = ImageIO.read(file);
		return bufferedImage;		
	}
	
	public static void writeFile(String fileName, String fileAsString) throws IOException {
		File file = new File(fileName);
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write(fileAsString);
		fileWriter.close();
		
		logger.info("Wrote to "+fileName+" with "+fileAsString.getBytes().length+" bytes.");
	}
	
	public static void writeBitmap(RenderedImage renderedImage, String fileName) throws IOException {
		File file = new File(fileName);		
		ImageIO.write(renderedImage, "BMP", file);
	}
	
	public static void main(String args[]) {
		String basePath = "C:\\Users\\jason\\eclipse-workspace\\FileUtilityLibrary\\";
		String testPath = basePath+"target\\classes";
		String srcPath = basePath+"src\\";
		
		try {
			BufferedImage bufferedImage = readJpg(testPath+"\\main\\resources\\DuncanvilleClouds_Small.jpg");
			logger.info("JPG read");
			Raster raster = bufferedImage.getData();
			int firstPixel[] = new int[3];
			raster.getPixel(0, 0, firstPixel);
			logger.info(firstPixel[0]+","+firstPixel[1]+","+firstPixel[2]);
			// Get pixel half way on first line
			int secondPixel[] = new int[3];
			raster.getPixel(raster.getWidth()/2, 0, secondPixel);
			logger.info(secondPixel[0]+","+secondPixel[1]+","+secondPixel[2]);
			XmlImage testImage = new XmlImage(raster);
			logger.info("XML Form of Data: "+testImage.getXMLFormOfImage());
			// Careful Example file of Xml (9 mb) freezes eclipse in 2023 - significant bug I would think, though opening
			// that big of xml file might be considered rare, design view might not be equipped to handle
			FileUtil.writeFile(srcPath+"main\\resources\\DuncanvilleCloudsImageAsXml.xml",testImage.getXMLFormOfImage());
			byte byteArray[] = testImage.getZippedXmlImage();
			logger.info("zipped length: "+byteArray.length);
		} catch (IOException e) {
			logger.severe(e.getMessage());
		}
	}
}

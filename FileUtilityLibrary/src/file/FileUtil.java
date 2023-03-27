package file;

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
}

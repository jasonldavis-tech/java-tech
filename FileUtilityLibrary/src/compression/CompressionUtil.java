package compression;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class CompressionUtil {
	private static Logger logger = Logger.getLogger(CompressionUtil.class.getName());
	
	public static byte [] zipString(String data) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		try {
			ZipEntry ze = new ZipEntry("String Data");
			zos.putNextEntry(ze);
			zos.write(data.getBytes());
			zos.closeEntry();
			zos.close();
			return baos.toByteArray();
		} catch (IOException e) {
			logger.info("IOException Zipping String Data: "+e.getMessage());
		}
		return null;
	}
	
	public static String unzipString(byte data[]) {
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		ZipInputStream zis = new ZipInputStream(bais);
		
		try {
			ZipEntry ze = zis.getNextEntry();
			byte buffer[] = zis.readAllBytes();
			String uncompressedData = new String(buffer);
			return uncompressedData;
		} catch (IOException e) {
			logger.info("IOException Unzipping String Data: "+e.getMessage());
		}
		return null;
	}
	
	public static void main(String[] args) {
		String test = "This is a test string that can be compressed. For some reason it is not working.";
		for (int i=0; i<100; i++) {
			test += " More data added for test case.";
		}
		logger.info("Uncompressed Original String: "+test);
		logger.info("Uncompressed data length: "+test.getBytes().length);
		byte zippedData[] = zipString(test);
		logger.info("Zipped data length: "+zippedData.length);
		String uncompressedString = unzipString(zippedData);
		logger.info("String after unzipped: "+uncompressedString);
		if (test.compareTo(uncompressedString)==0) {
			logger.info("Original Test Data matches Uncompressed Data");
		}
	}
}

package file;

import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Logger;

public class HtmlUtil {
	private static Logger logger = Logger.getLogger(HtmlUtil.class.toString());
	
	public static String createHtmlParagraphsFromEndlines(String message) {
		String lines[] = message.split("\n");
		
		final StringWriter htmlWriter = new StringWriter();
		
		for (int i=0; i<lines.length; i++) {
			htmlWriter.append("<p>");
			htmlWriter.append(lines[i]);
			htmlWriter.append("</p>");
		}
		
		return htmlWriter.toString();
	}

	public static String surroundInParagraphTag(String message) {
		final StringWriter htmlWriter = new StringWriter();

		htmlWriter.append("<p>");
		htmlWriter.append(message);
		htmlWriter.append("</p>");

		return htmlWriter.toString();
	}
	
	public static void createHtmlFile(String filename, String title, String message) {
		final StringWriter htmlWriter = new StringWriter();

		htmlWriter.append("<html>");
		htmlWriter.append("<head><title>"+title+"</title></head>");
		htmlWriter.append("<body>");
		htmlWriter.append(message);
		htmlWriter.append("</body>");
		htmlWriter.append("</html>");

		try {
			FileUtil.writeFile(filename, htmlWriter.toString());
		} catch (IOException e) {
			logger.severe("Error writing HTML file: "+e.getMessage());
		}
	}
}

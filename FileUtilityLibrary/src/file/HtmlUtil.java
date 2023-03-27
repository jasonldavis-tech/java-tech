package file;

import java.io.StringWriter;

public class HtmlUtil {
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
}

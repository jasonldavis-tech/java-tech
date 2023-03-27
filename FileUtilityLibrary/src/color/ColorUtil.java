package color;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import random.RandomUtil;

public class ColorUtil {
	private static ArrayList<String> htmlHexColors;
	
	public static ArrayList<String> getHtmlHexColorsSet() {
		if (null != htmlHexColors) {
			return htmlHexColors;
		}
		
		String arrayOfHtmlHexColors[] = { 
				"#000000", // Black
				"#FF0000", // Red
				"#00FF00", // Green
				"#0000FF"  // Blue
		};
		
		
		htmlHexColors = new ArrayList<>(Arrays.asList(arrayOfHtmlHexColors));
		
		return htmlHexColors;
	}
	
	public static String getRandomEncouragingPhrase() {
		String encouragingPhrase = random.RandomUtil.getRandomElementInListOfStrings(getHtmlHexColorsSet());
		return encouragingPhrase;
	}
	
	public static String encloseStringInParagraphOfRandomColor(final String content) {
		final StringWriter stringWriter = new StringWriter();
		
		String randomColor = RandomUtil.getRandomElementInListOfStrings(getHtmlHexColorsSet());
		String backgroundColor = "#222222";
		if (randomColor.compareTo("#000000")==0) {
			backgroundColor="#FFFFFF";
		}
		stringWriter.append("<p style=\"");
		stringWriter.append("color:"+randomColor);
		stringWriter.append(";");
		stringWriter.append("background-color:"+backgroundColor);
		stringWriter.append("\">");
		stringWriter.append(content);
		stringWriter.append("</p>");
		
		return stringWriter.toString();
	}
}

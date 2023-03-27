package support;

import java.io.StringWriter;
import java.util.ArrayList;

public class ListToFormattedStringUtil {

	public static String listToFormattedString(ArrayList<String> listOfStrings) {
		final StringWriter formattedString = new StringWriter();
			
		listOfStrings.forEach((element) -> {
			formattedString.append(element+", ");
		});
		
		String formattedReturnString = formattedString.toString();
		formattedReturnString=formattedReturnString.substring(0,formattedReturnString.length()-2);			
		
		return formattedReturnString.toString();
	}
}

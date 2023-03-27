package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class CommonUtil {
	private static Logger logger = Logger.getLogger(CommonUtil.class.toString());
	
	public static String readFromInput() {
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader inputReader = new BufferedReader(inputStreamReader);
		
		String value = null;
		try {
			value = inputReader.readLine();
		} catch (IOException e) {
			logger.severe("IOException reading input "+e.getMessage());
		}
		return value;
	}
}

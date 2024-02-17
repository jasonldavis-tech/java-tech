package color;

public class AnsiColorUtil {
	public static String ESCAPE="\u001B[";
	public static String GRAY=ESCAPE+"37m";
	public static String RED="\u001B[31m";
	public static String BLUE="\u001B[34m";
	public static String GREEN="\u001B[32m";
	
	public static String DEFAULT="\u001B[0m";
	
	public static String stripAsciiColorsFromString(String message) {
		message = message.replace(RED, "");
		message = message.replace(BLUE, "");
		message = message.replace(GREEN, "");
		message = message.replace(DEFAULT, "");
		return message;
	}
}

package data.xmlImageFormat;

import java.util.logging.Logger;

public class RgbColor {
	private static Logger logger = Logger.getLogger(RgbColor.class.toString());
	
	short red;
	short green;
	short blue;

	public RgbColor(int red, int green, int blue) {
		// TODO Auto-generated constructor stub
		this.red = Short.valueOf(""+red);
		this.green = Short.valueOf(""+green);
		this.blue = Short.valueOf(""+blue);		
	}
	
	// Form of "255,0,0" = Red, "0,0,255" = Blue
	public RgbColor(String rgbValues) {
		String values[] = rgbValues.split(",");
		if (values.length==3) {
			red = Short.valueOf(values[0]);
			green = Short.valueOf(values[1]);
			blue = Short.valueOf(values[2]);
		} else {
			logger.severe("Error parsing RGB Value");
		}		 
	}
	
	@Override
	public String toString() {
		String rgbString = red+","+green+","+blue;
		return rgbString;
	}

}

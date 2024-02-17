package math;

import java.util.logging.Logger;

public class SubtractionUtil {
	private static Logger logger = Logger.getLogger(SubtractionUtil.class.getName());

	public SubtractionUtil() {
		// TODO Auto-generated constructor stub
	}
	
	// Motivation, not all memory is ECC (transient bit flips likely could be problematic)
	// Verified additions matter
	public static int verifiedSubtract(int a, int b) {
		int c1 = a-b;
		int doubled = (2*a)-(2*b);
		int c2 = doubled/2;
		double dA = (double) a;
		double dB = (double) b;
		int c3 = (int) (dA-dB);
		
		if (c1==c2&&c2==c3) {
			return c1;
		} else {
			String errorMessage = "Verified subtract failed to subtract correctly, a= "+a+", b="+b+", c1="+c1+", c2="+c2+", c3="+c3;
			logger.severe(errorMessage);
			throw new RuntimeException(errorMessage);
		}
	}	

}

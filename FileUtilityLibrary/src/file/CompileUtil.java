package file;

import java.util.logging.Logger;

public class CompileUtil {
	private static Logger logger = Logger.getLogger(ScriptUtil.class.getName());
	
	public static void compileUsingOneApiIcx(String projectDirectory, String filename) {

		String executablePath = "C:\\Program Files (x86)\\Intel\\oneAPI\\compiler\\2023.0.0\\windows\\bin\\icpx.exe";
		String cppPath = projectDirectory+"\\"+filename;
		
		String commands[] = {
				executablePath,
				"-fsycl",
				cppPath,
				"-o",
				projectDirectory+"\\"+"test.exe"
		};
		
		ProcessUtil.runProcessAndRedirectOutput(commands, 10000); // Timesout after 10 seconds
	}
}

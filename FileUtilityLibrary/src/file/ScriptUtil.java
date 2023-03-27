package file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

import performance.PerformanceUtil;

public class ScriptUtil {
	private static Logger logger = Logger.getLogger(ScriptUtil.class.getName());
	
	public static void writePythonScript(String filename, String script) {
		File file = new File(filename);

		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(script);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void generatePythonScript(String filename) {
		final StringWriter stringWriter = new StringWriter();
		stringWriter.append("for i in range(100000):\n");
		stringWriter.append(" print('Never Say Die!')\n");
		stringWriter.append("exit");
		
		writePythonScript(filename, stringWriter.toString());
	}
	
	public static void runPythonScript(String filename) {

		String pythonExecutablePath = "C:\\Windows\\py.exe";
		String pythonScriptPath = FileUtil.getWorkingPath()+"\\"+filename;
		
		try {
			String commands[] = { 
					pythonExecutablePath,
					pythonScriptPath };
			ProcessBuilder processBuilder = new ProcessBuilder(Arrays.asList(commands));
			processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
			PerformanceUtil.getInstance().start();
			Process process = processBuilder.start();
			CompletableFuture<Process> processComplete = process.onExit();
			int i=0;
			while (!processComplete.isDone() || i>20) {
				try {
					i++;
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (processComplete.isDone()) {
				PerformanceUtil.getInstance().stop();
				long timeInNanoseconds = PerformanceUtil.getInstance().getRecordedTime();
				logger.info("Time to Run Python Script using ProcessBuilder Class in Java:"+timeInNanoseconds+" nanoseconds");
			} else {
				logger.info("Process timed out");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}

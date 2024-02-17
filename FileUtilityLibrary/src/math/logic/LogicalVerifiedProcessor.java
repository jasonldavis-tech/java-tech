package math.logic;

import java.util.ArrayList;
import java.util.List;

public class LogicalVerifiedProcessor {
	
	private List<String> instructions = new ArrayList<>();

	public LogicalVerifiedProcessor() {
		// TODO Auto-generated constructor stub
	}
	
	public void addInstruction(String instruction) {
		instructions.add(instruction);
	}
	
	public void runInstruction(String instruction) {
		String words[] = instruction.split(instruction);
		
		switch (words[0]) {
			case "add":
				break;
			case "subtract":
				break;
			case "multiply":
				break;
			case "divide":
				break;
		}
	}
	
	public void run() {
		instructions.stream().forEach((instruction) -> {
			runInstruction(instruction);
		});
	}

}

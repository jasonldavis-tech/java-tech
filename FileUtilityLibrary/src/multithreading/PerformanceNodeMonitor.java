package multithreading;

import java.util.Vector;

// Able to measure performance of nodes running tasks in real time based
// Upon current network topology can be powerful.
public class PerformanceNodeMonitor {
	
	Vector<ProcessingNode> processingNodes;

	public PerformanceNodeMonitor() {
		// TODO Auto-generated constructor stub
	}
	
	public void sendStayAliveMessages() {
		processingNodes.forEach((node) -> {
			node.sendStayAliveMessage();
		});
	}
	
	public ProcessingNode getBestChoiceForNextTaskProcessing() {
		// TODO: update to send tasks based upon previously sent tasks
		// and current impression of what Network Performance looks like
		
		return null;
	}
	
	public void sendTask(String taskMessage) {
		ProcessingNode node = getBestChoiceForNextTaskProcessing();
		
		node.sendTask(taskMessage);
	}

}

package multithreading;

import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import performance.PerformanceUtil;
import random.RandomUtil;

public class ProcessingNode {
	private static Logger logger = Logger.getLogger(ProcessingNode.class.getName());

	// Nodes online are nice, useful, not guaranteed
	
	String nodeId;
	String name;
	String description;
	String hostname;
	String ipAddress;
	int processingCores;
	int speedToProcessOneMillionAdditionsInMilliseconds;
	int speedToProcessOneBillionAdditionsInMilliseconds;
	int roundTripLatencyForOneInstruction;
	int timeElapsedSinceLastStayAliveCheck;
	long lastTimeTaskSent;
	long tasksProcessed;
	long numberOfTasksWaitingForReturn;
	boolean online;
	boolean canAcceptBatchOfTasks; // can high number of tasks be sent?
	int stayAlivesSent; // if 3 stay alive messages sent with no response set online to false
	Vector<RemoteTask> tasksInMotion = new Vector<>();
	
	public ProcessingNode() {
		// Establish baseline speed of additions
		processOneMillionAdds();
	}
	
	public void sendStayAliveMessage() {
		// TODO Send Stay Alive Message
		stayAlivesSent++;
	}
	
	public void sendTask(String taskMessage) {
		RemoteTask remoteTask = new RemoteTask(taskMessage);
		tasksInMotion.add(remoteTask);
	}
	
	public void acceptResponse(String responseMessage) {
		// TODO Accept Response of Task
		
		// TODO Get Task ID from Response Message
		String taskId = "";
		// TODO Get Result from Response Message
		String result = "";
		
		List<RemoteTask> matchingTasks = tasksInMotion.stream().filter((task) -> (task.getId().compareTo(taskId) == 0)).collect(Collectors.toList());
		if (matchingTasks.size()==1) {
			matchingTasks.get(0).setResult(result);
		} else {
			// TODO possibly throw error should only be one matching task
		}
	}
	
	public StayAliveResponse handleStayAliveRequest() {
		StayAliveResponse response = new StayAliveResponse(nodeId, processingCores, speedToProcessOneMillionAdditionsInMilliseconds);
		
		return response;
	}
	
	// Currently tests single core performance, need a version to split amongst all the cores
	public void processOneMillionAdds() {
		PerformanceUtil perfUtil = new PerformanceUtil();
		perfUtil.startInstant();
		int sum = 0;
		int a, b;
		int verify = 0;
		int doubled = 0;
		for (int i=0; i<1_000_000; i++) {
			a = RandomUtil.getRandomNumber(0, 9);
			b = RandomUtil.getRandomNumber(0, 9);
			sum = a+b;
			verify = (2*a)+(2*b);
			doubled = sum*2;
			if (verify!=doubled) {
				throw new RuntimeException("Addition did not verify correctly");
			}
		}
		perfUtil.stopInstant();
		speedToProcessOneMillionAdditionsInMilliseconds = (int) perfUtil.getInstantDifferenceMilliseconds();
		logger.info("Time required to process one billion additions: "+speedToProcessOneBillionAdditionsInMilliseconds);
	}	
	
	public void processOneBillionAdds() {
		PerformanceUtil perfUtil = new PerformanceUtil();
		perfUtil.startInstant();
		int sum = 0;
		int a, b;
		int verify = 0;
		int doubled = 0;
		for (int i=0; i<1_000_000_000; i++) {
			a = RandomUtil.getRandomNumber(0, 9);
			b = RandomUtil.getRandomNumber(0, 9);
			sum = a+b;
			verify = (2*a)+(2*b);
			doubled = sum*2;
			if (verify!=doubled) {
				throw new RuntimeException("Addition did not verify correctly");
			}
		}
		perfUtil.stopInstant();
		speedToProcessOneBillionAdditionsInMilliseconds = (int) perfUtil.getInstantDifferenceMilliseconds();
		logger.info("Time required to process one billion additions: "+speedToProcessOneBillionAdditionsInMilliseconds);
	}

}

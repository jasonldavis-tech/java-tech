package multithreading;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

import random.RandomUtil;

public class RemoteTask {
	private static AtomicInteger instanceId = new AtomicInteger();
	private static String runtimeId = RandomUtil.getRandomNumberString(10);
	
	private String id;
	public String getId() {
		return id;
	}

	private String task;
	
	private Instant taskCreated;
	private Instant taskCompleted;

	public RemoteTask(String task) {
		this.task = task;
		
		// TODO Generate Unique ID
		taskCreated = Instant.now();
		
		this.id = runtimeId+"-"+instanceId.getAndIncrement();
	}
	
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
		taskCompleted = Instant.now();
	}
}

package multithreading;

import java.time.Instant;

public class StayAliveResponse {
	String nodeId;
	int processingCores;
	int averageProcessingTimeMillisecondsOneMillionAdds;
	Instant timestamp;
	
	public StayAliveResponse(String nodeId, int processingCores, int averageProcessingTimeMilliseconds) {
		this.nodeId = nodeId;
		this.processingCores = processingCores;
		this.averageProcessingTimeMillisecondsOneMillionAdds = averageProcessingTimeMilliseconds;
		timestamp = Instant.now();
	}
	
	public String getNodeId() {
		return nodeId;
	}

	public int getProcessingCores() {
		return processingCores;
	}

	public int getAverageProcessingTimeMillisecondsOneMillionAdds() {
		return averageProcessingTimeMillisecondsOneMillionAdds;
	}

	public Instant getTimestamp() {
		return timestamp;
	}
}

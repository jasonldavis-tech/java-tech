package process;

public class ProcessComplete {
	private boolean complete=false;
	private boolean success=false;

	public ProcessComplete(boolean complete, boolean success) {
		this.complete=complete;
		this.success=success;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public boolean isComplete() {
		return complete;
	}
}

package multithreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ThreadUtil {
	private static Logger logger = Logger.getLogger(ThreadUtil.class.toString());
	private static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(6);
	private static ThreadPoolExecutor threadPoolExecutor;
	
	
	public static BlockingQueue<Runnable> getWorkQueue() {
		if (null == workQueue) {
			workQueue = new ArrayBlockingQueue<>(6);
		}
		return workQueue;
	}
	
	public static ThreadPoolExecutor getThreadPool() {
		if (null == threadPoolExecutor) {
			threadPoolExecutor = new ThreadPoolExecutor(6, 12, 1000, TimeUnit.MILLISECONDS, getWorkQueue());
		}
		
		return threadPoolExecutor;
	}
	
	private static boolean useVirtualThreads = true;
	
	public static void addTask(Runnable task) {
		if (useVirtualThreads) {
			Thread.startVirtualThread(task);
		} else {		
			getThreadPool().submit(task);
		}
	}
	
	public static void exitingApp() {
		getThreadPool().shutdownNow();
	}
}

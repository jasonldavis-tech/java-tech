package support;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.logging.Logger;

import multithreading.ThreadUtil;

public class FaultToranceUtil {
	private static Logger logger = Logger.getLogger(FaultToranceUtil.class.getName());
	
	public static <T,U,R> R tripleModularRedundancySequential(BiFunction<T, U, R> method1,
			BiFunction<T, U, R> method2,
			BiFunction<T, U, R> method3,
			T param1, U param2) throws DisagreementException {
		// TODO: Create Triple Modular Redundancy class that tries all three methods
		// and gives if two values agree and errors if not
		
		R valueFromMethod1 = method1.apply(param1, param2);
		R valueFromMethod2 = method2.apply(param1, param2);
		R valueFromMethod3 = method3.apply(param1, param2);
		
		if (valueFromMethod1==valueFromMethod2 && valueFromMethod2==valueFromMethod3) {
			return valueFromMethod1;
		} else if (valueFromMethod1==valueFromMethod2 && valueFromMethod2!=valueFromMethod3) {
			
			logger.info("Alert! Value from method3 is different than method1 and method2");
			return valueFromMethod1;
		} else if (valueFromMethod1!=valueFromMethod2 && valueFromMethod2==valueFromMethod3) {
			
			logger.info("Alert! Value from method1 is different than method2 and method3");
			return valueFromMethod2;
		} else if (valueFromMethod1==valueFromMethod3 && valueFromMethod2==valueFromMethod3) {
			
			logger.info("Alert! Value from method2 is different than method1 and method3");
			return valueFromMethod1;
		} else {
			logger.severe("Consensus on value computed can not be made, throwing disagreement exception");
			throw new DisagreementException("Consensus on value computed can not be made, throwing disagreement exception");
		}		
	}
	
	public static <T,U,R> R tripleModularRedundancyParallel(BiFunction<T, U, R> method1,
			BiFunction<T, U, R> method2,
			BiFunction<T, U, R> method3,
			T param1, U param2) throws DisagreementException {
		// TODO: Create Triple Modular Redundancy class that tries all three methods
		// and gives if two values agree and errors if not

		ConcurrentHashMap<String, R> values = new ConcurrentHashMap<>();
		
		Runnable task1 = () -> {
			R valueFromMethod1 = method1.apply(param1, param2);
			values.put("method1",valueFromMethod1);
		};
		Runnable task2 = () -> {
			R valueFromMethod2 = method2.apply(param1, param2);
			values.put("method2",valueFromMethod2);
		};
		Runnable task3 = () -> {
			R valueFromMethod3 = method3.apply(param1, param2);
			values.put("method3",valueFromMethod3);
		};
		
		ThreadUtil.addTask(task1);
		ThreadUtil.addTask(task2);
		ThreadUtil.addTask(task3);
		
		boolean waiting=true;
		int timeoutMilliseconds=10000;
		int numberOfIterations=timeoutMilliseconds/50;
		int i=0;
		while (waiting) {
			i++;
			if (values.size()==3 || i>=numberOfIterations) {
				waiting = false;
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				logger.info("Thread interrupted: "+e.getMessage());
			}
		}
		
		if (values.size() == 0 || values.size() == 1) {
			logger.severe("Consensus on value computed can not be made, throwing disagreement exception");
			throw new DisagreementException("Consensus on value computed can not be made, throwing disagreement exception");
		}
		
		R valueFromMethod1 = null;
		R valueFromMethod2 = null;
		R valueFromMethod3 = null;
		if (values.size() == 3) {
			// TODO: Might need a way to match these to which method output them, potential for
			// obfuscated clarity in which one failed
			valueFromMethod1 = values.get("method1");
			valueFromMethod2 = values.get("method2");
			valueFromMethod3 = values.get("method3");
		}
		
		if (valueFromMethod1==valueFromMethod2 && valueFromMethod2==valueFromMethod3) {
			return valueFromMethod1;
		} else if (valueFromMethod1==valueFromMethod2 && valueFromMethod2!=valueFromMethod3) {
			
			logger.info("Alert! Value from method3 is different than method1 and method2");
			return valueFromMethod1;
		} else if (valueFromMethod1!=valueFromMethod2 && valueFromMethod2==valueFromMethod3) {
			
			logger.info("Alert! Value from method1 is different than method2 and method3");
			return valueFromMethod2;
		} else if (valueFromMethod1==valueFromMethod3 && valueFromMethod2==valueFromMethod3) {
			
			logger.info("Alert! Value from method2 is different than method1 and method3");
			return valueFromMethod1;
		} else {
			logger.severe("Consensus on value computed can not be made, throwing disagreement exception");
			throw new DisagreementException("Consensus on value computed can not be made, throwing disagreement exception");
		}		
	}	
}

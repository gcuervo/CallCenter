package com.gcuervo.callcenter.dispatcher;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.gcuervo.callcenter.call.Call;
import com.gcuervo.callcenter.empleados.Employee;
import com.gcuervo.callcenter.empleados.business.EmployeeBusiness;
import com.gcuervo.callcenter.exceptions.CallCenterException;

public class Dispatcher {

	private ExecutorService executor;
	protected ConcurrentLinkedQueue<Call> waitingCalls;
	private EmployeeBusiness employeeBusiness;
	private ConcurrentLinkedQueue<Call> receiveCalls;
	private Logger logger = Logger.getLogger(Dispatcher.class.getName());

	// private Logger logger = LoggerFactory.getLogger(Dispatcher.class);

	public Dispatcher(Integer threads) {
		executor = Executors.newFixedThreadPool(threads);
		waitingCalls = new ConcurrentLinkedQueue<Call>();
		employeeBusiness = new EmployeeBusiness();
		receiveCalls = new ConcurrentLinkedQueue<Call>();
	}

	public void employ(int cantDir, int cantSup, int cantOp) {
		employeeBusiness.employ(cantDir, cantSup, cantOp);
	}

	public void addCalls(List<Call> calls) {
		receiveCalls.addAll(calls);
	}

	public void getToWork() {
		Iterator<Call> it = receiveCalls.iterator();

		while (it.hasNext()) {
			callHandler(it.next());
			it.remove();
		}

		/*
		 * for (Call call : receiveCalls) { callHandler(call); }
		 */
	}

	public void addCall(Call call) {
		receiveCalls.add(call);
	}

	public void dispatchCall(final Employee employee, final Call call) {
		executor.execute(new Runnable() {
			public void run() {
				System.out.println("entro ");
				employee.answerCall(call);
				waitingCalls();
			}
		});
	}

	private synchronized void waitingCalls() {
		System.out.println("waitingCalls: " + waitingCalls.size());
		System.out.println("receiveCalls: " + receiveCalls.size());
		if (waitingCalls.isEmpty() && receiveCalls.isEmpty()) {
			executor.shutdown();
		} else if (!waitingCalls.isEmpty()) {
			 Call call = waitingCalls.remove();
			//Call call = removeWaitingCalls();
			callHandler(call);
		}
	}

	/*private synchronized Call removeWaitingCalls() {
		return waitingCalls.remove();
	}*/

	public void callHandler(Call call) {
		try {
			Employee employee = employeeBusiness.getAvailableEmployee();
			dispatchCall(employee, call);
		} catch (CallCenterException ex) {
			logger.info(ex.getMessage());
			System.out.println("adding waiting call " + ex.getMessage());
			waitingCalls.add(call);
		}
	}
}

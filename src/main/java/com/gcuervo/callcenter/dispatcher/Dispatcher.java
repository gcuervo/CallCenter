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
import com.gcuervo.callcenter.empleados.business.DirectorHandler;
import com.gcuervo.callcenter.empleados.business.EmployeeChain;
import com.gcuervo.callcenter.empleados.business.OperatorHandler;

public class Dispatcher {

	private ExecutorService executor;
	protected ConcurrentLinkedQueue<Call> waitingCalls;
	// private EmployeeBusiness employeeBusiness;
	private EmployeeChain employeeChain;
	private ConcurrentLinkedQueue<Call> receiveCalls;
	private Logger logger = Logger.getLogger(Dispatcher.class.getName());

	public Dispatcher(Integer threads) {
		executor = Executors.newFixedThreadPool(threads);
		waitingCalls = new ConcurrentLinkedQueue<Call>();
		// employeeBusiness = new EmployeeBusiness();

		receiveCalls = new ConcurrentLinkedQueue<Call>();
	}

	public void employ(int cantDir, int cantSup, int cantOp) {
		// employeeBusiness.employ(cantDir, cantSup, cantOp);
		employeeChain = new OperatorHandler(cantOp);
		employeeChain.addNext(new OperatorHandler(cantOp));
		employeeChain.addNext(new DirectorHandler(cantDir));
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
	}

	public void addCall(Call call) {
		receiveCalls.add(call);
	}

	public void dispatchCall(final Employee employee, final Call call) {
		executor.execute(new Runnable() {
			public void run() {
				employee.answerCall(call);
				waitingCalls();
			}
		});
	}

	private synchronized void waitingCalls() {
		// System.out.println("waitingCalls: " + waitingCalls.size());
		// System.out.println("receiveCalls: " + receiveCalls.size());
		if (waitingCalls.isEmpty() && receiveCalls.isEmpty()) {
			logger.info("Fin del trabajo!");
			executor.shutdown();
		} else if (!waitingCalls.isEmpty()) {
			Call call = waitingCalls.remove();
			// Call call = removeWaitingCalls();
			logger.info("Tomando llamada que se encontraba espera..");
			callHandler(call);
		}
	}

	public void callHandler(Call call) {
		Employee employee = employeeChain.getAvailableEmployee();
		if (employee == null) {
			System.out.println("Adding waiting call");
			waitingCalls.add(call);
		} else {
			dispatchCall(employee, call);
		}
	}
}

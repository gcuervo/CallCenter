package com.gcuervo.callcenter.dispatcher;

import java.util.ArrayList;
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
import com.gcuervo.callcenter.empleados.business.SupervisorHandler;

public class Dispatcher {

	private ExecutorService executor;
	protected ConcurrentLinkedQueue<Call> waitingCalls;
	private EmployeeChain employeeChain;
	private ConcurrentLinkedQueue<Call> receiveCalls;
	private Logger logger = Logger.getLogger(Dispatcher.class.getName());

	public Dispatcher(int threads) {
		executor = Executors.newFixedThreadPool(threads);
		waitingCalls = new ConcurrentLinkedQueue<Call>();
		receiveCalls = new ConcurrentLinkedQueue<Call>();
	}

	/*
	 * Metodo para crear la cadena de responsabilidades de los empleados. Recibe
	 * como parametros la cantidade de cada tipo de empleado.
	 * 
	 * @param cantDir cantidad de directores.
	 * 
	 * @param cantSup cantidad de supervisores.
	 * 
	 * @param cantOp cantidad de operadores.
	 */

	public void employ(int cantDir, int cantSup, int cantOp) {
		employeeChain = new OperatorHandler(cantOp);
		employeeChain.addNext(new SupervisorHandler(cantSup));
		employeeChain.addNext(new DirectorHandler(cantDir));
	}

	public void addCalls(List<Call> calls) {
		receiveCalls.addAll(calls);
	}

	/*
	 * metodo para comenzar a atender las llamadas.
	 * 
	 * @return retorna la lista de llamadas actualizada
	 */

	public List<Call> getToWork() {
		List<Call> calls = new ArrayList<Call>();
		while (receiveCalls.size() > 0) {
			Call call = receiveCalls.poll();
			callHandler(call);
			calls.add(call);
		}
		return calls;
	}

	public void addCall(Call call) {
		receiveCalls.add(call);
	}

	/*
	 * Crea un thread por cada llamada. Atiende la llamada y luego verifica si
	 * hay mas llamadas para atender o termina el thread
	 * 
	 * @param employee empleado que atiende la llamada.
	 * 
	 * @param call llamada en curso.
	 */

	public void dispatchCall(final Employee employee, final Call call) {
		executor.execute(new Runnable() {
			public void run() {
				employee.answerCall(call);
				waitingCalls();
			}
		});
	}

	private synchronized void waitingCalls() {

		if (waitingCalls.isEmpty() && receiveCalls.isEmpty()) {
			logger.info("Fin del trabajo!");
			executor.shutdown();
		} else if (!waitingCalls.isEmpty()) {
			Call call = waitingCalls.remove();
			logger.info("Tomando llamada que se encontraba en espera..");
			callHandler(call);
		}
	}

	/*
	 * Verifica si hay un empleado disponible, si lo hay, le transfiere la
	 * llamada. Si no hay empleado disponible agrega la llamada a la lista de
	 * espera.
	 * 
	 * @param call llamada en curso.
	 */

	public void callHandler(Call call) {
		Employee employee = employeeChain.getAvailableEmployee();
		if (employee == null) {
			logger.info("Llamada: " + call.getName() + " puesta en espera.");
			waitingCalls.add(call);
		} else {
			call.setEmployee(employee.getName());
			dispatchCall(employee, call);
		}
	}
}

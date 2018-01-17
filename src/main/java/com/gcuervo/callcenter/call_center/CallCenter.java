package com.gcuervo.callcenter.call_center;

import java.util.ArrayList;
import java.util.List;

import com.gcuervo.callcenter.call.Call;
import com.gcuervo.callcenter.dispatcher.Dispatcher;

/**
 * Hello world!
 */
public class CallCenter {
	/*
	 * private Dispatcher dispatcher; private EmployeeBusiness employeeBusiness;
	 * 
	 * 
	 * public void employ(int cantDir,int cantSup,int cantOp){
	 * employeeBusiness.employ(cantDir, cantSup, cantOp); }
	 * 
	 * 
	 * public void startWork(List<Call> calls){ dispatcher.getToWork(); }
	 */
	public static void main(String[] args) {
		Dispatcher dispatcher = new Dispatcher(10);

		List<Call> calls = new ArrayList<Call>();
		calls.add(new Call(5l));
		calls.add(new Call(6l));
		calls.add(new Call(10l));
		calls.add(new Call(8l));
		calls.add(new Call(5l));
		calls.add(new Call(7l));
		calls.add(new Call(9l));
		calls.add(new Call(5l));
		calls.add(new Call(5l));
		calls.add(new Call(5l));
		calls.add(new Call(5l));
		calls.add(new Call(5l));
		calls.add(new Call(5l));
		dispatcher.addCalls(calls);
		dispatcher.employ(1, 2, 3);
		dispatcher.getToWork();
	}

}

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
		calls.add(new Call(7l,"1"));
		calls.add(new Call(6l,"2"));
		calls.add(new Call(8l,"3"));
		calls.add(new Call(8l,"4"));
		calls.add(new Call(5l,"5"));
		calls.add(new Call(1l,"6"));
		calls.add(new Call(1l,"7"));
		calls.add(new Call(5l,"8"));
		calls.add(new Call(50l,"9"));
		calls.add(new Call(5l,"10"));
		calls.add(new Call(5l,"11"));
		calls.add(new Call(5l,"12"));
		calls.add(new Call(5l,"13"));
		dispatcher.addCalls(calls);
		dispatcher.employ(1, 2, 3);
		dispatcher.getToWork();
	}

}

package com.gcuervo.callcenter.call_center;

import java.util.ArrayList;
import java.util.List;

import com.gcuervo.callcenter.call.Call;
import com.gcuervo.callcenter.dispatcher.Dispatcher;

/**
 * Hello world!
 */
public class CallCenter {

	private Dispatcher dispatcher;

	public void employ(int cantDir, int cantSup, int cantOp) {
		dispatcher.employ(cantDir, cantSup, cantOp);
	}
	
	public CallCenter(int threads) {
		dispatcher = new Dispatcher(threads);
	}

	public List<Call> startWork(List<Call> calls) {
		dispatcher.addCalls(calls);
		return dispatcher.getToWork();
	}
	

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CallCenter callCenter = new CallCenter(10);
		callCenter.employ(1, 2, 7);

		List<Call> calls = new ArrayList<Call>();
		calls.add(new Call(5l, "1"));
		calls.add(new Call(6l, "2"));
		calls.add(new Call(8l, "3"));
		calls.add(new Call(10l, "4"));
		calls.add(new Call(5l, "5"));
		calls.add(new Call(10l, "6"));
		calls.add(new Call(10l, "7"));
		calls.add(new Call(5l, "8"));
		calls.add(new Call(5l, "9"));
		calls.add(new Call(5l, "10"));
		calls.add(new Call(5l, "11"));
		calls.add(new Call(8l, "12"));
		calls.add(new Call(9l, "13"));
		calls.add(new Call(5l, "14"));
		calls.add(new Call(6l, "15"));
		calls.add(new Call(7l, "16"));
		calls.add(new Call(10l, "17"));
		calls.add(new Call(5l, "18"));
		
		callCenter.startWork(calls);
	}

}

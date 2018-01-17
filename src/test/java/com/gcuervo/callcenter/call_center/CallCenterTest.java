package com.gcuervo.callcenter.call_center;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.gcuervo.callcenter.call.Call;
import static java.lang.Thread.sleep;
import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class CallCenterTest extends TestCase {
	List<Call> testCalls;

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public CallCenterTest(String testName) {
		super(testName);
		testCalls = new ArrayList<Call>();
	}
	
	/*
	 * Test con diez llamadas concurrentes y diez empleados disponibles.
	 * */

	@Test
	public void testDefault() throws Exception {
		int threads = 10;
		CallCenter callCenter = new CallCenter(threads);

		for (int i = 0; i < 10; i++) {
			testCalls.add(new Call("Llamada " + i));
		}

		// callCenter.addCalls(testCalls);
		callCenter.employ(1, 2, 7);
		testCalls = callCenter.startWork(testCalls);
		
		Assert.assertEquals(testCalls.get(0).getEmployee(), "Operador 1");
		Assert.assertEquals(testCalls.get(1).getEmployee(), "Operador 2");
		Assert.assertEquals(testCalls.get(2).getEmployee(), "Operador 3");
		Assert.assertEquals(testCalls.get(3).getEmployee(), "Operador 4");
		Assert.assertEquals(testCalls.get(4).getEmployee(), "Operador 5");
		Assert.assertEquals(testCalls.get(5).getEmployee(), "Operador 6");
		Assert.assertEquals(testCalls.get(6).getEmployee(), "Operador 7");
		Assert.assertEquals(testCalls.get(7).getEmployee(), "Supervisor 1");
		Assert.assertEquals(testCalls.get(8).getEmployee(), "Supervisor 2");
		Assert.assertEquals(testCalls.get(9).getEmployee(), "Director 1");
	}
	


	
}

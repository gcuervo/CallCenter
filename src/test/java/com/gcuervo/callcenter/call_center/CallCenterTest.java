package com.gcuervo.callcenter.call_center;

import java.util.List;

import com.gcuervo.callcenter.call.Call;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(CallCenterTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}
}

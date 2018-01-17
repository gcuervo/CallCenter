package com.gcuervo.callcenter.empleados.handler;

import com.gcuervo.callcenter.empleados.Employee;

public interface EmployeeChain {

	public void addNext(EmployeeChain next);

	public Employee getAvailableEmployee();
}

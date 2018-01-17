package com.gcuervo.callcenter.empleados.business;

import com.gcuervo.callcenter.empleados.Employee;

public interface EmployeeChain {

	public void addNext(EmployeeChain next);

	public Employee getAvailableEmployee();
}

package com.gcuervo.callcenter.empleados.business;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.gcuervo.callcenter.empleados.Employee;
import com.gcuervo.callcenter.empleados.Supervisor;
import com.gcuervo.callcenter.utils.ErrorConstants;

public class SupervisorHandler implements EmployeeChain {
	private EmployeeChain nextEmployeeHandler;
	private List<Supervisor> supervisors;
	private Logger logger = Logger.getLogger(SupervisorHandler.class.getName());

	public SupervisorHandler(int cantSup) {
		int index = 0;
		supervisors = new ArrayList<Supervisor>();
		while (index < cantSup) {
			supervisors.add(new Supervisor("Supervisor " + String.valueOf(index++)));
		}
	}

	@Override
	public void addNext(EmployeeChain next) {
		if (nextEmployeeHandler == null) {
			nextEmployeeHandler = next;
		} else {
			nextEmployeeHandler.addNext(next);
		}
	}

	@Override
	public synchronized Employee getAvailableEmployee() {
		Supervisor supervisor = null;
		Boolean found = false;
		for (int i = 0; i < supervisors.size() && !found; i++) {
			Supervisor sup = supervisors.get(i);
			if (sup.isAvailable()) {
				supervisor = sup;
				found = true;
				sup.setisAvailable(Boolean.FALSE);
			}
		}
		if (supervisor == null) {
			logger.info(ErrorConstants.NO_AVAILABLE_SUPERVISOR);
			nextEmployeeHandler.getAvailableEmployee();
		}
		return supervisor;
	}

}

package com.gcuervo.callcenter.empleados.business;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.gcuervo.callcenter.empleados.Director;
import com.gcuervo.callcenter.empleados.Employee;
import com.gcuervo.callcenter.empleados.Operator;
import com.gcuervo.callcenter.empleados.Supervisor;
import com.gcuervo.callcenter.exceptions.CallCenterException;
import com.gcuervo.callcenter.utils.ErrorConstants;

public class EmployeeBusiness {

	private List<Director> directors;
	private List<Operator> operators;
	private List<Supervisor> supervisors;

	private Logger logger = Logger.getLogger(EmployeeBusiness.class.getName());

	public EmployeeBusiness() {
		directors = new ArrayList<Director>();
		supervisors = new ArrayList<Supervisor>();
		operators = new ArrayList<Operator>();
	}

	public void employ(int cantDir, int cantSup, int cantOp) {
		int index = 0;

		while (index < cantDir) {
			directors.add(new Director("Director " + String.valueOf(index++)));
		}
		index = 0;
		while (index < cantSup) {
			supervisors.add(new Supervisor("Supervisor " + String.valueOf(index++)));
		}
		index = 0;
		while (index < cantOp) {
			operators.add(new Operator("Operator " + String.valueOf(index++)));
		}
	}

	public Operator getAvailableOperator() throws CallCenterException {
		Operator operator = null;
		Boolean found = false;
		for (int i = 0; i < operators.size() && !found; i++) {
			Operator op = operators.get(i);
			if (op.getisAvailable()) {
				operator = op;
				found = true;
				op.setisAvailable(Boolean.FALSE);
			}
		}
		if (operator == null) {
			throw new CallCenterException(ErrorConstants.NO_AVAILABLE_OPERATOR);
		}
		return operator;
	}

	public Supervisor getAvailableSupervisor() throws CallCenterException {
		Supervisor supervisor = null;
		Boolean found = false;
		for (int i = 0; i < supervisors.size() && !found; i++) {
			Supervisor sup = supervisors.get(i);
			if (sup.getisAvailable()) {
				supervisor = sup;
				found = true;
				sup.setisAvailable(Boolean.FALSE);
			}
		}
		if (supervisor == null) {
			throw new CallCenterException(ErrorConstants.NO_AVAILABLE_SUPERVISOR);
		}
		return supervisor;
	}

	public Director getAvailableDirector() throws CallCenterException {
		Director director = null;
		Boolean found = false;
		for (int i = 0; i < directors.size() && !found; i++) {
			Director dir = directors.get(i);
			if (dir.getisAvailable()) {
				director = dir;
				found = true;
				dir.setisAvailable(Boolean.FALSE);
			}
		}
		if (director == null) {
			throw new CallCenterException(ErrorConstants.NO_AVAILABLE_DIRECTOR);
		}
		return director;
	}

	public synchronized Employee getAvailableEmployee() throws CallCenterException {
		Employee employee = null;
		try {
			employee = getAvailableOperator();
		} catch (CallCenterException ex) {
			logger.info(ex.getMessage());
		}
		if (employee == null) {
			try {
				employee = getAvailableSupervisor();
			} catch (CallCenterException ex) {
				logger.info(ex.getMessage());
			}
		}
		if (employee == null) {
			try {
				employee = getAvailableDirector();
			} catch (CallCenterException ex) {
				logger.info(ex.getMessage());
			}
		}
		if (employee == null) {
			throw new CallCenterException(ErrorConstants.NO_AVAILABLE_EMPLOYEE);
		} else {
			employee.setisAvailable(Boolean.FALSE);
		}

		return employee;
	}
}

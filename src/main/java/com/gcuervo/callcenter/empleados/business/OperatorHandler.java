package com.gcuervo.callcenter.empleados.business;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.gcuervo.callcenter.empleados.Employee;
import com.gcuervo.callcenter.empleados.Operator;
import com.gcuervo.callcenter.utils.ErrorConstants;

public class OperatorHandler implements EmployeeChain {
	private EmployeeChain nextEmployeeHandler;
	private List<Operator> operators;
	private Logger logger = Logger.getLogger(OperatorHandler.class.getName());

	public OperatorHandler(int cantOp) {
		int index = 0;
		operators = new ArrayList<Operator>();
		while (index < cantOp) {
			operators.add(new Operator("Operator " + String.valueOf(index++)));
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
		Employee operator = null;
		Boolean found = false;
		for (int i = 0; i < operators.size() && !found; i++) {
			Operator op = operators.get(i);
			if (op.isAvailable()) {
				operator = op;
				found = true;
				op.setisAvailable(Boolean.FALSE);
			}
		}
		if (operator == null) {
			logger.info(ErrorConstants.NO_AVAILABLE_OPERATOR);
			operator = nextEmployeeHandler.getAvailableEmployee();
		}
		return operator;
	}
}

package com.gcuervo.callcenter.empleados.business;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.gcuervo.callcenter.empleados.Director;
import com.gcuervo.callcenter.empleados.Employee;
import com.gcuervo.callcenter.utils.ErrorConstants;

public class DirectorHandler implements EmployeeChain {
	private EmployeeChain nextEmployeeHandler;
	private List<Director> directors;
	private Logger logger = Logger.getLogger(DirectorHandler.class.getName());

	public DirectorHandler(int cantDir) {
		int index = 0;
		directors = new ArrayList<Director>();
		while (index < cantDir) {
			directors.add(new Director("Director " + String.valueOf(index++)));
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
		Director director = null;
		Boolean found = false;
		for (int i = 0; i < directors.size() && !found; i++) {
			Director dir = directors.get(i);
			if (dir.isAvailable()) {
				director = dir;
				found = true;
				dir.setisAvailable(Boolean.FALSE);
			}
		}
		if (director == null) {
			logger.info(ErrorConstants.NO_AVAILABLE_DIRECTOR);
			logger.info(ErrorConstants.NO_AVAILABLE_EMPLOYEE);
		}
		return director;
	}

}

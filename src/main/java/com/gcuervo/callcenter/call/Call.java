package com.gcuervo.callcenter.call;

public class Call {

	private Long idCall;
	private Long duration;
	private String name;
	private String employee;

	public Call() {
	}

	public Call(Long duration, String name) {
		this.duration = duration;
		idCall = (long) (Math.random() * 1000 + 10);
		this.setName(name);
	}

	public Call(String name) {
		this.duration = (long) ((Math.random() * 10) + 5);
		idCall = (long) (Math.random() * 1000 + 10);
		this.setName(name);
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Long getIdCall() {
		return idCall;
	}

	public void setIdCall(Long idCall) {
		this.idCall = idCall;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}
}

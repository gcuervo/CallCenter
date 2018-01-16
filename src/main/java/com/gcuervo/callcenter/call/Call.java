package com.gcuervo.callcenter.call;

public class Call {

	private Long idCall;
	private Long duration;
	private String employeee;

	public Call() {
	}

	public Call(Long duration) {
		this.duration = duration;
		idCall = (long) (Math.random()*1000 + 10);
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getEmployeee() {
		return employeee;
	}

	public void setEmployeee(String employeee) {
		this.employeee = employeee;
	}

	public Long getIdCall() {
		return idCall;
	}

	public void setIdCall(Long idCall) {
		this.idCall = idCall;
	}
}

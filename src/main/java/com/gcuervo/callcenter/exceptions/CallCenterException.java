package com.gcuervo.callcenter.exceptions;

public class CallCenterException extends Exception {
  private String NO_EMPLOYEE_AVAILABLE = "No employee available at the moment";

  public CallCenterException(String msj) {
    super(msj);
  }
}

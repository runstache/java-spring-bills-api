package com.lswebworld.bills.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handler for dealing with Schedule Not Found issues.
 */
@ControllerAdvice
class ScheduleNotFoundAdvice {

  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(ScheduleNotFoundException.class)
  String scheduleNotFoundHandler(ScheduleNotFoundException ex) {
    return ex.getMessage();
  }
}

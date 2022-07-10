package com.lswebworld.bills.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handler for Bill Not Found Exceptions.
 */
@ControllerAdvice
class BillNotFoundAdvice {

  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(BillNotFoundException.class)
  String billNotFoundHandler(BillNotFoundException ex) {
    return ex.getMessage();
  }
}

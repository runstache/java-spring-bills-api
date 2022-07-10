package com.lswebworld.bills.api.exceptions;

/**
 * Exception denoting that a Bill was not found.
 */
public class BillNotFoundException extends RuntimeException {

  public BillNotFoundException(String identifier) {
    super("Cound not find bill: " + identifier);
  }
}

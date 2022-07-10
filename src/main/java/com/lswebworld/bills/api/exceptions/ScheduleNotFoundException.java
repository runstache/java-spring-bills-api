package com.lswebworld.bills.api.exceptions;

/**
 * Exception for denoting a Schedule Item was not found.
 */
public class ScheduleNotFoundException extends RuntimeException {

  public ScheduleNotFoundException(long id) {
    super("Could not find Schedule: " + id);
  }
}

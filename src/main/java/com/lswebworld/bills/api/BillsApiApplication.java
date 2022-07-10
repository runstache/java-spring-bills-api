package com.lswebworld.bills.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Api Launch Class.
 */
@SpringBootApplication
@EntityScan({"com.lswebworld"})
public class BillsApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(BillsApiApplication.class, args);
  }

}

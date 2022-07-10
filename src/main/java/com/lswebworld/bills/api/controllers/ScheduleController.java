package com.lswebworld.bills.api.controllers;

import com.lswebworld.bills.api.exceptions.ScheduleNotFoundException;
import com.lswebworld.bills.api.repositories.ScheduleRepository;
import com.lswebworld.bills.data.dataobjects.ScheduleInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Schedule Item Rest Controller.
 */
@RestController
public class ScheduleController {

  private final ScheduleRepository repo;

  @Autowired
  public ScheduleController(ScheduleRepository repo) {
    this.repo = repo;
  }

  @GetMapping("/api/schedule")
  public List<ScheduleInfo> getScheduleItems() {
    return repo.findAll();
  }

  @GetMapping("/api/schedule/{type}/items")
  public List<ScheduleInfo> getSchedule(@PathVariable String type) {
    return repo.findAllByScheduleType(type);
  }

  /**
   * Retrieves a given Schedule Item from the Database.
   *
   * @param id Id
   * @return Schedule Info
   */
  @GetMapping("/api/schedule/{id}")
  public ScheduleInfo getScheduleItem(@PathVariable long id) {
    var result = repo.findById(id);

    if (result.isPresent()) {
      return result.get();
    }
    throw new ScheduleNotFoundException(id);
  }
}

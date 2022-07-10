package com.lswebworld.bills.api.controllers;

import com.lswebworld.bills.api.exceptions.BillNotFoundException;
import com.lswebworld.bills.api.repositories.BillsRepository;
import com.lswebworld.bills.data.dataobjects.BillInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller for Bills Items.
 */
@RestController
public class BillsController {

  private final BillsRepository repo;

  @Autowired
  public BillsController(BillsRepository repo) {
    this.repo = repo;
  }

  /**
   * Retrieves a Bill from the Database based on the Identifier.
   *
   * @param identifier Identifier
   * @return Bill Info
   */
  @GetMapping(path = "/api/bills/{identifier}")
  public BillInfo getBillInfo(@PathVariable String identifier) {

    var result = repo.findById(identifier);
    if (result.isPresent()) {
      return result.get();
    }
    throw new BillNotFoundException(identifier);
  }


  /**
   * Retrieves all the Bills from the database.
   *
   * @return List of Bills.
   */
  @GetMapping(path = "/api/bills")
  public List<BillInfo> getBills() {
    return repo.findAll();
  }


}

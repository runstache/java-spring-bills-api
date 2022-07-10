package com.lswebworld.bills.api.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.lswebworld.bills.api.exceptions.BillNotFoundException;
import com.lswebworld.bills.api.repositories.BillsRepository;
import com.lswebworld.bills.data.dataobjects.BillInfo;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

class BillsControllerTest {

  private BillsRepository repo;
  private BillInfo bill;

  @BeforeEach
  void setup() {

    bill = new BillInfo();
    bill.setUrl("www.google.com");
    bill.setTitle("My Bill");
    bill.setPubDate(ZonedDateTime.now());
    bill.setPrimeSponsors("jim");
    bill.setIdentifier("20220SB106");
    bill.setPassedSenate(true);
    bill.setPassedHouse(false);
    bill.setLastAction("Passed");
    bill.setEnacted(false);
    bill.setCoSponsors("brian");
    bill.setDescription("Terrible idea");
    bill.setCreatedOn(ZonedDateTime.now());
    bill.setUpdatedOn(ZonedDateTime.now());

    repo = mock(BillsRepository.class);
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetBill() {
    when(repo.findById(anyString())).thenReturn(Optional.of(bill));

    var controller = new BillsController(repo);

    var result = controller.getBillInfo("20220SB106");

    assertThat(result).as("Result should not be null").isNotNull().isEqualTo(bill);
  }

  @Test
  void testGetAllBills() {

    when(repo.findAll()).thenReturn(Collections.singletonList(bill));
    var controller = new BillsController(repo);

    assertThat(controller.getBills())
            .as("Result should not be empty")
            .isNotEmpty().hasSize(1);

  }

  @Test
  void testGetBillNotFound() {
    when(repo.findById(anyString())).thenReturn(Optional.empty());

    var controller = new BillsController(repo);

    assertThatThrownBy(() -> controller.getBillInfo("20220SB106"))
            .as("Not Found Exception should be thrown")
            .isInstanceOf(BillNotFoundException.class)
            .hasMessage("Cound not find bill: 20220SB106");
  }

  @Test
  void testGetBillsNoResults() {
    when(repo.findAll()).thenReturn(Collections.emptyList());
    var controller = new BillsController(repo);

    assertThat(controller.getBills()).as("Result should be empty").isEmpty();

  }

}

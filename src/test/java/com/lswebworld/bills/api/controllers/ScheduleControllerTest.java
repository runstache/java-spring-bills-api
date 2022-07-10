package com.lswebworld.bills.api.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.lswebworld.bills.api.exceptions.ScheduleNotFoundException;
import com.lswebworld.bills.api.repositories.ScheduleRepository;
import com.lswebworld.bills.data.dataobjects.ScheduleInfo;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

class ScheduleControllerTest {

  ScheduleRepository repo;

  private ScheduleInfo item;

  @BeforeEach
  void setup() {
    item = new ScheduleInfo();
    item.setCreatedOn(ZonedDateTime.now());
    item.setId(1);
    item.setScheduleDate(ZonedDateTime.now());
    item.setIdentifier("20220SB101P1456");
    item.setScheduleType("house");
    item.setUpdatedOn(ZonedDateTime.now());

    repo = mock(ScheduleRepository.class);
    MockitoAnnotations.openMocks(this);
  }



  @Test
  void testGetScheduleItems() {
    when(repo.findAll()).thenReturn(Collections.singletonList(item));
    var controller = new ScheduleController(repo);

    var result = controller.getScheduleItems();

    assertThat(result).as("Result List should not be empty").isNotEmpty().hasSize(1);

  }

  @Test
  void testGetSchedule() {
    when(repo.findAllByScheduleType(anyString())).thenReturn(Collections.singletonList(item));
    var controller = new ScheduleController(repo);
    var result = controller.getSchedule("house");

    assertThat(result).as("Result should not be empty").isNotEmpty().hasSize(1);

  }

  @Test
  void testGetScheduleItem() {
    when(repo.findById(anyLong())).thenReturn(Optional.of(item));

    var controller = new ScheduleController(repo);
    var result = controller.getScheduleItem(1L);
    assertThat(result).as("Result should not be empty").isEqualTo(item);
  }

  @Test
  void testGetScheduleEmpty() {
    when(repo.findAllByScheduleType(anyString())).thenReturn(Collections.emptyList());

    var controller = new ScheduleController(repo);

    assertThat(controller.getSchedule("senate"))
            .as("Schedule should be empty")
            .isEmpty();

  }

  @Test
  void testGetScheduleItemsEmpty() {
    when(repo.findAll()).thenReturn(Collections.emptyList());

    var controller = new ScheduleController(repo);
    assertThat(controller.getScheduleItems()).as("Result should be empty").isEmpty();
  }

  @Test
  void testGetScheduleItemNoResult() {
    when(repo.findById(anyLong())).thenReturn(Optional.empty());

    var controller = new ScheduleController(repo);
    assertThatThrownBy(() -> controller.getScheduleItem(1L))
            .as("Not Found Exception should be thrown")
            .isInstanceOf(ScheduleNotFoundException.class)
            .hasMessage("Could not find Schedule: 1");
  }
}

package com.lswebworld.bills.api.repositories;

import com.lswebworld.bills.data.dataobjects.ScheduleInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repsitory for Retrieving Schedule information.
 */
@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleInfo, Long> {

  List<ScheduleInfo> findAllByScheduleType(String scheduleType);
}

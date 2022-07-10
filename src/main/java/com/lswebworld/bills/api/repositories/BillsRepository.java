package com.lswebworld.bills.api.repositories;

import com.lswebworld.bills.data.dataobjects.BillInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for accessing Bills Information.
 */
@Repository
public interface BillsRepository extends JpaRepository<BillInfo, String> {
}

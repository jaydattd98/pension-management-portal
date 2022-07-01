package com.cognizant.springboot.pensionerdetail.repository;

import com.cognizant.springboot.pensionerdetail.entity.BankDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface BankDeatilsRepository
 *
 * Used to perform All CRUD operation on BankDetail
 */
@Repository
public interface BankDeatilsRepository extends JpaRepository<BankDetail, String> {
}

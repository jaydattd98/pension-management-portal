package com.cognizant.springboot.pensionerdetail.repository;

import com.cognizant.springboot.pensionerdetail.entity.AadhaarCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface AadhaarCardRepository
 *
 * Used to perform All CRUD operation on AadhaarCard
 */
@Repository
public interface AadhaarCardRepository extends JpaRepository<AadhaarCard, String> {
}

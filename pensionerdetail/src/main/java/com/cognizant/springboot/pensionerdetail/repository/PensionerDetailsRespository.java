package com.cognizant.springboot.pensionerdetail.repository;

import com.cognizant.springboot.pensionerdetail.entity.PensionerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface PensionerDetailsRespository
 *
 * Used to perform All CRUD operation on PensionerDetail
 */
@Repository
public interface PensionerDetailsRespository extends JpaRepository<PensionerDetail, String> {

}

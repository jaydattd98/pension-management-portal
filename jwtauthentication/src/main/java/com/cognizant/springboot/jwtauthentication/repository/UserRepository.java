package com.cognizant.springboot.jwtauthentication.repository;

import com.cognizant.springboot.jwtauthentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface UserRepository
 *
 * Used to perform All CRUD operation on User
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}

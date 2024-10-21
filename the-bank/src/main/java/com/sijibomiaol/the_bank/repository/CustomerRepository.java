package com.sijibomiaol.the_bank.repository;

import com.sijibomiaol.the_bank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByAccountNumber(String accountNumber);
    Boolean existsByAccountNumber(String accountNumber);
    Boolean existsByEmail(String email);
    Optional<Customer> findByEmail(String email);

}

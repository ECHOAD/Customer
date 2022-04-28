package com.echoad.Customers.repositories;

import com.echoad.Customers.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepository
        extends JpaRepository<Customer,Long> {

    Optional<Customer> findByIdCardNumber(String idCardNumber);

    boolean existsByIdCardNumber(String idCardNumber);

}

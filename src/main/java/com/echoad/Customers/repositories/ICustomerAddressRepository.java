package com.echoad.Customers.repositories;

import com.echoad.Customers.models.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerAddressRepository
        extends JpaRepository<CustomerAddress,Long> {

    List<CustomerAddress> findByCustomerId(Long customerId);

}

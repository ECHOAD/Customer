package com.echoad.Customers.services.interfaces;

import com.echoad.Customers.dtos.CustomerDto;
import com.echoad.Customers.exceptions.ServiceException;
import com.echoad.Customers.models.Customer;
import com.echoad.Customers.models.CustomerAddress;

import java.util.List;

public interface ICustomerService {

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(Long id);

    boolean createCustomer(CustomerDto customer) ;

    boolean updateCustomer(CustomerDto customer);

    boolean deleteCustomer(Long id);


}



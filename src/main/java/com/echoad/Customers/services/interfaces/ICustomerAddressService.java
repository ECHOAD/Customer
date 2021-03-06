package com.echoad.Customers.services.interfaces;

import com.echoad.Customers.dtos.CustomerAddressDto;
import com.echoad.Customers.dtos.CustomerDto;
import com.echoad.Customers.models.CustomerAddress;

import java.util.List;

public interface ICustomerAddressService {

    List<CustomerAddressDto> getAllAddressByCustomer(Long idCustomer);

    CustomerAddressDto getCustomerAddressById(Long id);

    CustomerAddressDto createCustomerAddress(Long idCustomer,CustomerAddressDto customerAddress) ;

    CustomerAddressDto updateCustomerAddress(CustomerAddressDto customerAddress);

    boolean deleteCustomerAddress(Long id);


}



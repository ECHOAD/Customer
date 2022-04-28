package com.echoad.Customers.dtos.mappers;

import com.echoad.Customers.dtos.CustomerAddressDto;
import com.echoad.Customers.dtos.CustomerDto;
import com.echoad.Customers.models.Customer;
import com.echoad.Customers.models.CustomerAddress;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

//This class is used to map Customer to CustomerDto and vice versa
public class CustomerDtoMapper {


    public static CustomerDto softMapCustomerToCustomerDto(@NotNull Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getIdCardNumber(),
                customer.getFirstName(),
                customer.getLastName()
        );
    }

    public static Customer softMapCustomerDtoToCustomer(@NotNull CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setIdCardNumber(customerDto.getIdCardNumber());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());


        return customer;
    }



    public static CustomerDto deepMapCustomerToCustomerDto(@NotNull Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getIdCardNumber(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getAddresses().stream()
                        .map(address -> new CustomerAddressDto(
                                address.getId(),
                                address.getAddress())
                        ).collect(Collectors.toList())
        );
    }

    public static Customer deepMapCustomerDtoToCustomer(@NotNull CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setIdCardNumber(customerDto.getIdCardNumber());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());

        customer.setAddresses(customerDto.getAddresses().stream()
                .map(address -> new CustomerAddress(
                        address.getId(),
                        customer,
                        address.getAddress()))
                .collect(Collectors.toList()));

        return customer;
    }
}




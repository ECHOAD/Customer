package com.echoad.Customers.dtos.mappers;

import com.echoad.Customers.dtos.CustomerAddressDto;
import com.echoad.Customers.models.CustomerAddress;

public class CustomerAddressMapper {
    public static CustomerAddress softMapCustomerAddressDtoToCustomerAddress(CustomerAddressDto customerAddressDto) {
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setId(customerAddressDto.getId());
        customerAddress.setAddress(customerAddressDto.getAddress());
        return customerAddress;
    }

    public static CustomerAddressDto softMapCustomerAddressToCustomerAddressDto(CustomerAddress customerAddress) {
        CustomerAddressDto customerAddressDto = new CustomerAddressDto();
        customerAddressDto.setId(customerAddress.getId());
        customerAddressDto.setAddress(customerAddress.getAddress());
        return customerAddressDto;
    }

}

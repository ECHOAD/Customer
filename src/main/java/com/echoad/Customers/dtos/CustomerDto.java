package com.echoad.Customers.dtos;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerDto {

    private Long id;
    private String idCardNumber;
    private String firstName;
    private String lastName;
    private List<CustomerAddressDto> addresses;

    public CustomerDto() {
    }

    public CustomerDto(Long id, String idCardNumber, String firstName, String lastName) {
        this.id = id;
        this.idCardNumber = idCardNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public CustomerDto(Long id, String idCardNumber, String firstName, String lastName, List<CustomerAddressDto> addresses) {
        this.id = id;
        this.idCardNumber = idCardNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addresses = addresses;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<CustomerAddressDto> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<CustomerAddressDto> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "CustomerDto{" + "name=" + firstName + ", id_card_number=" + idCardNumber + ", lastName=" + lastName + "," +
                " addresses=" + addresses.stream().map(CustomerAddressDto::getAddress).collect(Collectors.joining(", ")) + '}';
    }

}
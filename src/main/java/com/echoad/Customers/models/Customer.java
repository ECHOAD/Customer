package com.echoad.Customers.models;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;


/*This class is representing a customer model on a database*/
@Entity
@Table(name = "customers")
@Where(clause = "is_deleted = 0")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_card_number", nullable = false)
    private String idCardNumber;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "Number default 0")
    private int isDeleted;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerAddress> addresses;

    public Customer() {
    }

    public Customer(Long id, String IdCardNumber, String firstName, String lastName, List<CustomerAddress> addresses) {
        this.id = id;
        this.idCardNumber = IdCardNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addresses = addresses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String id_card_number) {
        this.idCardNumber = id_card_number;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<CustomerAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<CustomerAddress> addresses) {
        this.addresses = addresses;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }


    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", id_card_number=" + idCardNumber + ", name=" + firstName + ", lastName=" + lastName + '}';
    }

}

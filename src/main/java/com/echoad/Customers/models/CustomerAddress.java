package com.echoad.Customers.models;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer_addresses")
public class CustomerAddress implements Serializable {


    public CustomerAddress() {
    }

    public CustomerAddress(Customer customer, String address) {
        this.customer = customer;
        this.address = address;
    }

    //This constructor is to be used for testing purposes in a future version
    public CustomerAddress(Long id, Customer customer, String address) {
        this.id = id;
        this.customer = customer;
        this.address = address;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "address", nullable = false)
    private String address;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CustomerAddress{" +
                "id=" + id +
                ", customer=" + customer +
                ", address='" + address + '\'' +
                '}';
    }

}

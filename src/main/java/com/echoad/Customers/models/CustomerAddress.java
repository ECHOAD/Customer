package com.echoad.Customers.models;

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

    @ManyToOne(optional = false,cascade= CascadeType.ALL ,fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false, updatable = false)
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

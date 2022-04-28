package com.echoad.Customers.controllers;

import com.echoad.Customers.dtos.CustomerAddressDto;
import com.echoad.Customers.dtos.CustomerDto;
import com.echoad.Customers.dtos.ResponseObject;
import com.echoad.Customers.exceptions.ServiceException;
import com.echoad.Customers.services.CustomersAddressService;
import com.echoad.Customers.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/customers/")
public class CustomerController {
    private final CustomersService customersService;
    private final CustomersAddressService customersAddressService;

    @Autowired
    public CustomerController(CustomersService customersService,
                              CustomersAddressService customersAddressService) {

        this.customersService = customersService;
        this.customersAddressService = customersAddressService;
    }


    @GetMapping("/")
    public ResponseEntity<?> fetchAllCustomers() {
        var responseObject = new ResponseObject<List<CustomerDto>>();

        try {
            responseObject.setMessage("Customers fetched successfully");
            responseObject.setData(customersService.getAllCustomers());

            return new ResponseEntity<>(responseObject, null, 200);

        } catch (Exception e) {
            responseObject.setMessage("Customers fetch failed");
            responseObject.setData(null);
            return new ResponseEntity<>(responseObject, null, 500);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDto customerDto) {
        var responseObject = new ResponseObject<Boolean>();
        try {

            responseObject.setData(customersService.createCustomer(customerDto));
            responseObject.setMessage("Customer created successfully");

            return new ResponseEntity<>(responseObject, null, 201);
        } catch (ServiceException e) {
            responseObject.setMessage(e.getMessage());
            responseObject.setData(false);
            return new ResponseEntity<>(responseObject, null, 400);
        } catch (Exception e) {

            responseObject.setMessage("Customer creation failed");
            responseObject.setData(false);

            return new ResponseEntity<>(responseObject, null, 500);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> fetchCustomerById(@PathVariable("id") Long id) {
        var responseObject = new ResponseObject<CustomerDto>();

        try {
            responseObject.setMessage("Customer fetched successfully");
            responseObject.setData(customersService.getCustomerById(id));

            return new ResponseEntity<>(responseObject, null, 200);

        } catch (ServiceException e) {
            responseObject.setMessage(e.getMessage());
            responseObject.setData(null);
            return new ResponseEntity<>(responseObject, null, 400);
        } catch (Exception e) {
            responseObject.setMessage("Customer fetch failed");
            responseObject.setData(null);
            return new ResponseEntity<>(responseObject, null, 500);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDto customerDto) {
        var responseObject = new ResponseObject<Boolean>();

        if (!customerDto.getId().equals(id)) {
            responseObject.setMessage("Customer id mismatch");
            responseObject.setData(null);
            return new ResponseEntity<>(responseObject, null, 400);
        }


        try {
            responseObject.setMessage("Customer updated successfully");
            responseObject.setData(customersService.updateCustomer(customerDto));

            return new ResponseEntity<>(responseObject, null, 200);

        } catch (ServiceException e) {
            responseObject.setMessage(e.getMessage());
            responseObject.setData(null);
            return new ResponseEntity<>(responseObject, null, 400);
        } catch (Exception e) {
            responseObject.setMessage("Customer update failed");
            responseObject.setData(null);
            return new ResponseEntity<>(responseObject, null, 500);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
        var responseObject = new ResponseObject<Boolean>();

        try {
            responseObject.setMessage("Customer deleted successfully");
            responseObject.setData(customersService.deleteCustomer(id));

            return new ResponseEntity<>(responseObject, null, 200);

        } catch (ServiceException e) {
            responseObject.setMessage(e.getMessage());
            responseObject.setData(false);
            return new ResponseEntity<>(responseObject, null, 400);
        } catch (Exception e) {
            responseObject.setMessage("Customer delete failed");
            responseObject.setData(false);
            return new ResponseEntity<>(responseObject, null, 500);
        }
    }

    @GetMapping("/{id}/address")
    public ResponseEntity<?> getAllCustomerAddress(@PathVariable("id") Long id) {
        var responseObject = new ResponseObject<List<CustomerAddressDto>>();

        try {
            responseObject.setMessage("Customer addresses retrieved successfully");
            responseObject.setData(customersAddressService.getAllAddressByCustomer(id));

            return new ResponseEntity<>(responseObject, null, 200);

        } catch (ServiceException e) {
            responseObject.setMessage(e.getMessage());
            responseObject.setData(null);
            return new ResponseEntity<>(responseObject, null, 400);
        } catch (Exception e) {
            responseObject.setMessage("Customer addresses retrieval failed");
            responseObject.setData(null);
            return new ResponseEntity<>(responseObject, null, 500);
        }
    }

    @PostMapping("/{idCustomer}/address")
    public ResponseEntity<?> createCustomerAddress(@PathVariable("idCustomer") Optional<Long> idCustomer, @RequestBody CustomerAddressDto customerAddressDto) {
        var responseObject = new ResponseObject<Boolean>();

        if (idCustomer.isEmpty()) {
            responseObject.setMessage("Customer id is required");
            responseObject.setData(false);
            return new ResponseEntity<>(responseObject, null, 400);

        }


        try {

            responseObject.setData(customersAddressService.createCustomerAddress(idCustomer.get(), customerAddressDto));
            responseObject.setMessage("Customer address created successfully");

            return new ResponseEntity<>(responseObject, null, 201);
        } catch (ServiceException e) {
            responseObject.setMessage(e.getMessage());
            responseObject.setData(false);

            return new ResponseEntity<>(responseObject, null, 400);
        } catch (Exception e) {

            responseObject.setMessage("Customer address creation failed");
            responseObject.setData(false);

            return new ResponseEntity<>(responseObject, null, 500);
        }
    }

    @PutMapping("/address/{addressId}")
    public ResponseEntity<?> updateCustomerAddress(@PathVariable("addressId") Optional<Long> addressId,
                                                   @RequestBody CustomerAddressDto customerAddressDto) {

        var responseObject = new ResponseObject<Boolean>();

        if(addressId.isEmpty() || customerAddressDto.getId() == null) {
            responseObject.setMessage("Customer address update failed, address id is empty");
            responseObject.setData(false);
            return new ResponseEntity<>(responseObject, null, 400);
        }


        if(!customerAddressDto.getId().equals(addressId.get())) {
            responseObject.setMessage("Customer address update failed, address id is not equal");
            responseObject.setData(false);
            return new ResponseEntity<>(responseObject, null, 400);
        }

        try {

            responseObject.setData(customersAddressService.updateCustomerAddress(customerAddressDto));
            responseObject.setMessage("Customer address updated successfully");

            return new ResponseEntity<>(responseObject, null, 200);
        } catch (ServiceException e) {
            responseObject.setMessage(e.getMessage());
            responseObject.setData(false);

            return new ResponseEntity<>(responseObject, null, 400);
        } catch (Exception e) {

            responseObject.setMessage("Customer address update failed");
            responseObject.setData(false);

            return new ResponseEntity<>(responseObject, null, 500);
        }
    }



    @DeleteMapping("/address/{addressId}")
    public ResponseEntity<?> deleteCustomerAddress(@PathVariable("addressId") Long addressId) {
        var responseObject = new ResponseObject<Boolean>();

        try {
            responseObject.setMessage("Customer address deleted successfully");
            responseObject.setData(customersAddressService.deleteCustomerAddress(addressId));

            return new ResponseEntity<>(responseObject, null, 200);

        } catch (ServiceException e) {
            responseObject.setMessage(e.getMessage());
            responseObject.setData(false);
            return new ResponseEntity<>(responseObject, null, 400);
        } catch (Exception e) {
            responseObject.setMessage("Customer address delete failed");
            responseObject.setData(false);
            return new ResponseEntity<>(responseObject, null, 500);
        }
    }


}

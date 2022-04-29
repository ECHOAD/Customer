package com.echoad.Customers.services;

import com.echoad.Customers.dtos.CustomerAddressDto;
import com.echoad.Customers.dtos.mappers.CustomerAddressMapper;
import com.echoad.Customers.exceptions.InvalidArgumentException;
import com.echoad.Customers.exceptions.ServiceException;
import com.echoad.Customers.models.Customer;
import com.echoad.Customers.models.CustomerAddress;
import com.echoad.Customers.repositories.ICustomerAddressRepository;
import com.echoad.Customers.repositories.ICustomerRepository;
import com.echoad.Customers.services.interfaces.ICustomerAddressService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// Service class for Customers
@Service
@Transactional(
        propagation = Propagation.REQUIRES_NEW,
        rollbackFor = {Exception.class, RuntimeException.class}
)
public class CustomersAddressService implements ICustomerAddressService {

    private static final Logger logger = LogManager.getLogger(CustomersAddressService.class);

    private final ICustomerAddressRepository customerAddressRepository;
    private final ICustomerRepository customerRepository;


    @Autowired
    public CustomersAddressService(ICustomerAddressRepository customerAddressRepository, ICustomerRepository customerRepository) {
        this.customerAddressRepository = customerAddressRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerAddressDto createCustomerAddress(Long idCustomer, CustomerAddressDto customerAddressDto) {
        try {
            Optional<Customer> customerFromDb = customerRepository.findById(idCustomer);
            if (customerFromDb.isEmpty()) {
                throw new InvalidArgumentException("The customer that you are trying to add a new address does not exist");
            }

            CustomerAddress customerAddressToCreate = new CustomerAddress(customerFromDb.get(), customerAddressDto.getAddress());
            customerAddressRepository.save(customerAddressToCreate);

            return CustomerAddressMapper.softMapCustomerAddressToCustomerAddressDto(customerAddressToCreate);

        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Error creating customer address", e);
            throw new RuntimeException("Error creating customer address");
        }
    }


    @Override
    public List<CustomerAddressDto> getAllAddressByCustomer(Long idCustomer) {
        try {

            return customerAddressRepository.findByCustomerId(idCustomer).stream()
                    .map(address -> new CustomerAddressDto(address.getId(), address.getAddress()))
                    .collect(java.util.stream.Collectors.toList());

        } catch (Exception e) {
            logger.error("Error getting all addresses by customer", e);
            throw new RuntimeException("Error getting all addresses by customer");
        }
    }

    @Override
    public CustomerAddressDto getCustomerAddressById(Long id) {

        try {

            Optional<CustomerAddress> customerAddressFromDb = customerAddressRepository.findById(id);
            if (customerAddressFromDb.isEmpty()) {
                throw new InvalidArgumentException("The customer address that you are trying to get does not exist");
            }

            CustomerAddress customerAddress = customerAddressFromDb.get();

            return new CustomerAddressDto(
                    customerAddress.getId(),
                    customerAddress.getAddress()
            );
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Error getting customer address", e);
            throw new RuntimeException("Error getting customer address");
        }
    }


    public CustomerAddressDto updateCustomerAddress(CustomerAddressDto customerAddress) {
        try {
            Optional<CustomerAddress> addressFromDb = customerAddressRepository.findById(customerAddress.getId());

            if (addressFromDb.isEmpty()) {
                throw new InvalidArgumentException("The address that you are trying to update does not exist");
            }

            CustomerAddress addressToUpdate = addressFromDb.get();
            addressToUpdate.setAddress(customerAddress.getAddress());
            customerAddressRepository.save(addressToUpdate);

            return CustomerAddressMapper.softMapCustomerAddressToCustomerAddressDto(addressToUpdate);
        } catch (Exception e) {
            logger.error("Error updating address", e);
            throw new ServiceException("Error updating customer", e);
        }
    }

    public boolean deleteCustomerAddress(Long id) {
        try {

            Optional<CustomerAddress> customerAddressFromDb = customerAddressRepository.findById(id);

            if (customerAddressFromDb.isEmpty()) {
                throw new InvalidArgumentException("The address that you are trying to delete does not exist");
            }

            CustomerAddress customerToDelete = customerAddressFromDb.get();

            customerAddressRepository.delete(customerToDelete);
            return true;

        } catch (InvalidArgumentException e) {
            logger.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error deleting customer", e);
            throw new ServiceException("Error deleting customer", e);
        }
    }


}

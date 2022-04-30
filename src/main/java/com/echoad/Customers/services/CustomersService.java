package com.echoad.Customers.services;

import com.echoad.Customers.dtos.CustomerDto;
import com.echoad.Customers.exceptions.ExistingRecordException;
import com.echoad.Customers.exceptions.InvalidArgumentException;
import com.echoad.Customers.exceptions.ServiceException;
import com.echoad.Customers.models.Customer;
import com.echoad.Customers.repositories.ICustomerRepository;
import com.echoad.Customers.dtos.mappers.CustomerDtoMapper;
import com.echoad.Customers.services.interfaces.ICustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Service class for Customers
@Service
@Transactional(
        propagation = Propagation.REQUIRES_NEW,
        rollbackFor = {Exception.class}
)
public class CustomersService implements ICustomerService {

    private static final Logger logger = LogManager.getLogger(CustomersService.class);


    private final ICustomerRepository customerRepository;


    @Autowired
    public CustomersService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDto> getAllCustomers() {
        try {
            return customerRepository.findAll().stream()
                    .map(CustomerDtoMapper::deepMapCustomerToCustomerDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error getting customers", e);
            throw new ServiceException("Error getting customers");
        }
    }

    public CustomerDto getCustomerById(Long id) {
        try {
            Optional<Customer> customerFromDb = customerRepository.findById(id);
            if (customerFromDb.isEmpty()) {
                throw new InvalidArgumentException("The customer that you are trying to get does not exist");
            }
            return CustomerDtoMapper.deepMapCustomerToCustomerDto(customerFromDb.get());

        } catch (InvalidArgumentException e) {
            logger.error("Error getting customer" + e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Error getting customer", e);
            throw new ServiceException("Error getting customer");
        }
    }


    public CustomerDto createCustomer(CustomerDto customerDto) {
        try {
            if (customerRepository.existsByIdCardNumber(customerDto.getIdCardNumber())) {
                throw new InvalidArgumentException("The customer that you are trying to create already exists");
            }

            Customer customerToCreate = CustomerDtoMapper.deepMapCustomerDtoToCustomer(customerDto);
            customerRepository.save(customerToCreate);


            return CustomerDtoMapper.deepMapCustomerToCustomerDto(customerToCreate);
        } catch (Exception e) {
            logger.error("Error creating customer", e);
            throw new RuntimeException("Error creating customer");
        }
    }

    public CustomerDto updateCustomer(CustomerDto customerDto) {
        try {

            Optional<Customer> customerToUpdateFromDb = customerRepository.findById(customerDto.getId());

            if (customerToUpdateFromDb.isEmpty()) {
                throw new InvalidArgumentException("The customer that you are trying to update does not exist");
            }

            Customer customerToUpdate = CustomerDtoMapper.deepMapCustomerDtoToCustomer(customerDto);

            if (!customerDto.getIdCardNumber().equalsIgnoreCase(customerToUpdate.getIdCardNumber())) {
                if (customerRepository.findByIdCardNumber(customerDto.getIdCardNumber()).isPresent()) {
                    if (customerToUpdate.getIsDeleted() == 0) {
                        throw new ExistingRecordException("The customer that you are trying to update already exists. Is deactivated");
                    }

                    throw new Exception("The customer that you are trying to update already exists");

                }
            }

            customerToUpdate.setIdCardNumber(customerDto.getIdCardNumber());
            customerToUpdate.setFirstName(customerDto.getFirstName());
            customerToUpdate.setLastName(customerDto.getLastName());

            customerRepository.save(customerToUpdate);
            return CustomerDtoMapper.deepMapCustomerToCustomerDto(customerToUpdate);

        } catch (ServiceException e) {
            logger.error("Error updating customer: " + e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Error updating customer", e);
            throw new RuntimeException("Error updating customer", e);
        }
    }


    public boolean deleteCustomer(Long id) {
        try {

            Optional<Customer> customerToDeleteFromDb = customerRepository.findById(id);

            if (customerToDeleteFromDb.isEmpty()) {
                throw new InvalidArgumentException("The customer that you are trying to delete does not exist");
            }

            Customer customerToDelete = customerToDeleteFromDb.get();

            customerToDelete.setIsDeleted(1);
            customerRepository.save(customerToDelete);
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

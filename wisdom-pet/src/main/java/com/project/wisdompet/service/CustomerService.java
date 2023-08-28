package com.project.wisdompet.service;

import com.project.wisdompet.entity.CustomerEntity;
import com.project.wisdompet.exception.NotFoundException;
import com.project.wisdompet.models.CustomerDTO;
import com.project.wisdompet.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDTO> getAllCustomers(String email) {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        if (StringUtils.hasLength(email)) {
            CustomerEntity customerEntity = this.customerRepository.findByEmail(email);
            customerDTOList.add(this.toDTO(customerEntity));
        } else {
            Iterable<CustomerEntity> entities = this.customerRepository.findAll();
            entities.forEach(customerEntity ->
                    customerDTOList.add(this.toDTO(customerEntity)));
        }

        return customerDTOList;
    }

    public CustomerDTO getCustomerById(Integer id) {
        Optional<CustomerEntity> optional = this.customerRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Customer not found with id" + id);
        }

        return this.toDTO(optional.get());
    }

    public CustomerDTO createOrUpdate(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = this.toEntity(customerDTO);
        customerEntity = this.customerRepository.save(customerEntity);
        return this.toDTO(customerEntity);
    }

    public void deleteCustomer(Integer id) {
        this.customerRepository.deleteById(id);
    }

    private CustomerEntity toEntity(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(customerDTO.getCustomerId());
        customerEntity.setFirstName(customerDTO.getFirstName());
        customerEntity.setLastName(customerDTO.getLastName());
        customerEntity.setEmail(customerDTO.getEmailAddress());
        customerEntity.setPhone(customerDTO.getPhoneNumber());
        customerEntity.setAddress(customerEntity.getAddress());
        return customerEntity;
    }

    private CustomerDTO toDTO(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customerEntity.getCustomerId());
        customerDTO.setFirstName(customerEntity.getFirstName());
        customerDTO.setLastName(customerEntity.getLastName());
        customerDTO.setEmailAddress(customerEntity.getEmail());
        customerDTO.setPhoneNumber(customerEntity.getPhone());
        customerDTO.setAddress(customerEntity.getAddress());
        return customerDTO;
    }
}

package com.project.wisdompet.controller;

import com.project.wisdompet.exception.BadRequestException;
import com.project.wisdompet.models.CustomerDTO;
import com.project.wisdompet.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers(@RequestParam(name = "email", required = false) String email) {
        return customerService.getAllCustomers(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.createOrUpdate(customerDTO);
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomer(@PathVariable("id") Integer id) {
        return this.customerService.getCustomerById(id);
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable("id") Integer id, @RequestBody CustomerDTO customerDTO) {
        if (id != customerDTO.getCustomerId()) {
            throw new BadRequestException(id + "is not the same");
        }
        return this.customerService.createOrUpdate(customerDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteCustomer(@PathVariable("id") Integer id) {
        this.customerService.deleteCustomer(id);
    }
}

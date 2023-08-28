package com.project.wisdompet.controller;

import com.project.wisdompet.exception.BadRequestException;
import com.project.wisdompet.models.ServiceDTO;
import com.project.wisdompet.service.ServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    public final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public List<ServiceDTO> getAllServices() {
        return serviceService.getAllServices();
    }

    @GetMapping("/{id}")
    public ServiceDTO getService(@PathVariable("id") Integer id) {
        return this.serviceService.getServiceById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceDTO createService(@RequestBody ServiceDTO serviceDTO) {
        return serviceService.createOrUpdate(serviceDTO);
    }

    @PutMapping("/{id}")
    public ServiceDTO updateService(@PathVariable("id") Integer id, @RequestBody ServiceDTO serviceDTO) {
        if (id != serviceDTO.getServiceId()) {
            throw new BadRequestException(id + "Is not matching");
        }
        return this.serviceService.createOrUpdate(serviceDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteService(@PathVariable("id") Integer id) {
        this.serviceService.deleteService(id);
    }
}

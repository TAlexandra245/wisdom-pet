package com.project.wisdompet.service;

import com.project.wisdompet.entity.ServiceEntity;
import com.project.wisdompet.exception.NotFoundException;
import com.project.wisdompet.models.ServiceDTO;
import com.project.wisdompet.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceService(@Qualifier("services") ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<ServiceDTO> getAllServices() {
        List<ServiceDTO> serviceDTOS = new ArrayList<>();
        Iterable<ServiceEntity> entities = this.serviceRepository.findAll();
        entities.forEach(entity -> serviceDTOS.add(this.toDTO(entity)));
        return serviceDTOS;
    }

    public ServiceDTO getServiceById(Integer id) {
        Optional<ServiceEntity> optional = this.serviceRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Service with id: " + id + " not found.");
        }
        return this.toDTO(optional.get());
    }

    public ServiceDTO createOrUpdate(ServiceDTO serviceDTO) {
        ServiceEntity entity = this.toEntity(serviceDTO);
        entity = this.serviceRepository.save(entity);
        return this.toDTO(entity);
    }

    public void deleteService(Integer id) {
        this.serviceRepository.deleteById(id);
    }

    private ServiceDTO toDTO(ServiceEntity serviceEntity) {
        return new ServiceDTO(serviceEntity.getServiceId(), serviceEntity.getName(), serviceEntity.getPrice());
    }

    private ServiceEntity toEntity(ServiceDTO serviceDTO) {
        ServiceEntity entity = new ServiceEntity();
        entity.setServiceId(serviceDTO.getServiceId());
        entity.setName(serviceDTO.getName());
        entity.setPrice(serviceDTO.getServicePrice());
        return entity;
    }
}



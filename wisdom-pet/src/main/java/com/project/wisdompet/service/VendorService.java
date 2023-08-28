package com.project.wisdompet.service;

import com.project.wisdompet.entity.VendorEntity;
import com.project.wisdompet.exception.NotFoundException;
import com.project.wisdompet.models.VendorDTO;
import com.project.wisdompet.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    private VendorDTO toEntity(VendorEntity vendorEntity) {
        return new VendorDTO(vendorEntity.getVendorId(), vendorEntity.getName(), vendorEntity.getContact(), vendorEntity.getPhone(), vendorEntity.getEmail(), vendorEntity.getAddress());
    }

    public List<VendorDTO> getAllVendors() {
        List<VendorDTO> vendorDTOList = new ArrayList<>();
        Iterable<VendorEntity> vendorEntities = this.vendorRepository.findAll();
        vendorEntities.forEach(vendorEntity ->
                vendorDTOList.add(this.toEntity(vendorEntity)));

        return vendorDTOList;
    }

    public void deleteVendors(Integer id) {
        this.vendorRepository.deleteById(id);
    }

    public VendorDTO findById(Integer id) {
        Optional<VendorEntity> entity = this.vendorRepository.findById(id);
        if (entity.isEmpty()) {
            throw new NotFoundException("Vendor not found with id: " + id);
        }
        return this.toEntity(entity.get());
    }

    public VendorDTO createOrUpdate(VendorDTO vendorDTO) {
        VendorEntity vendorEntity = this.toEntity(vendorDTO);
        vendorEntity = this.vendorRepository.save(vendorEntity);
        return this.toEntity(vendorEntity);
    }

    private VendorEntity toEntity(VendorDTO vendorDTO) {
        VendorEntity vendorEntity = new VendorEntity();
        vendorEntity.setVendorId(vendorDTO.getVendorId());
        vendorEntity.setContact(vendorDTO.getContact());
        vendorEntity.setName(vendorDTO.getName());
        vendorEntity.setEmail(vendorDTO.getEmailAddress());
        vendorEntity.setAddress(vendorDTO.getAddress());
        return vendorEntity;
    }

    private VendorDTO toDTO(VendorEntity vendorEntity) {
        return new VendorDTO(vendorEntity.getVendorId(), vendorEntity.getName(), vendorEntity.getContact(), vendorEntity.getPhone(), vendorEntity.getEmail(), vendorEntity.getAddress());
    }
}

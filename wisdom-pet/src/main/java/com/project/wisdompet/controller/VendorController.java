package com.project.wisdompet.controller;

import com.project.wisdompet.exception.BadRequestException;
import com.project.wisdompet.models.VendorDTO;
import com.project.wisdompet.service.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    public final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public List<VendorDTO> getAllVendors() {
        return this.vendorService.getAllVendors();
    }

    @GetMapping("/{id}")
    public VendorDTO getVendorById(@PathVariable("id") Integer id) {
        return this.vendorService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createVendor(@RequestBody VendorDTO vendorDTO) {
        return this.vendorService.createOrUpdate(vendorDTO);
    }

    @PutMapping("/{id}")
    public VendorDTO updateVendor(@PathVariable("id") Integer id, @RequestBody VendorDTO vendorDTO) {
        if (id != vendorDTO.getVendorId()) {
            throw new BadRequestException(id + "does not match");
        }
        return this.vendorService.createOrUpdate(vendorDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteVendor(@PathVariable("id") Integer id) {
        this.vendorService.deleteVendors(id);
    }
}

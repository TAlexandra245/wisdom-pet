package com.project.wisdompet.service;

import com.project.wisdompet.entity.ProductEntity;
import com.project.wisdompet.exception.NotFoundException;
import com.project.wisdompet.models.ProductDTO;
import com.project.wisdompet.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        Iterable<ProductEntity> productEntities = this.productRepository.findAll();
        productEntities.forEach(productEntity ->
                productDTOList.add(this.toDTO(productEntity)));
        return productDTOList;
    }

    public ProductDTO getProductById(Integer id) {
        Optional<ProductEntity> optional = this.productRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Product not found with id: " + id);
        }
        return this.toDTO(optional.get());
    }

    public ProductDTO createOrUpdate(ProductDTO productDTO) {
        ProductEntity productEntity = this.toEntity(productDTO);
        productEntity = this.productRepository.save(productEntity);
        return this.toDTO(productEntity);
    }

    public void deleteProduct(Integer id) {
        checkIfExistsById(id);
        this.productRepository.deleteById(id);
    }

    private ProductEntity toEntity(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductId(productDTO.getProductId());
        productEntity.setName(productDTO.getName());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setVendorId(productDTO.getVendorId());
        return productEntity;
    }

    private ProductDTO toDTO(ProductEntity productEntity) {
        return new ProductDTO(productEntity.getProductId(), productEntity.getName(), productEntity.getPrice(), productEntity.getVendorId());
    }

    private void checkIfExistsById(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Product with id: %d does not exists".formatted(id));
        }
    }
}

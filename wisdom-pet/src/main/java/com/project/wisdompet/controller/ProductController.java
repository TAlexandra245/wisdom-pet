package com.project.wisdompet.controller;

import com.project.wisdompet.exception.BadRequestException;
import com.project.wisdompet.exception.NotFoundException;
import com.project.wisdompet.models.ProductDTO;
import com.project.wisdompet.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        return this.productService.createOrUpdate(productDTO);
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable("id") Integer id) {
        return this.productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable("id") Integer id, ProductDTO productDTO) {
        if (id != productDTO.getProductId()) {
            throw new BadRequestException("Id does not match");
        }
        return this.productService.createOrUpdate(productDTO);
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Integer id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>("Product has been successfully deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

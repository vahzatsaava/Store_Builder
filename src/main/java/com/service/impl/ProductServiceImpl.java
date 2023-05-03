package com.service.impl;

import com.dto.ProductDto;
import com.model.Product;
import com.repository.ProductRepository;
import com.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDto create(Product product) {
        productRepository.save(product);
        return ProductDto.modifierToProductDto(product);
    }

    @Override
    public ProductDto update(Product product, Long id) {
        Product updatedProduct = productRepository.findById(id).orElse(null);
        if (updatedProduct == null) {
            throw new NullPointerException(" product with this id - " + id + " not found");
        }

        updatedProduct.setProductName(product.getProductName());
        updatedProduct.setPrice(product.getPrice());
        productRepository.save(updatedProduct);
        return ProductDto.modifierToProductDto(updatedProduct);

    }

    @Override
    public List<ProductDto> getAll() {
        return productRepository.findAll().stream().map(ProductDto::modifierToProductDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new NullPointerException(" product with this id - " + id + " not found");
        }
        productRepository.delete(product);
    }

    @Override
    public ProductDto getById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new NullPointerException(" product with this id - " + id + " not found");
        }
        return ProductDto.modifierToProductDto(product);
    }


    @Override
    public ProductDto getByName(String productName) {
        Product product = productRepository.findProductByProductName(productName);
        if (product == null) {
            throw new NullPointerException(" product with this name - " + productName + " not found");
        }
        return ProductDto.modifierToProductDto(product);
    }
}

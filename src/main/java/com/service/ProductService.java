package com.service;

import com.dto.ProductDto;
import com.model.Product;

import java.util.List;

public interface ProductService {
    ProductDto create(Product product);
    ProductDto update(Product product,Long id);
    List<ProductDto> getAll();
    void delete(Long id);
    ProductDto getById(Long id);
    ProductDto getByName(String productName);

}

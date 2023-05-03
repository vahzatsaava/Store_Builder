package com.dto;

import com.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String productName;
    private BigDecimal price;
    private String url;
    private String category;

    public static ProductDto modifierToProductDto(Product product) {
        return new ProductDto(product.getId(), product.getProductName(), product.getPrice(), product.getUrl(), product.getCategory());
    }

    public static Product modifierToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());
        product.setCategory(productDto.getCategory());
        product.setUrl(productDto.getUrl());
        return product;
    }
}

package com.example.store_builder.impl;

import com.model.Product;
import com.repository.ProductRepository;
import com.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product getProductForTest() {
        Product product = new Product();
        product.setId(1L);
        product.setProductName("potato");
        product.setPrice(BigDecimal.valueOf(12));
        product.setCategory("vegetables");
        product.setKeepRooms(null);

        return product;
    }

    @Test
    void create_Successful() {
        Product product = getProductForTest();
        when(productRepository.save(any(Product.class))).thenReturn(product);
        productService.create(product);
        verify(productRepository, times(1)).save(any(product.getClass()));
    }


    @Test
    void update_Successful() {
        Product product = getProductForTest();
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product updatedProduct = getProductForTest();
        productService.update(updatedProduct, 1L);

        verify(productRepository, times(1)).save(updatedProduct);
    }

    @Test
    void getAll_Successful() {
        List<Product> list = new ArrayList<>();
        list.add(getProductForTest());

        when(productRepository.findAll()).thenReturn(list);

        productService.getAll();

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getAll_unSuccessful() {
        when(productRepository.findAll()).thenReturn(null);

        assertThrows(NullPointerException.class, () -> productService.getAll());

        verify(productRepository, times(1)).findAll();

    }

    @Test
    void delete_Successful() {
        Product product = getProductForTest();
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        assertDoesNotThrow(() -> productService.delete(anyLong()));

        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void delete_unSuccessful() {
        Product product = getProductForTest();
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () -> productService.delete(anyLong()));

        verify(productRepository, never()).delete(any(Product.class));
    }

    @Test
    void getById_Successful() {
        Product product = getProductForTest();
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        productService.getById(anyLong());

        verify(productRepository,times(1)).findById(anyLong());
    }
    @Test
    void getById_unSuccessful() {
        Product product = getProductForTest();
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () -> productService.getById(anyLong()));

        verify(productRepository,times(1)).findById(anyLong());
    }

    @Test
    void getByName_Successful() {
        Product product = getProductForTest();
        when(productRepository.findProductByProductName(anyString())).thenReturn(product);

        productService.getByName(anyString());

        verify(productRepository,times(1)).findProductByProductName(anyString());

    }
    @Test
    void getByName_unSuccessful() {

        when(productRepository.findProductByProductName(anyString())).thenReturn(null);

        assertThrows(NullPointerException.class, () -> productService.getByName(anyString()));

        verify(productRepository,times(1)).findProductByProductName(anyString());

    }
}
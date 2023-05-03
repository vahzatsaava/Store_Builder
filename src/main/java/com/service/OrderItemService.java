package com.service;

import com.dto.OrderItemDto;

import java.util.List;

public interface OrderItemService {

    OrderItemDto save(Long orderId, Long productId, Integer quantity);
    OrderItemDto update(Long orderItemsId, Long orderId, Long productId, Integer quantity);
    List<OrderItemDto> getAll();
    void delete(Long id);
    OrderItemDto getById(Long id);
}

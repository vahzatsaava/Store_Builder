package com.service;

import com.dto.OrderDtos;

import java.util.List;

public interface OrderService {
    OrderDtos createOrder(OrderDtos order);

    OrderDtos updateOrder(Long id, OrderDtos order);

    void deleteOrder(Long id);

    List<OrderDtos> getOrders();

    OrderDtos findOrderById(Long id);
}

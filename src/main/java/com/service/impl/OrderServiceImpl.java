package com.service.impl;

import com.dto.OrderDtos;
import com.model.Order;
import com.model.User;
import com.repository.OrderRepository;
import com.repository.UserRepository;
import com.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    public OrderDtos createOrder(OrderDtos order) {
        User user = userRepository.findById(order.getUserId()).orElse(null);
        Order orderNew = new Order();
        orderNew.setUser(user);
        orderNew.setOrderDate(order.getOrderDate());
        orderRepository.save(orderNew);
        return OrderDtos.transformOrderToOrderDtos(orderNew);
    }


    @Override
    public OrderDtos updateOrder(Long id, OrderDtos order) {
        Order newOrder = orderRepository.findById(id).orElse(null);
        if (newOrder == null) {
            throw new NullPointerException("class with id " + id + " not found");
        }
        User user = userRepository.findById(order.getUserId()).orElse(null);
        newOrder.setOrderDate(order.getOrderDate());
        newOrder.setUser(user);
        orderRepository.save(newOrder);
        return OrderDtos.transformOrderToOrderDtos(newOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            throw new NullPointerException("class with id " + id + " not found");
        }
        orderRepository.delete(order);

    }

    @Override
    public List<OrderDtos> getOrders() {
        return orderRepository.findAll().stream().map(OrderDtos::transformOrderToOrderDtos).collect(Collectors.toList());
    }

    @Override
    public OrderDtos findOrderById(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            throw new NullPointerException("class with id " + id + " not found");
        }
        return OrderDtos.transformOrderToOrderDtos(order);

    }


}

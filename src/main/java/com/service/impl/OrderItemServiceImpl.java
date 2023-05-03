package com.service.impl;

import com.dto.OrderDtos;
import com.dto.OrderItemDto;
import com.dto.ProductDto;
import com.model.Order;
import com.model.OrderItem;
import com.model.User;
import com.repository.OrderItemRepository;
import com.service.OrderItemService;
import com.service.OrderService;
import com.service.ProductService;
import com.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderService orderService;
    private final ProductService productService;
    private final UserService userService;


    @Override
    public OrderItemDto save(Long orderId, Long productId, Integer quantity) {
        OrderDtos orderDtos = orderService.findOrderById(orderId);
        ProductDto productDto = productService.getById(productId);

        User user = userService.findById(orderDtos.getUserId());
        Order updatedOrder = new Order();
        updatedOrder.setUser(user);
        updatedOrder.setId(orderDtos.getId());
        updatedOrder.setOrderDate(orderDtos.getOrderDate());

        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(quantity);
        orderItem.setProduct(ProductDto.modifierToProduct(productDto));
        orderItem.setOrder(updatedOrder);


        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return OrderItemDto.toOrderItemDto(savedOrderItem);
    }


    @Override
    public OrderItemDto update(Long orderItemsId, Long orderId, Long productId, Integer quantity) {
        OrderItem orderItemUpdated = orderItemRepository.findById(orderItemsId).orElseThrow(() -> new EntityNotFoundException("Entity with id " + orderItemsId + " not found"));
        orderItemRepository.save(orderItemUpdated);

        var orderDtos = orderService.findOrderById(orderId);
        var productDto = productService.getById(productId);

        User user = userService.findById(orderDtos.getUserId());
        Order updatedOrder = new Order();
        updatedOrder.setUser(user);
        updatedOrder.setId(orderDtos.getId());
        updatedOrder.setOrderDate(orderDtos.getOrderDate());


        orderItemUpdated.setQuantity(quantity);
        orderItemUpdated.setProduct(ProductDto.modifierToProduct(productDto));
        orderItemUpdated.setOrder(updatedOrder);


        orderItemRepository.save(orderItemUpdated);
        return OrderItemDto.toOrderItemDto(orderItemUpdated);
    }

    @Override
    public List<OrderItemDto> getAll() {
        return orderItemRepository.findAll().stream().map(OrderItemDto::toOrderItemDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        OrderItem orderItemUpdated = orderItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
        orderItemRepository.delete(orderItemUpdated);
    }

    @Override
    public OrderItemDto getById(Long id) {
        OrderItem orderItemUpdated = orderItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
        return OrderItemDto.toOrderItemDto(orderItemUpdated);
    }
}

package com.dto;

import com.model.Order;
import lombok.Data;

import java.sql.Date;

@Data
public class OrderDtos {
    private Long id;
    private Long userId;
    private Date orderDate;

    public static OrderDtos transformOrderToOrderDtos(Order order) {
        OrderDtos orderDtos = new OrderDtos();
        orderDtos.setId(order.getId());
        orderDtos.setOrderDate(order.getOrderDate());
        orderDtos.setUserId(order.getUser().getId());
        return orderDtos;
    }


}

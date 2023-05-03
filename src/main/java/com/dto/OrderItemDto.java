package com.dto;

import com.model.OrderItem;
import lombok.Data;

@Data
public class OrderItemDto {
    private Long id;
    private Long orderDtoId;
    private Long productDtoID;
    private Integer quantity;


    public static OrderItemDto toOrderItemDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setProductDtoID(orderItem.getProduct().getId());
        orderItemDto.setOrderDtoId(orderItem.getOrder().getId());
        return orderItemDto;
    }

}

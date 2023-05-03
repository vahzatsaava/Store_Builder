package com.controller;

import com.dto.OrderItemDto;
import com.service.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orderItems")
@AllArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;

    @GetMapping("/all")
    ResponseEntity<List<OrderItemDto>> getAllOrderItems() {
        List<OrderItemDto> orderItems = orderItemService.getAll();
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    ResponseEntity<OrderItemDto> getOrderItemByID(@PathVariable Long id) {
        return new ResponseEntity<>(orderItemService.getById(id), HttpStatus.OK);
    }


    @PostMapping("/create/{orderId}/{productId}/{quantity}")
    public ResponseEntity<OrderItemDto> createOrderItems(@PathVariable Long orderId, @PathVariable Long productId, @PathVariable Integer quantity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderItemService.save(orderId,productId,quantity));
    }


    @PutMapping("/update/{orderItem}/{orderId}/{productId}/{quantity}")
    public ResponseEntity<OrderItemDto> updateOrderItems(@PathVariable Long orderItem, @PathVariable Long orderId, @PathVariable Long productId, @PathVariable Integer quantity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderItemService.update(orderItem, orderId,productId,quantity));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrderItems(@PathVariable Long id) {
        orderItemService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

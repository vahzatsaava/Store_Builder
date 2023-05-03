package com.controller;

import com.dto.OrderDtos;
import com.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @GetMapping("/all")
    ResponseEntity<List<OrderDtos>> getAllOrders() {
        List<OrderDtos> orders = orderService.getOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    ResponseEntity<OrderDtos> getOrderByID(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.findOrderById(id), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<OrderDtos> createOrder(@RequestBody OrderDtos order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(order));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<OrderDtos> updateOrder(@PathVariable Long id, @RequestBody OrderDtos order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.updateOrder(id, order));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.bgarage.orderservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bgarage.orderservice.aspect.LogExecutionTime;
import com.bgarage.orderservice.model.OrderRestModel;
import com.bgarage.orderservice.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create/{supplier}")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @LogExecutionTime
    public String placeOrder(@RequestBody OrderRestModel orderRestModel) {
        return orderService.placeOrder(orderRestModel);
    }

    public String fallbackMethod(OrderRestModel orderRestModel, RuntimeException runtimeException) {
        return "Order can't be placed, please try again after sometime!";
    }

}

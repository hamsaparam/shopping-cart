package com.api.order.shopping.rest;

import com.api.order.shopping.models.CreateOrderDetails;
import com.api.order.shopping.models.Order;
import com.api.order.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService service;

    @Autowired
    public OrderController (OrderService orderService){
        this.service = orderService;

    };

    @PostMapping(produces = "application/json",consumes = "application/json")
    public ResponseEntity createOrder(@RequestBody CreateOrderDetails createOrderDetails ) throws URISyntaxException {
        Order createdOrder = service.createOrder(createOrderDetails);
        URI location = new URI("/orders/" + createdOrder.getOrderId());
        return ResponseEntity.created(location).body(createdOrder);



    }
}

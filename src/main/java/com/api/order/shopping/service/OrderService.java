package com.api.order.shopping.service;

import com.api.order.shopping.models.CreateOrderDetails;
import com.api.order.shopping.models.Order;
import com.api.order.shopping.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(CreateOrderDetails createOrderDetails) {
        Order order = Order.createOrder(createOrderDetails.getOrderName(), createOrderDetails.getItems());
        orderRepository.save(order);
        return order;

    }

}


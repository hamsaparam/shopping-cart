package com.api.order.shopping.rest;


import com.api.order.shopping.models.*;
import com.api.order.shopping.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.net.URISyntaxException;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderControllerTest {


    @Test
    void shouldCreateOrderAndReturnOrderId() throws URISyntaxException {

        //Given
        OrderService service = mock(OrderService.class);
        OrderController subject = new OrderController(service);

        CreateOrderDetails createOrderDetails = CreateOrderDetails.builder()
                .orderName("some-order")
                .items(singletonList(
                        CreateOrderItem
                                .builder()
                                .itemName("some-item")
                                .itemQuantity(2)
                                .unitPrice(BigDecimal.ONE)
                                .build()
                ))
                .build();

        Order expectedOrder = Order.builder().orderName("some-order").orderId("some-oderid").orderStatus(OrderStatus.CREATED).orderAmount(BigDecimal.ONE)
                .items(asList(Item.builder().itemName("some-item").itemQuantity(1).unitPrice(BigDecimal.ONE).build())).build();

        when(service.createOrder(any(CreateOrderDetails.class))).thenReturn(expectedOrder);


        //When
        ResponseEntity<Order> createdOrder = subject.createOrder(createOrderDetails);


        //Then
        assertThat(createdOrder.getBody()).isEqualToIgnoringGivenFields(expectedOrder, "orderId", "items", "orderAmount", "orderStatus");
        verify(service).createOrder(any(CreateOrderDetails.class));
        assertThat(createdOrder.getBody().getOrderStatus()).isEqualTo(OrderStatus.CREATED);



    }

}

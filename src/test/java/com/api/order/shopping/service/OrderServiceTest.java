package com.api.order.shopping.service;

import com.api.order.shopping.models.*;
import com.api.order.shopping.repository.OrderRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OrderServiceTest {

    @Test
    void shouldCreateOrderAndReturnOrderId() {

        // Given
        OrderRepository orderRepository = mock(OrderRepository.class);
        OrderService subject = new OrderService(orderRepository);

        Item item1 = Item
                .builder()
                .itemName("some-item")
                .itemQuantity(2)
                .unitPrice(BigDecimal.ONE)
                .build();

        Item item2 = Item
                .builder()
                .itemName("some-item2")
                .itemQuantity(2)
                .unitPrice(BigDecimal.valueOf(2))
                .build();

        Order expectedCreatedOrder = Order
                .builder()
                .orderName("some-order")
                .orderAmount(BigDecimal.valueOf(6))
                .orderStatus(OrderStatus.CREATED)
                .items(asList(
                        item1,
                        item2
                ))
                .build();

        CreateOrderDetails createOrderDetails = CreateOrderDetails.builder()
                .orderName("some-order")
                .items(asList(
                        CreateOrderItem
                                .builder()
                                .itemName("some-item")
                                .itemQuantity(2)
                                .unitPrice(BigDecimal.ONE)
                                .build(),
                        CreateOrderItem
                                .builder()
                                .itemName("some-item2")
                                .itemQuantity(2)
                                .unitPrice(BigDecimal.valueOf(2))
                                .build()
                ))
                .build();

        // When
        Order actualCreatedOrder = subject.createOrder(createOrderDetails);


        // Then
        assertThat(actualCreatedOrder).isEqualToIgnoringGivenFields(expectedCreatedOrder, "orderId", "items");
        assertThat(actualCreatedOrder.getOrderId()).isNotBlank();

        Item actualItem1 = actualCreatedOrder.getItems().get(0);
        assertThat(actualItem1).isEqualToIgnoringGivenFields(item1, "itemId");
        assertThat(actualItem1.getItemId()).isNotBlank();

        Item actualItem2 = actualCreatedOrder.getItems().get(1);
        assertThat(actualItem2).isEqualToIgnoringGivenFields(item2, "itemId");
        assertThat(actualItem2.getItemId()).isNotBlank();

        verify(orderRepository).save(any(Order.class));



    }

}

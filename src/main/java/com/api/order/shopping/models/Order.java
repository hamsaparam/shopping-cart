package com.api.order.shopping.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name ="orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    private String orderId;

    @Column
    private String orderName;

    @Column
    private BigDecimal orderAmount;

    @Column
    private OrderStatus orderStatus;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Item> items;


    public static Order createOrder(String orderName, List<CreateOrderItem> createOrderItems) {
        Order order = Order.builder()
                .orderId(UUID.randomUUID().toString())
                .orderName(orderName)
                .items(addItems(createOrderItems))
                .orderStatus(OrderStatus.CREATED)
                .build();
        order.setOrderAmount(calculateAmount(order));
        return order;
    }

    private static List<Item> addItems(List<CreateOrderItem> createOrderItems) {
        return createOrderItems
                .stream()
                .map(item -> Item.createItem(item.getItemName(), item.getItemQuantity(), item.getUnitPrice())).collect(Collectors.toList());

    }

    private static BigDecimal calculateAmount(Order order) {
        BigDecimal totalOrderAmount = BigDecimal.ZERO;

        List<Item> itemList = order.getItems();
        for (Item item : itemList) {
            totalOrderAmount = totalOrderAmount.add(item.getUnitPrice().multiply(new BigDecimal(item.getItemQuantity())));
        }
        order.setOrderAmount(totalOrderAmount);
        return order.getOrderAmount();
    }


}

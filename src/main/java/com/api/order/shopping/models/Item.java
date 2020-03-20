package com.api.order.shopping.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name ="item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    private String itemId;

    @Column
    private String itemName;

    @Column
    private Integer itemQuantity;

    @Column
    private BigDecimal unitPrice;

    public static Item createItem(String itemName, Integer itemQuantity, BigDecimal unitPrice) {
        return Item.builder()
                .itemId(UUID.randomUUID().toString())
                .itemName(itemName)
                .itemQuantity(itemQuantity)
                .unitPrice(unitPrice)
                .build();
    }
}

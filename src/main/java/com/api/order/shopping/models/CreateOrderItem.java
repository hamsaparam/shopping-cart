package com.api.order.shopping.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderItem {

    private String itemName;

    private Integer itemQuantity;

    private BigDecimal unitPrice;

}

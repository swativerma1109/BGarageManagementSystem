package com.bgarage.orderservice.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderRestModel {
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
    private String supplier;

}

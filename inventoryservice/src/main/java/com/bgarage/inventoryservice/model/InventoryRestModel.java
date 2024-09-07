package com.bgarage.inventoryservice.model;

import lombok.Data;

@Data
public class InventoryRestModel {
    private String skuCode;
    private Integer quantity;
    private String supplier;
    private Integer minOrderQuantity;
    private Integer thresholdLimit;

}

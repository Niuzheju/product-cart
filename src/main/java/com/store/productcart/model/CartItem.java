package com.store.productcart.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CartItem {

    private String title;

    private Double price;

    private Integer count;

    private Integer productId;

}

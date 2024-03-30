package com.example.springproject2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductToBePatched {

    private Double price;
    private Integer quantity;
}

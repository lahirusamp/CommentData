package com.sl.shopingCart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(length = 30)
    private String CartonName;
    @Column(length = 20)
    private Integer cartonSize;
    @Column(length = 20)
    private Float cartonPrice;
    @Column(length = 20)
    private Float cartonPriceRatio;
    @Column(length = 20)
    private Float cartonDiscount;

}

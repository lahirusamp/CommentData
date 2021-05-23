package com.sl.shopingCart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sl.shopingCart.entity.Product;

public interface ProductsRepository extends JpaRepository<Product, Long> {

}

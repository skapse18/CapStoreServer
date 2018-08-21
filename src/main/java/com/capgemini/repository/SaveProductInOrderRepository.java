package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.model.Product;

public interface SaveProductInOrderRepository extends JpaRepository<Product,Integer> {

}

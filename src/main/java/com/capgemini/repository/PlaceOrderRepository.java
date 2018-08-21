package com.capgemini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.model.Cart;
import com.capgemini.model.Customer;
import com.capgemini.model.OrderDetails;
import com.capgemini.model.Product;

@Repository
public interface PlaceOrderRepository extends JpaRepository<OrderDetails,Integer> {

	
	
	@Query(value="SELECT cart from Cart cart where cart.id=(:cart_id)")
	Cart getCart(@Param(value="cart_id") int cart_id);
	
	@Query(value="SELECT cart.product from Cart cart where cart.id=(:cart_id)")
	List<Product> getProduct(@Param(value="cart_id") int cart_id);

	@Query(value="SELECT cust.id from Customer cust where cust.cart.id=(:cart_id)")
	int getCustomer1(@Param(value="cart_id") int cart_id);
	
}

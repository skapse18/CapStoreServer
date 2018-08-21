package com.capgemini.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.exception.ProductNotFoundException;
import com.capgemini.model.Cart;
import com.capgemini.model.Customer;
import com.capgemini.model.Discount;
import com.capgemini.model.OrderDetails;
import com.capgemini.model.Product;
import com.capgemini.repository.PlaceOrderRepository;
import com.capgemini.repository.SaveProductInOrderRepository;

@Service("service")

public class PlaceOrderImpl implements PlaceOrderService {

	@Autowired(required = true)
	PlaceOrderRepository repository;
	
	@Autowired																		//creating repository's object
	SaveProductInOrderRepository repository1;
	
	public PlaceOrderImpl(PlaceOrderRepository repo) {
		super();
		this.repository = repo;
	}
	
	@Override
	public int getCart(int cart_id) throws ProductNotFoundException {

		Cart cart =  repository.getCart(cart_id);										
		if(cart==null)
		{
			throw new ProductNotFoundException();
		}
			
		int quantity=0;
		List<Product> productList = repository.getProduct(cart_id);						//fetching list of products from cart
		for(Product product : productList)
		{	
			quantity=product.getQuantity();											//getting product quantity
		}
		return quantity;
	}

	@Override
	@Transactional
	public OrderDetails updatingOrder(int cart_id) throws ProductNotFoundException {
		
		int id;
		float totalAmount=0;
		int countQuantity=0;
		String status;
		
		int customer_id=repository.getCustomer1(cart_id);									//fetching customer id from cart id
	
		Customer customer = new Customer();	
		customer.setId(customer_id);												//fetching customer object from customer id
		
		List<Product> listProduct = repository.getProduct(cart_id);						//fetching product list from given id of cart
		
		for (Product product : listProduct) {				

			int quantity = product.getQuantity();
		
			/*System.out.println("Before placing Order - ");
			System.out.println("Product Quantity: " + quantity);*/
			int updatedQuantity = quantity - 1;
			product.setQuantity(updatedQuantity);
			/*System.out.println("After placing Order - ");
			System.out.println("Product Quantity: " + updatedQuantity);*/
			
			
		
			totalAmount = totalAmount+product.getCost();							//calculating total amount of the order
			
							
			countQuantity++;														//setting total count of the products
			repository1.save(product);
			
		}	
		
		List<Product> updatedProduct=repository.getProduct(cart_id);
		
		OrderDetails order = new OrderDetails();									//setting attributes of order details
		order.setAmount(totalAmount);
		order.setQuantity(countQuantity);
		order.setProduct(updatedProduct);
		order.setCustomer(customer);
		order.setStatus("Pending");
		repository.save(order);
			
		return order;
	}
}

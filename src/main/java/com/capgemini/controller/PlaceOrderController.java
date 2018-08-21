package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.exception.ProductNotFoundException;
import com.capgemini.model.Cart;
import com.capgemini.model.OrderDetails;
import com.capgemini.service.PlaceOrderService;

@RestController
public class PlaceOrderController {

	@Autowired
	PlaceOrderService service;
	
	@RequestMapping(value="/get")
	public String startPage() {
		return "Hello World";
		
	}
	
	@RequestMapping(value="/place")
	public int myFunct(@RequestParam(value="cart_id", required=false)int cart_id) throws ProductNotFoundException
	{
		return service.getCart(cart_id);
		
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public OrderDetails myFunct1(@RequestParam(value="cart_id", required=false)int cart_id) throws ProductNotFoundException
	{
		return service.updatingOrder(cart_id);
		
	}
	
}

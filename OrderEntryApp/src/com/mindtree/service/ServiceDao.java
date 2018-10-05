package com.mindtree.service;

import java.util.Date;
import java.util.List;

import com.mindtree.entity.Order;
import com.mindtree.entity.Product;
import com.mindtree.entity.ProductDto;
import com.mindtree.entity.User;
import com.mindtree.exceptions.FetchException;
import com.mindtree.exceptions.PersistException;

public interface ServiceDao {

	List<Product> listAllProducts() throws FetchException;

	boolean validateLogin(User user) throws FetchException;

	int addProduct(Product product) throws PersistException;

	Integer placeOrder(String userName, List<ProductDto> list)
			throws PersistException;

	List<Order> getOrderSummary(Date date) throws FetchException;

}

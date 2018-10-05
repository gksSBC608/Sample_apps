/**
 * 
 */
package com.mindtree.dao;

import java.util.Date;
import java.util.List;

import com.mindtree.entity.Order;
import com.mindtree.entity.Product;
import com.mindtree.entity.User;
import com.mindtree.exceptions.DaoException;

/**
 * @author M1030608
 *
 */
public interface OrderDao {

	int addProduct(Product product) throws DaoException;


	List<Product> getAllProducts() throws DaoException;

	boolean validateLogin(User user) throws DaoException;

	int placeOrder(Order order) throws DaoException;


	List<Order> getOrderSummary(Date date) throws DaoException;

}

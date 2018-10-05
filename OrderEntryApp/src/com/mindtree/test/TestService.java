package com.mindtree.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mindtree.service.*;
import com.mindtree.entity.*;
import com.mindtree.exceptions.FetchException;
import com.mindtree.exceptions.PersistException;

public class TestService {

	private static ServiceDao service;
	private static Product product;
	private static List<ProductDto> list;
	private static User user;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new Service();
		product = new Product();
		product.setId("M1001");
		product.setName("Micromax Canvas");
		product.setCategory("Mobile");
		product.setDetails("Low cost phablet");
		product.setPrice(12000.0);
		user = new User();
		user.setId(0);
		user.setRole("customer");
		user.setUserName("customer");
		user.setPassword("welcome@123");

	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testValidateLogin() throws FetchException {
		assertTrue(service.validateLogin(user));
	}

	@Test
	public void testListAllProducts() throws FetchException {
		assertTrue(service.listAllProducts().size() > 0);
	}

	@Test
	public void testPlaceOrder() throws PersistException {
		List<ProductDto> list = new ArrayList<ProductDto>();
		ProductDto p = new ProductDto();
		p.setProduct(product);
		p.setQuantity(1);
		list.add(p);
		assertNotNull(service.placeOrder("customer", list));
	}

	@Test
	public void testAddProduct() throws PersistException {
		Product product1 = new Product();
		product1.setId("M1009");
		product1.setName("Nokia Lumia");
		product1.setCategory("Mobile");
		product1.setPrice(12000.00);
		product1.setDetails("asd");
		assertTrue(service.addProduct(product1) > 0);

	}

	@Test(expected = PersistException.class)
	public void testAddProductNegative() throws PersistException {

		assertTrue(service.addProduct(product) > 0);

	}

	@Test
	public void testGetOrderSummary() throws ParseException, FetchException {
		Date date = new SimpleDateFormat("dd-MM-yyyy").parse("11-05-2015");
		assertTrue(service.getOrderSummary(date).size() > 0);
	}

}

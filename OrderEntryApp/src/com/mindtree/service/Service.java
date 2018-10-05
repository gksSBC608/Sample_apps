/**
 * 
 */
package com.mindtree.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.mindtree.dao.jdbc.OrderDaoImpl;
import com.mindtree.entity.Order;
import com.mindtree.entity.Product;
import com.mindtree.entity.ProductDto;
import com.mindtree.entity.User;
import com.mindtree.exceptions.DaoException;
import com.mindtree.exceptions.FetchException;
import com.mindtree.exceptions.PersistException;

/**
 * @author M1030608
 *
 */
public class Service implements ServiceDao {

	/**
	 * 
	 */
	public Service() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param user
	 * @return
	 * @throws FetchException
	 */
	@Override
	public boolean validateLogin(User user) throws FetchException {
		// TODO Auto-generated method stub
		try {
			return new OrderDaoImpl().validateLogin(user);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new FetchException(e.getMessage());
		}
	}

	/**
	 * 
	 */
	@Override
	public List<Product> listAllProducts() throws FetchException {
		// TODO Auto-generated method stub
		try {
			return new OrderDaoImpl().getAllProducts();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new FetchException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param userName
	 * @param list
	 * @throws PersistException
	 */
	@Override
	public Integer placeOrder(String userName, List<ProductDto> list)
			throws PersistException {
		// TODO Auto-generated method stub
		Order order = new Order();
		for (ProductDto dto : list) {
			order.setProduct(dto.getProduct());
			order.setOrderDate(new java.util.Date());
			double totalPrice = dto.getQuantity() * dto.getProduct().getPrice();
			order.setTotalPrice(totalPrice);
			int id = 0;
			try {
				id = new OrderDaoImpl().getUserIdByName(userName);
			} catch (DaoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			User user = new User(id, userName, "", "");
			order.setUser(user);

			try {
				int n = new OrderDaoImpl().placeOrder(order);
				return new Integer(n);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				throw new PersistException(e.getMessage(), e);
			}
		}
		return null;

	}

	/**
	 * 
	 * @param product
	 * @return
	 * @throws PersistException
	 */
	@Override
	public int addProduct(Product product) throws PersistException {
		// TODO Auto-generated method stub
		int b;
		try {
			b = new OrderDaoImpl().addProduct(product);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new PersistException(e.getMessage(), e);
		}

		return b;

	}

	/**
	 * 
	 * @param date
	 * @return
	 * @throws FetchException
	 */
	@Override
	public List<Order> getOrderSummary(Date date) throws FetchException {
		// TODO Auto-generated method stub
		try {
			return new OrderDaoImpl().getOrderSummary(date);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new FetchException(e.getMessage());
		}
	}

	public boolean backup(String msg) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Gaurav");
		String routePath = this.getClass().getClassLoader()
				.getResource(File.separator).getPath();
		System.out.println(routePath);
		Date date = null;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse("13-05-2015");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String dateStr = new SimpleDateFormat().format(date);
		String newLine = System.getProperty("line.separator");
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				routePath + File.separator + ".." + File.separator
						+ "backup.txt"), true));
		bw.write(dateStr + " : " + msg);
		bw.close();
		return true;

	}

}

/**
 * 
 */
package com.mindtree.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mindtree.dao.OrderDao;
import com.mindtree.exceptions.DBUtilityException;
import com.mindtree.exceptions.DaoException;
import com.mindtree.entity.*;
import com.mindtree.util.DBUtil;

/**
 * @author M1030608
 *
 */
public class OrderDaoImpl implements OrderDao {

	/**
	 * 
	 */
	public OrderDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param product
	 * @return
	 * @throws DaoException
	 */
	@Override
	public int addProduct(Product product) throws DaoException {

		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			// TODO Auto-generated catch block
			throw new DaoException(e1.getMessage(), e1);
		}
		String sql = "insert into products values(?,?,?,?,?)";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, product.getId());
			ps.setString(2, product.getName());
			ps.setString(3, product.getCategory());
			ps.setDouble(4, product.getPrice());
			ps.setString(5, product.getDetails());
			return ps.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {

			try {
				DBUtil.releaseResource(ps);
				DBUtil.releaseResource(con);
			} catch (DBUtilityException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e.getMessage());
			}
		}

	}

	/**
	 * 
	 * @param order
	 * @return
	 * @throws DaoException
	 */
	@Override
	public int placeOrder(Order order) throws DaoException {

		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			// TODO Auto-generated catch block
			throw new DaoException(e1.getMessage(), e1);
		}
		String sql = "insert into orders values(null,?,?,?,?)";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, order.getProduct().getId());
			ps.setInt(2, order.getUser().getId());
			ps.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
			ps.setDouble(4, order.getTotalPrice());
			return ps.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {

			try {
				DBUtil.releaseResource(ps);
				DBUtil.releaseResource(con);
			} catch (DBUtilityException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e.getMessage());
			}
		}

	}

	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	@Override
	public List<Product> getAllProducts() throws DaoException {
		String sql = "select * from products  ";
		List<Product> al = new ArrayList<Product>();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			// TODO Auto-generated catch block
			throw new DaoException(e1.getMessage(), e1);
		}
		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {

				Product product = new Product();
				product.setId(rs.getString(1));
				product.setName(rs.getString(2));
				product.setCategory(rs.getString(3));
				product.setPrice(rs.getDouble(4));
				product.setDetails(rs.getString(5));
				al.add(product);
			}

			return al;
		} catch (SQLException e) {
			throw new DaoException("Unable to retrieve data");
		} finally {

			try {
				DBUtil.releaseResource(st);
				DBUtil.releaseResource(con);
			} catch (DBUtilityException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @param user
	 * @return
	 * @throws DaoException
	 */
	@Override
	public boolean validateLogin(User user) throws DaoException {
		// TODO Auto-generated method stub
		String role = user.getRole();
		String sql = null;
		if ("admin".equalsIgnoreCase(user.getRole())) {
			sql = "select count(*) from users  where username= '"
					+ user.getUserName() + "' and password = '"
					+ user.getPassword() + "' and role = 'admin'";

		} else if ("customer".equalsIgnoreCase(user.getRole())) {
			sql = "select count(*) from users  where username= '"
					+ user.getUserName() + "' and password = '"
					+ user.getPassword() + "' and role = 'customer'";

		}

		Connection con = null;
		System.out.println(sql);

		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			// TODO Auto-generated catch block
			throw new DaoException(e1.getMessage(), e1);
		}
		Statement st = null;
		try {
			st = con.createStatement();

			ResultSet rs = st.executeQuery(sql);
			int count = 0;
			if (rs.next()) {

				count = rs.getInt(1);
			}
			System.out.println("In JDBCDao " + count);
			if (count > 0)
				return true;
		} catch (SQLException e) {
			throw new DaoException("Unable to retrieve data");
		} finally {

			try {
				DBUtil.releaseResource(st);
				DBUtil.releaseResource(con);
			} catch (DBUtilityException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e.getMessage());
			}
		}
		return false;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Product getProductById(String id) throws DaoException {
		String sql = "select * from products where id= '" + id + "'";

		Connection con = null;
		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			// TODO Auto-generated catch block
			throw new DaoException(e1.getMessage(), e1);
		}
		Statement st = null;
		Product product = new Product();
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {

				product.setId(rs.getString(1));
				product.setName(rs.getString(2));
				product.setCategory(rs.getString(3));
				product.setPrice(rs.getDouble(4));
				product.setDetails(rs.getString(5));

			}
			return product;
		} catch (SQLException e) {
			throw new DaoException("Unable to retrieve data");
		} finally {

			try {
				DBUtil.releaseResource(st);
				DBUtil.releaseResource(con);
			} catch (DBUtilityException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @param userName
	 * @return
	 * @throws DaoException
	 */
	public int getUserIdByName(String userName) throws DaoException {
		// TODO Auto-generated method stub
		String sql = "select id from users where username= '" + userName + "'";

		Connection con = null;
		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			// TODO Auto-generated catch block
			throw new DaoException(e1.getMessage(), e1);
		}
		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			int id = 0;
			while (rs.next()) {

				id = rs.getInt(1);

			}
			return id;
		} catch (SQLException e) {
			throw new DaoException("Unable to retrieve data");
		} finally {

			try {
				DBUtil.releaseResource(st);
				DBUtil.releaseResource(con);
			} catch (DBUtilityException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e.getMessage());
			}
		}

	}

	/**
	 * This method fetches role from users table based on userid that it
	 * receives as its argument.
	 */
	public String getRoleByUserId(String userid) throws DaoException {
		String sql = "select role from users where username= '" + userid + "'";
		String role = null;
		Connection con = null;
		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			// TODO Auto-generated catch block
			throw new DaoException(e1.getMessage(), e1);
		}
		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				role = rs.getString(1);

			}
			return role;
		} catch (SQLException e) {
			throw new DaoException("Unable to retrieve data");
		} finally {

			try {
				DBUtil.releaseResource(st);
				DBUtil.releaseResource(con);
			} catch (DBUtilityException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @param date
	 * @return
	 * @throws DaoException
	 */
	@Override
	public List<Order> getOrderSummary(Date date) throws DaoException {
		String sql = "select id, ( select name from products where id= orders.product_id) as product_name, (select username from users where id = orders.user_id) as user, total_amount from orders where order_date=?";
		List<Order> al = new ArrayList<Order>();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			// TODO Auto-generated catch block
			throw new DaoException(e1.getMessage(), e1);
		}
		PreparedStatement st = null;
		System.out.println(sql);
		try {
			st = con.prepareStatement(sql);
			st.setDate(1, new java.sql.Date(date.getTime()));
			ResultSet rs = st.executeQuery();
			while (rs.next()) {

				Order order = new Order();
				order.setId(rs.getInt(1));
				Product product = new Product();
				product.setName(rs.getString(2));
				order.setProduct(product);
				User user = new User();
				user.setUserName(rs.getString(3));
				order.setUser(user);
				order.setOrderDate(date);
				order.setTotalPrice(rs.getDouble(4));
				al.add(order);
			}
			System.out.println("In JDBCDao " + al.size());
			return al;
		} catch (SQLException e) {
			throw new DaoException("Unable to retrieve data");
		} finally {

			try {
				DBUtil.releaseResource(st);
				DBUtil.releaseResource(con);
			} catch (DBUtilityException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e.getMessage());
			}
		}
	}
}

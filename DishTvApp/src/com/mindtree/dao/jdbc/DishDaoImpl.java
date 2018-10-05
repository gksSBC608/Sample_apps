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
import java.util.List;

import com.mindtree.dao.DishDao;
import com.mindtree.entity.Channel;
import com.mindtree.entity.Customer;
import com.mindtree.entity.Subscription;
import com.mindtree.entity.User;
import com.mindtree.exceptions.DBUtilityException;
import com.mindtree.exceptions.DaoException;

/**
 * @author M1030608
 *
 */
public class DishDaoImpl implements DishDao {

	/**
	 * 
	 */
	public DishDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mindtree.dao.DishDao#getAllChannels()
	 */
	@Override
	public List<Channel> getAllChannels() throws DaoException {
		String sql = "select * from channels order by channel_id ";
		List<Channel> al = new ArrayList<Channel>();
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

				Channel channel = new Channel(rs.getInt(1), rs.getString(2),
						rs.getDouble(3));
				al.add(channel);
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
				
				throw new DaoException(e.getMessage());
			}
		}
	}

	@Override
	public boolean subscribe(Subscription s) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			// TODO Auto-generated catch block
			throw new DaoException(e1.getMessage(), e1);
		}
		String sql = "insert into subscriptions values(null,?,?,?)";

		try {
			ps = con.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(s.getDate().getTime()));
			ps.setInt(2, s.getChannel().getChanelId());
			ps.setLong(3, s.getCustomer().getSubscriberId());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}

	}

	@Override
	public List<Subscription> getSubscriptionInfo(long subscriberId)
			throws DaoException {
		// TODO Auto-generated method stub

		String sql = "select c.first_name,c.last_name, ch.channel_name, ch.cost_per_month, ";
		sql += " s.channel_id from subscriptions s, customers c, channels ch ";
		sql += " where s.subscriber_id = c.subscriber_id and ";
		sql += "s.channel_id = ch.channel_id and s.subscriber_id = "
				+ subscriberId;
		List<Subscription> al = new ArrayList<Subscription>();
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
				String firstName = rs.getString(1);
				String lastName = rs.getString(2);
				String channelName = rs.getString(3);
				double costPerMonth = rs.getDouble(4);
				int chanelId = rs.getInt(5);
				Subscription s = new Subscription();
				s.setCustomer(new Customer(subscriberId, null, firstName,
						lastName));
				s.setChannel(new Channel(chanelId, channelName, costPerMonth));
				al.add(s);
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

	public boolean validateUser(String userName) throws DaoException {
		// TODO Auto-generated method stub
		String user = userName + "%";
		String sql = "select count(username) from login  where username like '"
				+ user + "'";

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

	public boolean validateLogin(User user) throws DaoException {
		// TODO Auto-generated method stub
		String sql = "select count(*) from login  where username= '"
				+ user.getUsername() + "' and password = '"+user.getPassword()+"'";

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

}

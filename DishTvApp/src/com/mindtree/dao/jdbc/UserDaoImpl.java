/**
 * 
 */
package com.mindtree.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mindtree.dao.UserDao;
import com.mindtree.entity.User;
import com.mindtree.exceptions.DBUtilityException;
import com.mindtree.exceptions.DaoException;

/**
 * @author M1030608
 *
 */
public class UserDaoImpl implements UserDao {
	private Connection connection; 

	/**
	 * 
	 */
	public UserDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean getLogin(User user) throws DaoException {
		try{
			connection = DBUtil.getConnection();
			}
		catch(DBUtilityException db){
			System.out.println("Error!!");
		}
		try {
			PreparedStatement ps = connection.prepareStatement("select * from login where username=? and password=?");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}

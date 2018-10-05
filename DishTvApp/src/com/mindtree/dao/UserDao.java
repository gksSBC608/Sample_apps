/**
 * 
 */
package com.mindtree.dao;

import com.mindtree.entity.User;
import com.mindtree.exceptions.DBUtilityException;
import com.mindtree.exceptions.DaoException;

/**
 * @author M1030608
 *
 */
public interface UserDao {

	public boolean getLogin(User user) throws DaoException, DBUtilityException;
}

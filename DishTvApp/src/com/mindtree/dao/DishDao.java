/**
 * 
 */
package com.mindtree.dao;

import com.mindtree.entity.*;
import com.mindtree.exceptions.DaoException;

import java.util.*;

/**
 * @author M1030608
 *
 */
public interface DishDao {

	List<Channel> getAllChannels() throws DaoException;

	boolean subscribe(Subscription s) throws DaoException;

	List<Subscription> getSubscriptionInfo(long subscriptionid)
			throws DaoException;

	boolean validateUser(String userName) throws DaoException;

}

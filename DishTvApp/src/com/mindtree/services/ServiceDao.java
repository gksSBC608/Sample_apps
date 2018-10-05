package com.mindtree.services;

import java.util.List;

import com.mindtree.entity.Channel;
import com.mindtree.entity.Subscription;
import com.mindtree.entity.SubscriptionDto;
import com.mindtree.entity.User;
import com.mindtree.exceptions.FetchException;
import com.mindtree.exceptions.PersistException;

public interface ServiceDao {

	List<Channel> getAllChannels() throws FetchException;

	SubscriptionDto getSubscriptionInfo(Long subscriberId)
			throws FetchException;

	boolean subscribe(Subscription s) throws PersistException;

	boolean validateUser(String user) throws FetchException;

}

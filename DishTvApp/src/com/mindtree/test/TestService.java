package com.mindtree.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mindtree.entity.Channel;
import com.mindtree.entity.Customer;
import com.mindtree.entity.Subscription;
import com.mindtree.exceptions.DaoException;
import com.mindtree.exceptions.FetchException;
import com.mindtree.exceptions.PersistException;
import com.mindtree.services.Service;

public class TestService {

	static Subscription s = null;

	@BeforeClass
	public static void setUp() throws ParseException {
		s = new Subscription();
		Channel channel = null;
		//s.setChannel(new Channel(105, null, 0));
		s.setChannel(channel);
		//s.setCustomer(new Customer(1100123458, "", "", ""));
		s.setCustomer(null);
		s.setDate(new SimpleDateFormat("dd-MM-yyyy").parse("04-04-2015"));
	}

	@Test
	public void testGetAllChannels() throws FetchException {
		assertTrue(new Service().getAllChannels().size() > 0);
	}

	/*@Test
	public void testSubscribe() throws PersistException {
		
		assertTrue(new Service().subscribe(s));
		
	}*/

	@Test(expected =PersistException.class)
	public void testSubscribe() throws PersistException {
		
		//try {
			new Service().subscribe(s);
		//} catch (Exception e) {
			
			/*if (e instanceof PersistException)
			{
				e.printStackTrace();
				System.out.println("Exception thrown");
			}
			else
			{
				fail ("test casse failed");
			}*/
		}

	
	
	@Test
	public void testGetSubscriptionInfo() throws FetchException {
		//new Service().getSubscriptionInfo(1100123454l);
		assertNotNull(new Service().getSubscriptionInfo(1100123458L));
	}
	
	@Test(expected = FetchException.class)
	public void  testNegativeFlowSubcciptionInfo() throws FetchException{
		new Service().getSubscriptionInfo(1100123454l);
	}

}

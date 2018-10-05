package com.mindtree.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.mindtree.entity.SubscriptionDto;
import com.mindtree.exceptions.FetchException;
import com.mindtree.services.Service;

public class MockService {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetSubscriptionInfo() throws FetchException {
		
		Service s = Mockito.mock(Service.class);
		SubscriptionDto sD = Mockito.mock(SubscriptionDto.class);
		s.getSubscriptionInfo(1100123456L);
		Mockito.when(s.getSubscriptionInfo(1100123456L)).thenReturn(sD);
		assertNotNull(s.getSubscriptionInfo(1100123456L));
	}

}

package com.mindtree.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mindtree.exceptions.FetchException;
import com.mindtree.services.Service;

public class TestBackup {

	@Test
	public void testGetSubscriptionInfo() throws FetchException {
		assertNotNull(new Service().getSubscriptionInfo(1100123457L));
	}

}

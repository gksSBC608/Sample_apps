package com.mindtree.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mindtree.exceptions.FetchException;
import com.mindtree.exceptions.FileRelatedException;
import com.mindtree.service.Service;

public class TestBackup {

	/*
	 * This method tests the generateReport(0 method of the Service class. If
	 * exception is not thrown then this test succeeds.
	 */
	@Test
	public void test() throws FetchException, FileRelatedException {
		new Service().generateReport("M1001 ");
	}

}

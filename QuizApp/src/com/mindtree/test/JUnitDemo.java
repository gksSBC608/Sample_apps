package com.mindtree.test;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.mindtree.controller.HomeController;

/*
 * Test cases for mocling HomeController class
 */
public class JUnitDemo {

	static HttpServletRequest request;
	static HttpServletResponse response;
	static HomeController hc;

	/*
	 * Method invoked after the class is loaded. It defines the required mocked
	 * objects.
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		request = Mockito.mock(HttpServletRequest.class);
		response = Mockito.mock(HttpServletResponse.class);
		hc = Mockito.mock(HomeController.class);
		hc.doGet(request, response);
	}

	/*
	 * Test method to test the working of doGet() method.
	 */
	@Test
	public void testDoGetHttpServletRequestHttpServletResponse()
			throws ServletException, IOException {

		Mockito.verify(hc).doGet(request, response);

	}

	/*
	 * Test case for doPost()
	 */
	@Test
	public void testDoPostHttpServletRequestHttpServletResponse()
			throws ServletException, IOException {
		Mockito.doNothing().when(hc).doPost(request, response);
	}

}

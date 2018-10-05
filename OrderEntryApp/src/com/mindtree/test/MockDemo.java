package com.mindtree.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.mindtree.controller.HomeController;

/**
 * @author M1030608
 *
 */
public class MockDemo {

	private static HomeController controller;
	private static HttpServletRequest request;
	private static HttpServletResponse response;
	private static RequestDispatcher requestDispatcher;
	private static HttpServletRequest request1;
	private static HttpServletResponse response1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		request = Mockito.mock(HttpServletRequest.class);
		response = Mockito.mock(HttpServletResponse.class);
		requestDispatcher = mock(RequestDispatcher.class);
		controller = new HomeController();

		Mockito.when(request.getMethod()).thenReturn("GET");
		Mockito.when(request.getRequestURI()).thenReturn(
				"/OrderEntryApp/orderHome");
		Mockito.when(request.getRequestDispatcher("/home.jsp"))
				.thenReturn(requestDispatcher);
		
		request1 = Mockito.mock(HttpServletRequest.class);
		response1 = Mockito.mock(HttpServletResponse.class);
	}
	
	

	/**
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testServiceHttpServletRequestHttpServletResponse()
			throws ServletException, IOException {
		controller.service(request, response);
		Mockito.verify(requestDispatcher, Mockito.times(1)).forward(request,
				response);
		
	}

	@Test
	public void testDoGetHttpServletRequestHttpServletResponse() {
		Mockito.when(request1.getRequestURI())
		.thenReturn("/OrderEntryApp/logout");
		
	}

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() {
		
	}

}

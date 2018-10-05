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

public class MockControllerDemo {

	static HttpServletRequest request;
	static HttpServletResponse response;
	static HomeController hc;
	private static RequestDispatcher requestDispatcher;

	/*
	 * Method invoked after the class is loaded. It defines the required mocked
	 * objects.
	 */

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		request = Mockito.mock(HttpServletRequest.class);
		response = Mockito.mock(HttpServletResponse.class);
		hc = new HomeController();
		requestDispatcher = mock(RequestDispatcher.class);
		Mockito.when(request.getMethod()).thenReturn("GET");
		Mockito.when(request.getRequestURI()).thenReturn(
				"/M1030608/addQuestion");
		Mockito.when(request.getRequestDispatcher("/createQuiz.jsp"))
				.thenReturn(requestDispatcher);

	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testServiceHttpServletRequestHttpServletResponse() {
		try {
			hc.service(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Mockito.verify(requestDispatcher, Mockito.times(1)).forward(
					request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDoGetHttpServletRequestHttpServletResponse()
			throws ServletException, IOException {

	}

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() {

	}

}

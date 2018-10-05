/**
 * 
 */
package com.mindtree.test.mock;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mindtree.controller.HomeController;

import static org.mockito.Mockito.*;

/**
 * @author M1030608
 *
 */
public class Controller {

	static HttpServletRequest request;	static HttpServletResponse response;
	static HomeController hc;
	static RequestDispatcher rd;
	static HttpServletRequest request1;
	static HttpServletResponse response1;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		request1 = mock(HttpServletRequest.class);
		hc = mock(HomeController.class);
		rd = mock(RequestDispatcher.class);
		when(request.getRequestURI()).thenReturn("/DishTvApp/subscribeChannel");
		when(request.getParameter("subId")).thenReturn("1100123456");
		when(request.getParameter("channel")).thenReturn("Star Plus");
		when(request1.getParameter("date")).thenReturn("03-23-2015");
		when(request.getRequestDispatcher("/subscriptionSuccess.jsp"))
				.thenReturn(rd);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.mindtree.controller.HomeController#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
	 * .
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoGetHttpServletRequestHttpServletResponse()
			throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HomeController hc = mock(HomeController.class);
		//HttpServlet servlet = mock(HttpServlet.class);
		hc.doGet(request, response);
		verify(hc).doGet(request, response);

	}

	/**
	 * Test method for
	 * {@link com.mindtree.controller.HomeController#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
	 * .
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testDoPostHttpServletRequestHttpServletResponse()
			throws ServletException, IOException {

		 doNothing().when(hc).doPost(request, response);
		//doNothing().when(rd).forward(request, response);

	}

	@Test(expected = ParseException.class)
	public void testNegativeDoPostHttpServletRequestHttpServletResponse()
			throws ParseException {

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HomeController hc = mock(HomeController.class);
		SimpleDateFormat sdf = mock(SimpleDateFormat.class);
		//when(sdf).thenReturn(new SimpleDateFormat("dd-MM-yyyy"));
		// sdf.parse(request1.getParameter("date"));
		when(sdf.parse(request1.getParameter("date"))).thenThrow(
				new ParseException("", 0));

	}

}

package com.mindtree.test;

import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.mindtree.controller.HomeController;

/**
 * 
 * @author M1030608
 * 
 *         This class contains test cases to test the HomeController class using
 *         Mockito. It tests login, logout, invalid access without acquiring a
 *         session, invalid login, Generation of transaction report i.e. used by
 *         AJAX.
 *
 */
public class HomeControllerMockito {

	private static HomeController controller;
	private static HttpServletRequest request;
	private static HttpServletResponse response;
	private static RequestDispatcher rd;
	private static HttpSession session;
	private static PrintWriter writer;

	private static ServletContext context;

	private static RequestDispatcher rd1;
	private static RequestDispatcher rd2;
	private static RequestDispatcher rd3;
	private static RequestDispatcher rd4;

	/**
	 * @throws Exception
	 * 
	 *             This method executes before loading of the class, and it
	 *             initializes the data required by all the test case.
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		request = Mockito.mock(HttpServletRequest.class);
		response = Mockito.mock(HttpServletResponse.class);
		rd = mock(RequestDispatcher.class);
		rd1 = mock(RequestDispatcher.class);
		rd2 = mock(RequestDispatcher.class);
		rd3 = mock(RequestDispatcher.class);
		rd4 = mock(RequestDispatcher.class);
		session = Mockito.mock(HttpSession.class);
		context = Mockito.mock(ServletContext.class);
		controller = new HomeController();
		writer = mock(PrintWriter.class);

		Mockito.when(request.getSession(Mockito.anyBoolean())).thenReturn(
				session);
		Mockito.when(request.getServletContext()).thenReturn(context);
	}

	/**
	 * 
	 * @throws ServletException
	 * @throws IOException
	 *             This test case tests the invalid access to the servlet, i.e.
	 *             if a user accesses it without logging in, then he should be
	 *             redirected to the login page.
	 */
	@Test
	public void testServiceNegative() throws ServletException, IOException {
		Mockito.when(request.getRequestURI()).thenReturn("/BankApp");
		Mockito.when(session.getAttribute("user")).thenReturn(null);
		Mockito.when(request.getRequestDispatcher("login.jsp")).thenReturn(rd);

		controller.service(request, response);
		Mockito.verify(rd).forward(request, response);
	}

	/**
	 * 
	 * @throws ServletException
	 * @throws IOException
	 *             This method tests login by a valid user. A valid user is
	 *             redirected to home.jsp page.
	 */
	@Test
	public void testLoginSuccess() throws ServletException, IOException {
		Mockito.when(request.getRequestURI()).thenReturn("/BankApp/login");
		Mockito.when(session.getAttribute("user")).thenReturn("G");
		Mockito.when(request.getMethod()).thenReturn("POST");
		Mockito.when(request.getParameter("userid")).thenReturn("gaurav");
		Mockito.when(request.getParameter("password")).thenReturn("gaurav");
		Mockito.when(request.getRequestDispatcher("home.jsp")).thenReturn(rd1);
		controller.service(request, response);

		Mockito.verify(rd1).forward(request, response);
	}

	/**
	 * 
	 * @throws ServletException
	 * @throws IOException
	 *             This method tests login failure of a user entering wrong
	 *             credentials. Such users are redirected to login.jsp
	 */
	@Test
	public void testLoginFailure() throws ServletException, IOException {
		Mockito.when(request.getRequestURI()).thenReturn("/BankApp/login");
		Mockito.when(session.getAttribute("user")).thenReturn("G");
		Mockito.when(request.getMethod()).thenReturn("POST");
		Mockito.when(request.getParameter("userid")).thenReturn("gaurav");
		Mockito.when(request.getParameter("password")).thenReturn("gaura");
		Mockito.when(request.getRequestDispatcher("login.jsp")).thenReturn(rd2);
		controller.service(request, response);

		Mockito.verify(rd2).forward(request, response);
	}

	/**
	 * 
	 * @throws ServletException
	 * @throws IOException
	 *             This method tests the action when a user selects logout link
	 *             from any page in the applicaton.
	 */
	@Test
	public void testLogout() throws ServletException, IOException {
		Mockito.when(request.getRequestURI()).thenReturn("/BankApp/logout");
		Mockito.when(session.getAttribute("user")).thenReturn("G");
		Mockito.when(request.getMethod()).thenReturn("GET");

		Mockito.doNothing().when(session).invalidate();
		Mockito.when(request.getRequestDispatcher("login.jsp")).thenReturn(rd3);
		controller.service(request, response);

		Mockito.verify(rd3).forward(request, response);
	}

	/**
	 * 
	 * @throws ServletException
	 * @throws IOException
	 *             This method tests the action when a user selects link to go
	 *             to home from any page in the application.
	 */
	@Test
	public void testGoHome() throws ServletException, IOException {
		Mockito.when(request.getRequestURI()).thenReturn("/BankApp/bankHome");

		Mockito.when(request.getMethod()).thenReturn("GET");
		Mockito.when(request.getRequestDispatcher("/home.jsp")).thenReturn(rd4);
		controller.service(request, response);
		Mockito.verify(rd4).forward(request, response);
	}

	/**
	 * @throws ServletException
	 * @throws IOException
	 *             This test case tests the method invoked by Ajax for fetching
	 *             report on transactions occured on a particular date.
	 */
	@Test
	public void testGetReportAjax() throws ServletException, IOException {
		Mockito.when(request.getRequestURI()).thenReturn("/BankApp/viewDetail");

		Mockito.when(request.getMethod()).thenReturn("GET");
		Mockito.when(request.getParameter("id")).thenReturn("15/05/2015");
		Mockito.when(response.getWriter()).thenReturn(writer);

		controller.service(request, response);

		Mockito.verify(writer).print(Mockito.anyString());

	}

}

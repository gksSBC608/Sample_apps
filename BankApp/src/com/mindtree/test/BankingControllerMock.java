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

import com.mindtree.controller.BankingController;

/**
 * 
 * @author M1030608
 *
 *         This class defines the test cases to test various methods of the
 *         BankingController servlet.
 */
public class BankingControllerMock {

	private static BankingController controller;
	private static HttpServletRequest request;
	private static HttpServletResponse response;
	private static RequestDispatcher rd;
	private static RequestDispatcher rd1;
	private static HttpSession session;
	private static PrintWriter writer;

	private static ServletContext context;

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

		session = Mockito.mock(HttpSession.class);
		context = Mockito.mock(ServletContext.class);
		controller = new BankingController();
		writer = mock(PrintWriter.class);

		Mockito.when(request.getSession(Mockito.anyBoolean())).thenReturn(
				session);
		Mockito.when(request.getServletContext()).thenReturn(context);
	}

	/**
	 * 
	 * @throws IOException
	 * @throws ServletException
	 *             This method tests the server side feature used by Ajax to
	 *             fetch account number of beneficiary.
	 */
	@Test
	public void testGetAccountNumber() throws IOException, ServletException {
		Mockito.when(request.getRequestURI()).thenReturn(
				"/BankApp/getAccountNumber");

		Mockito.when(request.getMethod()).thenReturn("GET");
		Mockito.when(request.getParameter("name")).thenReturn("Gaurav Sharan");
		Mockito.when(response.getWriter()).thenReturn(writer);

		Mockito.when(session.getAttribute("user")).thenReturn("G");

		controller.doGet(request, response);

		Mockito.verify(writer).print(Mockito.anyObject());
	}

	/**
	 * 
	 * @throws ServletException
	 * @throws IOException
	 *             This test case tests the method that receives request from
	 *             home page to display page accepting transaction details. The
	 *             method being tested various lists like list of beneficiaries,
	 *             list of all accounts and passes the objects to
	 *             transferPage.jsp for dispaly.
	 */
	@Test
	public void testDisplayTransferPage() throws ServletException, IOException {
		Mockito.when(request.getRequestURI()).thenReturn(
				"/BankApp/transferFund");

		Mockito.when(request.getMethod()).thenReturn("GET");
		Mockito.when(session.getAttribute("user")).thenReturn("G");
		Mockito.when(context.getAttribute("username")).thenReturn("gaurav");
		Mockito.when(request.getRequestDispatcher("/transferPage.jsp"))
				.thenReturn(rd);

		controller.service(request, response);

		Mockito.verify(rd).forward(request, response);
	}

	/**
	 * 
	 * @throws ServletException
	 * @throws IOException
	 *             This test case tests doPost() method, it accepts request to
	 *             transfer the amount with required details and then forwards
	 *             the request to JSP page to show success message.
	 */
	@Test
	public void testDoPost() throws ServletException, IOException {
		Mockito.when(request.getRequestURI()).thenReturn(
				"/BankApp/processTransfer");

		Mockito.when(request.getMethod()).thenReturn("POST");
		Mockito.when(session.getAttribute("user")).thenReturn("Gaurav Sharan");

		Mockito.when(request.getParameter("fromAc")).thenReturn("123456789120");

		Mockito.when(request.getParameter("amount")).thenReturn("500");
		Mockito.when(request.getParameter("detail")).thenReturn("Fun");
		Mockito.when(context.getAttribute("dest")).thenReturn(123456789123L);

		Mockito.when(request.getRequestDispatcher("/transferSuccess.jsp"))
				.thenReturn(rd1);

		controller.service(request, response);

		Mockito.verify(rd1).forward(request, response);

	}

}

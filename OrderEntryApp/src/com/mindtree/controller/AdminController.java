package com.mindtree.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mindtree.entity.*;
import com.mindtree.exceptions.FetchException;
import com.mindtree.exceptions.PersistException;
import com.mindtree.service.Service;

/**
 * Servlet implementation class AdminController
 */
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This method is invoked by the servlet container. On basis of the type of
	 * HTTP request, it forwards the incoming request to doGet and doPost
	 * methods.
	 * 
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (request.getRequestURI().equals("/OrderEntryApp")
				&& session.getAttribute("user") == null) {
			request.setAttribute("errormsg", "Invalid Access");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		} else {
			if ("GET".equals(request.getMethod())) {
				doGet(request, response);
			} else if ("POST".equals(request.getMethod())) {
				doPost(request, response);
			}
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) This method is invokes by service() method if HTTP request
	 *      method is "GET"
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) This method is invoked by service() method if the request
	 *      method is "POST". It parses the incoming URI and forwards the
	 *      request to appropriate method.
	 * 
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getRequestURI().equals("/OrderEntryApp/addProductDetail")) {

			addProduct(request, response);
		} else if (request.getRequestURI().equals(
				"/OrderEntryApp/generateSummary")) {

			generateOrderSummary(request, response);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 *             This method is invoked from inside doPost() method if
	 *             incoming URI is to generate order summary. It receives date
	 *             through incoming request and interacts with the database to
	 *             fetch order details. After fetching the details, it passes
	 *             the list of orders related to that date to JSP page to show
	 *             details of orders.
	 */
	private void generateOrderSummary(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String strDate = request.getParameter("date");
		List<Order> orderList = new ArrayList<Order>();
		java.util.Date date = null;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			new Service().backup(e.getMessage());
			request.setAttribute("message",
					"Date should be of format dd-MM-yyyy");
			request.getRequestDispatcher("/errorPage.jsp").forward(request,
					response);
			return;
		}

		try {
			System.out.println("Going to Service layer...");
			orderList = new Service().getOrderSummary(date);
			System.out.println("Returned from servic elayer...");
		} catch (FetchException e) {
			// TODO Auto-generated catch block
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/errorPage.jsp").forward(request,
					response);
			return;
		}
		Collections.sort(orderList);
		String dateStr = new SimpleDateFormat("dd-MMM-yyyy").format(date);
		System.out.println(dateStr);
		request.setAttribute("date", date);
		request.setAttribute("detail", orderList);
		request.getRequestDispatcher("/generateOrderSummary.jsp").forward(
				request, response);
		return;

	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * 
	 *             This method is invoked if admin opts to add a new product to
	 *             the database. It receives details of the object through
	 *             request object and passes the object to service layer to add
	 *             the product. Then, it passes success message response to home
	 *             page.
	 * 
	 */
	private void addProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		double price = Double.parseDouble(request.getParameter("price"));
		String details = request.getParameter("details");
		Product product = new Product(id, name, category, price, details);
		int i = 0;
		try {
			i = new Service().addProduct(product);
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			new Service().backup(e.getMessage());
			request.setAttribute("message", "Added the product");
			request.getRequestDispatcher("errorPage.jsp").forward(request,
					response);
		}
		if (i > 0) {
			request.setAttribute("success", "Added the product");
			request.getRequestDispatcher("homeAdmin.jsp").forward(request,
					response);
		} else {
			new Service().backup("Product not added");
			request.setAttribute("message", "Product not added");
			request.getRequestDispatcher("errorPage.jsp").forward(request,
					response);
		}
	}

}

package com.mindtree.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mindtree.dao.jdbc.OrderDaoImpl;
import com.mindtree.entity.Product;
import com.mindtree.entity.ProductDto;
import com.mindtree.entity.User;
import com.mindtree.exceptions.DaoException;
import com.mindtree.exceptions.FetchException;
import com.mindtree.service.Service;

/**
 * Servlet implementation class HomeController This servlet controls logging in
 * and logging out of the users.
 */

public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<ProductDto> list = new ArrayList<ProductDto>();

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
	 *      response)
	 * 
	 *      This method is invoked if the HTTP request is GET.Then, it passes
	 *      the request to appropriate method on basis of the incoming URI
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("In doGet " + request.getRequestURI());
		if (request.getRequestURI().equals("/OrderEntryApp/logout")) {
			processLogout(request, response);
		}
		if (request.getRequestURI().equals("/OrderEntryApp/orderHome")) {

			request.getRequestDispatcher("/home.jsp")
					.forward(request, response);
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * 
	 *             This method is called by doGet() method. Its purpose is to
	 *             logout existing user and then it passes success message to
	 *             login page.
	 */
	private void processLogout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if (session.getAttribute("user") != null) {
			list = (List<ProductDto>) session.getAttribute("cart");
			list = new ArrayList<ProductDto>();
			session.invalidate();
			request.setAttribute("message", "Logged out successfully");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) This method is invoked if a user chooses to login. It
	 *      invokes processLogin() method of the same class.
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		if (request.getRequestURI().equals("/OrderEntryApp/login")) {
			processLogin(request, response);
		}

	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 *             This method creates User object by using the incoming
	 *             credentials of the user and pases the object to service layer
	 *             to validate the user. If logging in user is valid then
	 *             request is forwarded to home page.
	 * 
	 */
	private void processLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Going to login..Customer.");

		String userid = request.getParameter("userid");

		String role = null;
		try {
			role = new OrderDaoImpl().getRoleByUserId(userid);
		} catch (DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (role != null) {
			User user = new User(0, request.getParameter("userid"),
				request.getParameter("password"), role);
			boolean b = false;
			try {
				b = new Service().validateLogin(user);
			} catch (FetchException e) {
				boolean backed = new Service().backup(e.getMessage());
				request.setAttribute("message", e.getMessage());
				if (b) {
					request.getRequestDispatcher("errorPage.jsp").forward(
							request, response);
					return;
				}
			}
			if (b) {
				HttpSession session = request.getSession(true);
				session.setAttribute("user", user.getUserName());
				session.setAttribute("role", role);
				session.setAttribute("cart", list);
				request.setAttribute("message", "Logged in successfully");
				request.getRequestDispatcher("home.jsp").forward(request,
						response);
			} else {
				boolean backed = new Service()
						.backup("Invalid username or password");
				request.setAttribute("errorMsg", "Invalid username or password");
				request.getRequestDispatcher("login.jsp").forward(request,
						response);
			}

		}
	}

}

package com.mindtree.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mindtree.dao.jdbc.OrderDaoImpl;
import com.mindtree.entity.Order;
import com.mindtree.entity.Product;
import com.mindtree.entity.ProductDto;
import com.mindtree.exceptions.DaoException;
import com.mindtree.exceptions.FetchException;
import com.mindtree.exceptions.PersistException;
import com.mindtree.service.Service;

/**
 * Servlet implementation class ShoppingController
 */
public class ShoppingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShoppingController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * This method is invoked by the servlet container. On basis of the type of
	 * HTTP request, it forwards the incoming request to doGet and doPost
	 * methods.
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
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		if (request.getRequestURI().equals("/OrderEntryApp/listProducts")) {
			listAllProducts(request, response);
		} else if (request.getRequestURI().equals("/OrderEntryApp/addToCart")) {
			addToCart(request, response);
		} else if (request.getRequestURI().equals("/OrderEntryApp/placeOrder")) {
			placeOrder(request, response);
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 *             This method is invoked if user chooses to checkout the
	 *             products added to cart. It fetches the items from cart and
	 *             passes them to the service layer to be stored into order
	 *             table.
	 */
	private void placeOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		Order order = new Order();
		if (session.getAttribute("user") != null) {
			List<ProductDto> list = (List<ProductDto>) session
					.getAttribute("cart");
			String userId = (String) session.getAttribute("user");
			if (list.size() > 0) {
				try {
					new Service().placeOrder(userId, list);
				} catch (PersistException e) {
					// TODO Auto-generated catch block
					new Service().backup(e.getMessage());
					request.setAttribute("message", e.getMessage());
					request.getRequestDispatcher("errorPage.jsp").forward(
							request, response);
					return;
				}
				list.clear();
				request.setAttribute("success", "Order Placed Successfully");
				request.getRequestDispatcher("home.jsp").forward(request,
						response);

			}
		}

	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * 
	 *             This method is invoked to add a selected item to cart. It
	 *             uses the cart stored in session scope to add the selected
	 *             item to the cart.
	 * 
	 */
	private void addToCart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String itemId = request.getParameter("itemId");
		int quantity = Integer.parseInt(request.getParameter("qty"));
		Product product = null;
		ProductDto productDto = new ProductDto();

		try {
			product = new OrderDaoImpl().getProductById(itemId);

		} catch (DaoException e) {
			// TODO Auto-generated catch block
			new Service().backup(e.getMessage());
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("errorPage.jsp").forward(request,
					response);
			new Service().backup(e.getMessage());
		}
		productDto.setProduct(product);
		productDto.setQuantity(quantity);
		HttpSession session = request.getSession(false);
		if (session.getAttribute("user") != null) {
			List<ProductDto> list = (List<ProductDto>) session
					.getAttribute("cart");
			list.add(productDto);
			session.setAttribute("cart", list);
			double price = 0;
			for (ProductDto p : list) {
				price += p.getProduct().getPrice() * p.getQuantity();
			}
			//request.setAttribute("totalPrice", price);
			request.setAttribute("success", "Item added to cart");
			request.getRequestDispatcher("listProducts").forward(request,
					response);

		}

	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 *             This method is invoked if user chooses to view the products
	 *             available in the inventory.It fetches all the roduts
	 *             available y=in the database and passes the list to
	 *             corresponding JSP page to display the list of available
	 *             product.
	 */
	private void listAllProducts(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Product> productList = new ArrayList<Product>();
		try {
			productList = new Service().listAllProducts();
		} catch (FetchException e) {
			// TODO Auto-generated catch block
			new Service().backup(e.getMessage());
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/errorPage.jsp").forward(request,
					response);
		}
		Collections.sort(productList);
		request.setAttribute("products", productList);
		request.getRequestDispatcher("/listProducts.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

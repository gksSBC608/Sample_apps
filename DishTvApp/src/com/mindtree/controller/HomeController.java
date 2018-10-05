package com.mindtree.controller;

import com.mindtree.services.*;
import com.mindtree.entity.*;
import com.mindtree.exceptions.FetchException;
import com.mindtree.exceptions.PersistException;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out
				.println("Entered service method of Home Controller with Request URI "
						+ request.getRequestURI());

		HttpSession session = request.getSession(false);
		if (request.getRequestURI().equals("/DishTvApp")
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
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("In doGet " + request.getRequestURI());

		if (request.getRequestURI().equals("/DishTvApp/subscribeChannel")) {
			System.out.println("Going to subscribe channel");
			processSubscription(request, response);
		}
		if (request.getRequestURI().equals("/DishTvApp/viewDetail")) {
			System.out.println("In view Detail " + request.getRequestURI());
			request.getRequestDispatcher("/viewSubscription.jsp").forward(
					request, response);
		}
		if (request.getRequestURI().equals("/DishTvApp/dthHome")) {
			System.out.println("Going to home");
			request.getRequestDispatcher("/home.jsp")
					.forward(request, response);
		}
		if (request.getRequestURI().equals("/DishTvApp/logout")) {
			System.out.println("Going to logout..");
			processLogout(request, response);
		}

	}

	private void processLogout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
			request.setAttribute("message", "Logged out successfully");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		}

	}

	private void processLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User(request.getParameter("userid"),
				request.getParameter("password"));
		boolean b = false;
		try {
			b = new Service().validateLogin(user);
		} catch (FetchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (b == true) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user.getUsername());
			request.setAttribute("message", "Logged in successfully");
			request.getRequestDispatcher("home.jsp").forward(request, response);
		} else {
			System.out.println("error");
			request.setAttribute("errorMsg", "Invalid username or password");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		}

	}

	private void processSubscription(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Channel> channels = new ArrayList<Channel>();
		try {
			channels = new Service().getAllChannels();
		} catch (FetchException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		request.setAttribute("channelList", channels);
		request.getRequestDispatcher("subscribeChannel.jsp").forward(request,
				response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In doPost " + request.getRequestURI());
		if (request.getRequestURI().equals("/DishTvApp/subscribeChannel")) {
			System.out.println("Going to subscribe channel");
			processRequest(request, response);
		} else if (request.getRequestURI().equals("/DishTvApp/viewDetail")) {
			System.out.println("In doPost, going to fetch Detail "
					+ request.getRequestURI());
			// fetchDetail(request, response);
			fetchDetailAjax(request, response);
		} else if (request.getRequestURI().equals("/DishTvApp/login")) {
			System.out.println("Going to login..");
			processLogin(request, response);
		}

	}

	private void fetchDetailAjax(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		long subscriberId = Long.parseLong(request.getParameter("id"));
		System.out.println("Subscriber ID :" + subscriberId);
		SubscriptionDto sub = null;

		try {
			sub = new Service().getSubscriptionInfo(subscriberId);
		} catch (FetchException e) {
			// TODO Auto-generated catch block
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/errorPage.jsp").forward(request,
					response);
		}

		PrintWriter writer = response.getWriter();
		String str = "<h2>Subscription Summary</h2>";
		str += "<table width=50%><tr id=formField><td><label>Subscription ID : </label></td>";
		str += "<td>" + sub.getSubscriptionId() + "</td></tr>";
		str += "<tr id=formField><td><label>Customer name : </label></td>";
		str += "<td>" + sub.getCustomerName() + "</td></tr>";
		str += "<tr id=formField><td><label>Total Subscription Cost </label></td>";
		str += "<td>" + sub.getTotalCost() + "</td></tr></table><br><br>";
		str += "<table width=\"100%\">";
		str += "<tr><th colspan=3 align=left>Channels Subscribed :</th></tr>";
		str += "<tr id=topic><td>Channel Name</td><td>Cost Per Month</td></tr>";
		for (Channel c : sub.getSubscribedChannel()) {
			str += "<tr id=content><td>" + c.getChannelName() + "</td><td>"
					+ c.getCostPerMonth() + "</td></tr>";

		}
		str += "</table>";

		writer.print(str);

	}

	private void fetchDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		long subscriberId = Long.parseLong(request.getParameter("id"));
		System.out.println("Subscriber ID :" + subscriberId);
		SubscriptionDto sub = null;
		try {
			sub = new Service().getSubscriptionInfo(subscriberId);
		} catch (FetchException e) {
			// TODO Auto-generated catch block
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/errorPage.jsp").forward(request,
					response);
		}
		request.setAttribute("detail", sub);
		request.getRequestDispatcher("/viewSubscription.jsp").forward(request,
				response);

	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println(request.getParameter("subId"));
		long subscriberId = Long.parseLong(request.getParameter("subId"));
		int chanelId = Integer.parseInt(request.getParameter("channel"));
		String strDate = request.getParameter("subDate");
		System.out.println("Strung date :" + strDate);
		Date date = null;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.printStackTrace();
			request.setAttribute("message",
					"Date should be of format dd-MM-yyyy");
			request.getRequestDispatcher("/errorPage.jsp").forward(request,
					response);
		}
		System.out.println("date :" + date);
		Channel channel = new Channel(chanelId, null, 0);
		Customer customer = new Customer(subscriberId, "", "", "");
		Subscription s = new Subscription(0, date, channel, customer);
		try {
			new Service().subscribe(s);
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/errorPage.jsp").forward(request,
					response);
		}
		request.setAttribute("success", "Subscription Successful");
		RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
		rd.forward(request, response);

	}
}

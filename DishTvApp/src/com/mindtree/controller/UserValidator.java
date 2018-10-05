package com.mindtree.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mindtree.entity.User;
import com.mindtree.exceptions.FetchException;
import com.mindtree.services.Service;

/**
 * Servlet implementation class UserValidator
 */
@WebServlet("/UserValidator")
public class UserValidator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserValidator() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Hi, I am in user validator...");
		PrintWriter write = response.getWriter();
		String userName = request.getParameter("userid");
		System.out.println("UserName is : "+userName);
		if (userName == null || userName=="")
			write.print("");
		else {
			boolean b = false;
			try {
				b = new Service().validateUser(userName);
			} catch (FetchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Boolean is :" + b);
			if (b == true) {

				System.out.println("Available");
				write.print("Available");

			} else {
				write.print("Unavailable");
			}
			write.close();
		}

	}
}

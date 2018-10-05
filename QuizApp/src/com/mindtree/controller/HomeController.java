package com.mindtree.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mindtree.entity.Quiz;
import com.mindtree.exceptions.FetchException;
import com.mindtree.exceptions.FileRelatedException;
import com.mindtree.service.Service;

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
	 * 
	 * This method is invoked by Servlet container after init() method. In turn
	 * it invokes either of doGet(0 or doPost() methods on the basis of received
	 * request URIs.
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Im entering");
		if ("GET".equals(request.getMethod())) {
			doGet(request, response);
		} else if ("POST".equals(request.getMethod())) {
			doPost(request, response);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) This method is invoked if HTTP method is GET
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getRequestURI().equals("/M1030608/addQuestion")) {
			request.setAttribute("topic", request.getParameter("name"));
			request.getRequestDispatcher("/createQuiz.jsp").forward(request,
					response);
		}
		if (request.getRequestURI().equals("/M1030608/backup")) {
           generateReport(request, response);
		}

		if (request.getRequestURI().equals("/M1030608/")
				|| request.getRequestURI().equals("/M1030608/quizHome")) {
			System.out.println("Im going to home ... "+request.getRequestURI());
			List<Quiz> quizes = null;
			try {
				quizes = quizes = new Service().getAllQuiz();
			} catch (FetchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("quizes", quizes);
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
			
		}
	}

	/*
	 * This method is invoked to generate report releted to quiz. It invokes
	 * generateReport() method of Service(0 class which saves the report into a
	 * file
	 */
	private void generateReport(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String quizId = request.getParameter("id");

		try {
			new Service().generateReport(quizId);
			request.setAttribute("message", "Data backed up successfully");
			request.getRequestDispatcher("/quizHome").forward(request, response);
		} catch (FetchException | FileRelatedException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/errorPage.jsp").forward(request,
					response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) This method is invoked if HTTP method is post. It checks
	 *      the incoming URI and forwards request to servlet page to dynamically
	 *      generate filds to pass options on the basis of input parameter
	 *      corresponding to no of options
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getRequestURI().equals("/M1030608/createOptions")) {
			createOptions(request, response);
		}
	}

	/*
	 * This method is called by doPost(). It dynamically generates the text
	 * boxes on JSP page to input options corresponding to a question.
	 */
	private void createOptions(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("topic", request.getParameter("name"));
		request.setAttribute("options", request.getParameter("optNo"));
		request.getRequestDispatcher("/createQuiz.jsp").forward(request,
				response);

	}

}

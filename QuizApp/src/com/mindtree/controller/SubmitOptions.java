package com.mindtree.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mindtree.dao.jdbc.QuizDaoImpl;
import com.mindtree.entity.Options;
import com.mindtree.entity.Question;
import com.mindtree.entity.Quiz;
import com.mindtree.exceptions.DaoException;
import com.mindtree.exceptions.PersistException;
import com.mindtree.service.Service;

/**
 * Servlet implementation class SubmitOptions
 * 
 */
@WebServlet("/SubmitOptions")
public class SubmitOptions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Enumeration<String> en = request.getParameterNames();
		int correctOption = Integer.parseInt(request.getParameter("choice"));
		String quizName = request.getParameter("quiz");
		String questionName = request.getParameter("question");
		List<String> optionsList = new ArrayList<String>();
		while (en.hasMoreElements()) {
			String s = en.nextElement();
			if (Pattern.matches("opt.", s)) {
				optionsList.add(request.getParameter(s));
			}

		}

		String questionId = new Service().generateQuestionId();
		String quizId = null;
		try {
			quizId = new QuizDaoImpl().getQuizId(quizName);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/errorPage.jsp").forward(request,
					response);
		}
		Quiz quiz = new Quiz(quizId, quizName);
		String[] optArray = new String[4];
		optArray = optionsList.toArray(optArray);
		Question question = new Question(questionId, questionName, quiz);
		Options options = new Options(0, correctOption, question, quiz,
				optArray);
		try {
			new Service().addQuestionDetail(question, options);
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/errorPage.jsp").forward(request,
					response);
		}
		request.setAttribute("message", "Question Detail saved to the database");
		request.getRequestDispatcher("/index.jsp").forward(request, response);

	}

}

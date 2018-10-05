/**
 * 
 */
package com.mindtree.dao;

import java.util.List;

import com.mindtree.entity.Options;
import com.mindtree.entity.Question;
import com.mindtree.entity.Quiz;
import com.mindtree.exceptions.DaoException;

/**
 * @author M1030608
 *
 */
public interface QuizDao {

	/*
	 * This method fetches the list of quizes from the database and returns to
	 * the callee the list fetched for displaying the list of quizes on home
	 * page of the application.
	 */
	List<Quiz> getAllQuiz() throws DaoException;

	/*
	 * This method adds questions to the database and returns the no of rows
	 * updated.
	 */
	int addQuestion(Question question) throws DaoException;

	/*
	 * This method adds options corresponding to a question to the database and
	 * returns the number of rows updated.
	 */
	int addOptions(Options option) throws DaoException;

	/*
	 * This method fetches quiz name, questions for the quiz including the
	 * options. These data are sent to the file for future reference.
	 */
	List<Options> generateReport(String id) throws DaoException;

}

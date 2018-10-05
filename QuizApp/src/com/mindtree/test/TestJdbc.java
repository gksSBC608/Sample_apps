/**
 * 
 */
package com.mindtree.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mindtree.dao.jdbc.QuizDaoImpl;
import com.mindtree.entity.Options;
import com.mindtree.entity.Question;
import com.mindtree.entity.Quiz;
import com.mindtree.exceptions.DaoException;

/**
 * @author M1030608
 *
 */
public class TestJdbc {

	static QuizDaoImpl jdbc;
	static Question question;
	static Options options;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		jdbc = new QuizDaoImpl();
		question = new Question("Q1001",
				"Java is a dynamically typed language", new Quiz("M1001", null));

		String[] optionList = { "yes", "No", null, null };

		int correctOption = 2;
		Quiz quiz = new Quiz("M1001", "Core Java");

		options = new Options(0, correctOption, question, quiz, optionList);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.mindtree.dao.jdbc.QuizDaoImpl#getAllQuiz()}.
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testGetAllQuiz() throws DaoException {
		assertTrue(jdbc.getAllQuiz().size() == 3);
	}

	

	/**
	 * Test method for
	 * {@link com.mindtree.dao.jdbc.QuizDaoImpl#addOptions(com.mindtree.entity.Options)}
	 * .
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testAddOptions() throws DaoException {
		assertEquals(1, jdbc.addOptions(options));
	}

}

package com.mindtree.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mindtree.dao.jdbc.QuizDaoImpl;
import com.mindtree.entity.Question;
import com.mindtree.entity.Quiz;
import com.mindtree.exceptions.DaoException;

public class TestAddOptions {

	Question question;

	@Before
	public void setUp() throws Exception {
		question = new Question("Q1001",
				"Java is a dynamically typed language", new Quiz("M1001", null));
	}

	/**
	 * Test method for
	 * {@link com.mindtree.dao.jdbc.QuizDaoImpl#addQuestion(com.mindtree.entity.Question)}
	 * .
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testAddQuestion() throws DaoException {
		assertEquals(1, new QuizDaoImpl().addQuestion(question));
	}

}

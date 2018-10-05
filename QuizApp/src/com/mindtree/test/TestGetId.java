package com.mindtree.test;

import com.mindtree.dao.jdbc.*;
import com.mindtree.exceptions.DaoException;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestGetId {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetMaxQuestionId() throws DaoException {
		assertNotNull(new QuizDaoImpl().getMaxQuestionId());
	}
	
	@Test
	public void testGetQuizId() throws DaoException {
		assertNotNull(new QuizDaoImpl().getQuizId("Geography of India"));
	}

}

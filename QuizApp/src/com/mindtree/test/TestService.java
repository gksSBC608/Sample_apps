package com.mindtree.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mindtree.service.Service;

public class TestService {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	/*
	 * @Test public void testGetAllQuiz() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testAddQuestionDetail() { fail("Not yet implemented");
	 * }
	 * 
	 * @Test public void testGenerateReport() { fail("Not yet implemented"); }
	 */

	@Test
	public void testGenerateQuestionId() {
		String id = new Service().generateQuestionId();
		System.out.println(id);

	}

}

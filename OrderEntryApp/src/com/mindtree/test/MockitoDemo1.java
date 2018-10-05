package com.mindtree.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.mindtree.controller.HomeController;
import com.mindtree.entity.ProductDto;

public class MockitoDemo1 {

	private static HomeController controller;
	private static HttpServletRequest request;
	private static HttpServletResponse response;
	private static RequestDispatcher requestDispatcher;
	private static HttpSession session;
	private static List<ProductDto> list;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		request = Mockito.mock(HttpServletRequest.class);
		response = Mockito.mock(HttpServletResponse.class);
		requestDispatcher = mock(RequestDispatcher.class);
		controller = new HomeController();
		session = Mockito.mock(HttpSession.class);
		list = Mockito.mock(List.class);
		
		Mockito.when(request.getSession(false)).thenReturn(session);
		Mockito.when(session.getAttribute("user")).thenReturn("customer");
		Mockito.when(request.getMethod()).thenReturn("GET");
		
		Mockito.when(request.getRequestURI()).thenReturn(
				"/OrderEntryApp/logout");
		Mockito.when(request.getRequestDispatcher("login.jsp"))
				.thenReturn(requestDispatcher);
	}

	@Test
	public void testServiceHttpServletRequestHttpServletResponse() {
		
	}

	@Test
	public void testDoGetHttpServletRequestHttpServletResponse() throws ServletException, IOException {
		controller.doGet(request, response);
		Mockito.verify(requestDispatcher, Mockito.times(1)).forward(request,
				response);
			}

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() {
		
	}

}

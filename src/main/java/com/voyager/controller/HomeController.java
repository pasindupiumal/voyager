package com.voyager.controller;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.istack.internal.logging.Logger;

public class HomeController extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(HomeController.class);
	
	public HomeController() {
		super();
	}
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOGGER.log(Level.INFO, "Control aquired by Home Controller. Re-directing control to /Routes");

		//Re-direct to the RouteController servlet
		System.out.println(request.getContextPath());
		response.sendRedirect(request.getContextPath() + "/Routes");
		
	}

}

package com.voyager.controller;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.istack.internal.logging.Logger;
import com.voyager.database.DBConnection;


public class RouteController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(RouteController.class);

    public RouteController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOGGER.log(Level.INFO, "Control aquired by Route Controller. Dispatching RouteManagement JSP");
		
		String name = "Pasindu";
		request.setAttribute("Name", name);
		final RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/NewFile.jsp");
		dispatcher.forward(request, response);
		
	}

}

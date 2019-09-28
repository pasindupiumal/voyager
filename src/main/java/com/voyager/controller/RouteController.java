package com.voyager.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.istack.internal.logging.Logger;
import com.voyager.dao.Dao;
import com.voyager.dao.DaoFactory;
import com.voyager.model.Route;


public class RouteController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(RouteController.class);
	private final DaoFactory daoFactory = new DaoFactory();

    public RouteController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOGGER.log(Level.INFO, "Control aquired by Route Controller. Dispatching RouteManagement JSP");
		
		
		final String action = request.getServletPath();
		System.out.println("Servlet path: " + action);
		
		try {
			switch (action) {
			
				case "/Routes/AddNewRoute":
					showNewRoute(request, response);
					break;
				
				case "/Routes/ViewAllRoutes":
					showAllRoutes(request, response);
					break;
			
			}
		}
		catch(SQLException e) {
			LOGGER.log(Level.SEVERE, "SQL Exception: " + e.toString());
		}

		
	}

	private final void showNewRoute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		
		final RequestDispatcher dispatcher = request.getRequestDispatcher("../jsp/AddNewRoute.jsp");
		dispatcher.forward(request, response);
	}
	
	private final void showAllRoutes(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		final RequestDispatcher dispatcher = request.getRequestDispatcher("../jsp/ViewAllRoutes.jsp");
		dispatcher.forward(request, response);
		
	}

}

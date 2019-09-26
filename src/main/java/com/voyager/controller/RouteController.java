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
import com.voyager.dao.RouteDao;
import com.voyager.dao.RouteDaoImpl;
import com.voyager.model.Route;


public class RouteController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(RouteController.class);

    public RouteController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOGGER.log(Level.INFO, "Control aquired by Route Controller. Dispatching RouteManagement JSP");
		
		try {
					
			final RouteDao routes = new RouteDaoImpl();
			List<Route> routeList = routes.findAll();
			
			request.setAttribute("routeList", routeList);
			
			final RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/NewFile.jsp");
			dispatcher.forward(request, response); 
			
			
		} catch (SQLException e) {
			
			LOGGER.log(Level.INFO, "SQL Exception: " + e.toString());
			
		} catch (ClassNotFoundException e) {
			
			LOGGER.log(Level.INFO, "ClassNotFound Exception: " + e.toString());
		} 
		
	}

}

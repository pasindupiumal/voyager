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
import com.voyager.model.RouteHalt;


public class FeeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(FeeController.class);
	private final DaoFactory daoFactory = new DaoFactory();

	
    public FeeController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOGGER.log(Level.INFO, "Control aquired by Fee Controller. Dispatching FeesManagement.");
		
		
		final String action = request.getServletPath();
		System.out.println("Servlet path: " + action);
		
		try {
			switch (action) {
			
				case "/Fees/ViewAllRoutes":
					showViewAllRoutes(request, response);
					break;
			
			}
		}
		catch(SQLException | ClassNotFoundException e) {
			LOGGER.log(Level.SEVERE, "SQL Exception: " + e.toString());
		}

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private final void showViewAllRoutes(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ClassNotFoundException{
		
		final Dao dao = daoFactory.getDataAcessObject("RouteHalt");
		final List<RouteHalt> routeHaltList = dao.findAll();
		
		final RequestDispatcher dispatcher = request.getRequestDispatcher("../jsp/FeesViewAllRoutes.jsp");
		request.setAttribute("routeList", routeHaltList);
		dispatcher.forward(request, response);
		
	}

}

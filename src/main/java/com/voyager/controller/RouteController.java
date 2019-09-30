package com.voyager.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.istack.internal.logging.Logger;
import com.voyager.dao.Dao;
import com.voyager.dao.DaoFactory;
import com.voyager.dao.RouteDaoImpl;
import com.voyager.methods.Methods;
import com.voyager.model.Route;


public class RouteController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(RouteController.class);
	private final DaoFactory daoFactory = new DaoFactory();

    public RouteController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOGGER.log(Level.INFO, "Control aquired by Route Controller. Dispatching RouteManagement.");
		
		
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
				case "/Routes/DeleteRoute":
					deleteRoute(request, response);
					break;
				case "/Routes/UpdateRoute":
					showUpdateRoute(request, response);
					break;
			
			}
		}
		catch(SQLException | ClassNotFoundException e) {
			LOGGER.log(Level.SEVERE, "SQL Exception: " + e.toString());
		}

		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOGGER.log(Level.INFO, "Control aquired by Route Controller. Dispatching RouteManagement.");
		
		
		final String action = request.getServletPath();
		System.out.println("Servlet path: " + action);
		
		try {
			switch (action) {
			
				case "/Routes/InsertRoute":
					insertRoute(request, response);
					break;
				case "/Routes/UpdateRoute":
					updateRoute(request, response);
					break;
				
				
			
			}
		}
		catch(SQLException | ClassNotFoundException e) {
			LOGGER.log(Level.SEVERE, "SQL Exception: " + e.toString());
		}

		
	}

	private final void showNewRoute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		
		final RequestDispatcher dispatcher = request.getRequestDispatcher("../jsp/AddNewRoute.jsp");
		dispatcher.forward(request, response);
	}
	
	private final void showAllRoutes(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ClassNotFoundException{
		
		final Dao dao = daoFactory.getDataAcessObject("route");
		final List<Route> routeList = dao.findAll();
		
		final RequestDispatcher dispatcher = request.getRequestDispatcher("../jsp/ViewAllRoutes.jsp");
		request.setAttribute("routeList", routeList);
		dispatcher.forward(request, response);
		
	}

	private final void insertRoute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ClassNotFoundException{
		
		System.out.println("Insert route is triggered");
		final String routeName = request.getParameter("routeName");
		final String routeNumber = request.getParameter("routeNumber");
		final String origin = request.getParameter("origin");
		final String destination = request.getParameter("destination");
		final String routeOffice = request.getParameter("routeOffice");
		final String totalDistance = request.getParameter("totalDistance");

		final RequestDispatcher view = request.getRequestDispatcher("../jsp/AddNewRoute.jsp");
		
		request.setAttribute("routeName", routeName);
		request.setAttribute("routeNumber", routeNumber);
		request.setAttribute("origin",  origin);
		request.setAttribute("destination",  destination);
		request.setAttribute("routeOffice", routeOffice);
		request.setAttribute("totalDistance", totalDistance);
		final RouteDaoImpl dao = (RouteDaoImpl) daoFactory.getDataAcessObject("route");
		
		if(!Methods.isFloat(totalDistance)) {
			request.setAttribute("error1", "<p style=\"color:red;\" > Please enter a valid number for total distance</p>");
			view.forward(request, response);
		}
		else if(dao.isAdded(routeName, routeNumber)) {
			request.setAttribute("error2", "<p style=\"color:red;\" > Route already exists</p>");
			view.forward(request, response);
		}
		else {
			final float totalDistanceFloat = Float.parseFloat(totalDistance);
			final Route newRoute = new Route(routeName, routeNumber, origin, destination, routeOffice, totalDistanceFloat);
			dao.create(newRoute);
			response.sendRedirect("/voyager/Routes/ViewAllRoutes");
		
		}
	
	}
	
	private final void deleteRoute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ClassNotFoundException{
		
		final RouteDaoImpl dao = (RouteDaoImpl) daoFactory.getDataAcessObject("route");
		final int routeID = Integer.parseInt(request.getParameter("id"));
		final Route deleteRoute = new Route(routeID);
		dao.delete(deleteRoute);
		
		response.sendRedirect("/voyager/Routes/ViewAllRoutes");
		
	}
	
	private final void showUpdateRoute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ClassNotFoundException{
		
		final RouteDaoImpl dao = (RouteDaoImpl) daoFactory.getDataAcessObject("route");
		final int routeID = Integer.parseInt(request.getParameter("id"));
		final Optional<Route> updateRoute = dao.find(routeID);
		final Route routeToUpdate = updateRoute.get();
		
		final RequestDispatcher updateView = request.getRequestDispatcher("../jsp/UpdateRoute.jsp");
		
		request.setAttribute("routeID", routeToUpdate.getRouteID());
		request.setAttribute("routeName", routeToUpdate.getRouteName());
		request.setAttribute("routeNumber", routeToUpdate.getRouteNumber());
		request.setAttribute("origin",  routeToUpdate.getOrigin());
		request.setAttribute("destination",  routeToUpdate.getDestination());
		request.setAttribute("routeOffice", routeToUpdate.getRouteOffice());
		request.setAttribute("totalDistance", routeToUpdate.getTotalDistance());
		
		
		updateView.forward(request, response);
	}
	
	private final void updateRoute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ClassNotFoundException{
		
		System.out.println("Update route is triggered");
		final int routeID = Integer.parseInt(request.getParameter("routeID"));
		final String routeName = request.getParameter("routeName");
		final String routeNumber = request.getParameter("routeNumber");
		final String origin = request.getParameter("origin");
		final String destination = request.getParameter("destination");
		final String routeOffice = request.getParameter("routeOffice");
		final String totalDistance = request.getParameter("totalDistance");

		final RequestDispatcher view = request.getRequestDispatcher("../jsp/UpdateRoute.jsp");
		
		request.setAttribute("routeID", routeID);
		request.setAttribute("routeName", routeName);
		request.setAttribute("routeNumber", routeNumber);
		request.setAttribute("origin",  origin);
		request.setAttribute("destination",  destination);
		request.setAttribute("routeOffice", routeOffice);
		request.setAttribute("totalDistance", totalDistance);
		
		
		final RouteDaoImpl dao = (RouteDaoImpl) daoFactory.getDataAcessObject("route");
		
		if(!Methods.isFloat(totalDistance)) {
			request.setAttribute("error1", "<p style=\"color:red;\" > Please enter a valid number for total distance</p>");
			view.forward(request, response);
		}
		else {
			final float totalDistanceFloat = Float.parseFloat(totalDistance);
			final Route newRoute = new Route(routeID, routeName, routeNumber, origin, destination, routeOffice, totalDistanceFloat);
			dao.update(newRoute);
			response.sendRedirect("/voyager/Routes/ViewAllRoutes");
		
		}
	
	}
}

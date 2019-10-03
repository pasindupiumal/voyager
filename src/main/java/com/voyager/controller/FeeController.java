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
import com.voyager.dao.HaltDaoImpl;
import com.voyager.dao.RouteDaoImpl;
import com.voyager.methods.Methods;
import com.voyager.model.Halt;
import com.voyager.model.Route;
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
				case "/Fees/DeleteHalt":
					deleteHalt(request, response);
					break;
				case "/Fees/UpdateHalt":
					showUpdateHalt(request, response);
					break;
				case "/Fees/SelectRoute":
					showSelectRoute(request, response);
					break;
				case "/Fees/InsertHalt":
					showInsertHalt(request, response);
					break;
			
			}
			
			
		}
		catch(SQLException | ClassNotFoundException e) {
			LOGGER.log(Level.SEVERE, "SQL Exception: " + e.toString());
		}

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.log(Level.INFO, "Control aquired by Fee Controller. Dispatching FeesManagement.");
		
		
		final String action = request.getServletPath();
		System.out.println("Servlet path: " + action);
		
		try {
			switch (action) {
			
				case "/Fees/UpdateHalt":
					updateHalt(request, response);
					break;		
				case "/Fees/InsertHalt":
					insertHalt(request, response);
					break;
			
			}
			
			
		}
		catch(SQLException | ClassNotFoundException e) {
			LOGGER.log(Level.SEVERE, "SQL Exception: " + e.toString());
		}
	}
	
	private final void showViewAllRoutes(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ClassNotFoundException{
		
		final Dao dao = daoFactory.getDataAcessObject("RouteHalt");
		final List<RouteHalt> routeHaltList = dao.findAll();
		
		final RequestDispatcher dispatcher = request.getRequestDispatcher("../jsp/FeesViewAllRoutes.jsp");
		request.setAttribute("routeList", routeHaltList);
		dispatcher.forward(request, response);
		
	}
	
	private final void deleteHalt(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ClassNotFoundException{
		
		final Dao dao = (HaltDaoImpl) daoFactory.getDataAcessObject("Halt");
		final int haltID = Integer.parseInt(request.getParameter("id"));
		final Halt deleteHalt = new Halt(haltID);
		dao.delete(deleteHalt);
		
		response.sendRedirect("/voyager/Fees/ViewAllRoutes");
		
	}
	
	private final void showUpdateHalt(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ClassNotFoundException{
		
		final Dao dao = (HaltDaoImpl) daoFactory.getDataAcessObject("Halt");
		final int haltID = Integer.parseInt(request.getParameter("id"));
		final Optional<Halt> updateHalt = dao.find(haltID);
		final Halt haltToUpdate = updateHalt.get();
		
		final RequestDispatcher updateView = request.getRequestDispatcher("../jsp/UpdateHalt.jsp");
		
		request.setAttribute("haltID", haltToUpdate.getHaltID());
		request.setAttribute("routeID", haltToUpdate.getRouteID());
		request.setAttribute("haltName", haltToUpdate.getHaltName());
		request.setAttribute("price", haltToUpdate.getPrice());
		
		
		updateView.forward(request, response);
	}
	
	private final void updateHalt(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ClassNotFoundException{
		
		System.out.println("Update halt is triggered");
		final int haltID = Integer.parseInt(request.getParameter("haltID"));
		final String routeID = request.getParameter("routeID");
		final String haltName = request.getParameter("haltName");
		final String price = request.getParameter("price");

		final RequestDispatcher view = request.getRequestDispatcher("../jsp/UpdateHalt.jsp");
		
		request.setAttribute("haltID", haltID);
		request.setAttribute("routeID", routeID);
		request.setAttribute("haltName", haltName);
		request.setAttribute("price", price);

		final Dao dao = (HaltDaoImpl) daoFactory.getDataAcessObject("Halt");
		
		if(!Methods.isInteger(routeID)) {
			request.setAttribute("error1", "<p style=\"color:red;\" > Please enter a valid route id</p>");
			view.forward(request, response);
		}
		else if(!Methods.isFloat(price)) {
			request.setAttribute("error2", "<p style=\"color:red;\" > Please enter a valid number price</p>");
			view.forward(request, response);
		}
		else {
			final float priceFloat = Float.parseFloat(price);
			final int routeIDInt = Integer.parseInt(routeID);
			
			final Halt newHalt = new Halt(haltID, routeIDInt, haltName, priceFloat);
			dao.update(newHalt);
			response.sendRedirect("/voyager/Fees/ViewAllRoutes");
		
		}
	
	}
	
	private final void showSelectRoute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ClassNotFoundException{
		
		final Dao dao = daoFactory.getDataAcessObject("route");
		final List<Route> routeList = dao.findAll();
		
		final RequestDispatcher dispatcher = request.getRequestDispatcher("../jsp/ViewRoutesForHalts.jsp");
		request.setAttribute("routeList", routeList);
		dispatcher.forward(request, response);
	}
	
	private final void showInsertHalt(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ClassNotFoundException{
		
		final RequestDispatcher dispatcher = request.getRequestDispatcher("../jsp/AddNewHalt.jsp");
		request.setAttribute("routeID", request.getParameter("id"));
		dispatcher.forward(request, response);
	}
	
	private final void insertHalt(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ClassNotFoundException{
		
		System.out.println("Insert route is triggered");
		final String routeIDString = request.getParameter("routeID");
		System.out.println("Route ID: " + routeIDString);
		final int routeID = Integer.parseInt(request.getParameter("routeID"));
		
		final String haltName = request.getParameter("haltName");
		final String priceString = request.getParameter("price");

		final RequestDispatcher view = request.getRequestDispatcher("../jsp/AddNewHalt.jsp");
		
		request.setAttribute("routeID", routeID);
		request.setAttribute("haltName", haltName);
		request.setAttribute("price", priceString);
		
		final Dao dao = (HaltDaoImpl) daoFactory.getDataAcessObject("Halt");
		
		if(!Methods.isFloat(priceString)) {
			request.setAttribute("error1", "<p style=\"color:red;\" > Please enter a valid number for price</p>");
			view.forward(request, response);
		}
		else if(((HaltDaoImpl) dao).isAdded(routeID, haltName, Float.parseFloat(priceString))) {
			request.setAttribute("error2", "<p style=\"color:red;\" > Halt has already been added to this route</p>");
			view.forward(request, response);
		}
		else {
			final float price = Float.parseFloat(priceString);
			final Halt newHalt = new Halt(routeID, haltName, price);
			dao.create(newHalt);
			response.sendRedirect("/voyager/Fees/ViewAllRoutes");
		
		}
	
	}

}

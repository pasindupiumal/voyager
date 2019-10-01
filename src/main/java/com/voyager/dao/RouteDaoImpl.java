package com.voyager.dao;

import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import com.voyager.database.DBConnection;
import com.voyager.model.Route;

public class RouteDaoImpl implements RouteDao{
	
	private Connection connection = null;
	
	public RouteDaoImpl() throws SQLException, ClassNotFoundException {
		
		//Get database connection
		final DBConnection dbConnection = DBConnection.getInstance();
		connection = dbConnection.getConnection();
	}

	@Override
	public Optional<Route> find(Integer id) throws SQLException {
		
		//Retrieve routes given the routeID
		final String sqlStatement1 = "SELECT * FROM routes where routeID = ?";
		
		final PreparedStatement ps1 = connection.prepareStatement(sqlStatement1);
		ps1.setInt(1,  id);
		
		//Execute query and get the result set
		final ResultSet rs1 = ps1.executeQuery(); 
		
		//Create temporary variables to hold result set values
		int routeID = 0;
		String routeName = null;
		String routeNumber = null;
		String origin = null;
		String destination = null;
		String routeOffice = null;
		float totalDistance = 0;
		
		//Read from the result set
		while( rs1.next() ) {
			
			routeID = rs1.getInt("routeID");
			routeName = rs1.getString("routeName");
			routeNumber = rs1.getString("routeNumber");
			origin = rs1.getString("origin");
			destination = rs1.getString("destination");
			routeOffice = rs1.getString("routeOffice");
			totalDistance = rs1.getFloat("totalDistance");
			
		}
		
		//Close preapredStatement
		if(ps1 != null) {
			ps1.close();
		}
		
		//Close result set
		if(rs1 != null) {
			rs1.close();
		}
		
		//Create and return Route object
		return Optional.of(new Route(routeID, routeName, routeNumber, origin, destination, routeOffice, totalDistance));
		
		
		
	}

	@Override
	public List<Route> findAll() throws SQLException {
		
		//Retrieve all the routes from the database
		final List<Route> routeList = new ArrayList<Route>();
		
		final String sqlStatement1 = "SELECT * FROM routes";
		final PreparedStatement ps1 = connection.prepareStatement(sqlStatement1);
		final ResultSet rs1 = ps1.executeQuery();
		
		//Read from the result set
		while(rs1.next()) {
			
			final int routeID = rs1.getInt("routeID");
			final String routeName = rs1.getString("routeName");
			final String routeNumber = rs1.getString("routeNumber");
			final String origin = rs1.getString("origin");
			final String destination = rs1.getString("destination");
			final String routeOffice = rs1.getString("routeOffice");
			final float totalDistance = rs1.getFloat("totalDistance");
			
			//Create route object and add to the list
			final Route newRoute = new Route(routeID, routeName, routeNumber, origin, destination, routeOffice, totalDistance);
			routeList.add(newRoute);
			
		}
		
		//Close preapredStatement
		if(ps1 != null) {
			ps1.close();
		}
		
		//Close result set
		if(rs1 != null) {
			rs1.close();
		}
		
		//Return the routes list
		return routeList;
	}

	@Override
	public boolean create(Route route) throws SQLException {
		
		boolean rowInserted = false;
		
		final String sqlStatement1 = "INSERT INTO routes(routeName, routeNumber, origin, destination, routeOffice, totalDistance) VALUES(?,?,?,?,?,?)";
		final PreparedStatement ps1 = connection.prepareStatement(sqlStatement1);
		
		ps1.setString(1,  route.getRouteName());
		ps1.setString(2,  route.getRouteNumber());
		ps1.setString(3,  route.getOrigin());
		ps1.setString(4,  route.getDestination());
		ps1.setString(5,  route.getRouteOffice());
		ps1.setFloat(6, route.getTotalDistance());
		
		//Insert the row and get the confirmation
		rowInserted = ps1.executeUpdate() > 0;
		
		//Close preapredStatement
		if(ps1 != null) {
			ps1.close();
		}
		
		return rowInserted;
		
	}

	@Override
	public boolean update(Route route) throws SQLException {
		
		boolean rowUpdated = false;
		
		final String sqlStatement1 = "UPDATE routes SET routeName=?, routeNumber=?, origin=?, destination=?, routeOffice=?, totalDistance=? WHERE routeID=?";
		final PreparedStatement ps1 = connection.prepareStatement(sqlStatement1);
		
		ps1.setString(1,  route.getRouteName());
		ps1.setString(2,  route.getRouteNumber());
		ps1.setString(3,  route.getOrigin());
		ps1.setString(4,  route.getDestination());
		ps1.setString(5,  route.getRouteOffice());
		ps1.setFloat(6,  route.getTotalDistance());
		ps1.setInt(7, route.getRouteID());
		
		//Update row and get confirmation
		rowUpdated = ps1.executeUpdate() > 0;
		
		//Close preapredStatement
		if(ps1 != null) {
			ps1.close();
		}
		
		return rowUpdated;
				
	
	}

	@Override
	public boolean delete(Route route) throws SQLException {
		
		boolean rowDeleted = false;
		
		final String sqlStatement1 = "DELETE FROM routes WHERE routeID=?";
		final PreparedStatement ps1 = connection.prepareStatement(sqlStatement1);
		
		ps1.setInt(1,  route.getRouteID());
		
		//Delete the row and get confirmation
		rowDeleted = ps1.executeUpdate() > 0;
		
		//Close preapredStatement
		if(ps1 != null) {
			ps1.close();
		}
		
		return rowDeleted;
		
		
	}

	@Override
	public boolean isAdded(String routeName, String routeNumber, String routeOffice, Float totalDistance) throws SQLException {
		//Retrieve routes given the routeID
		final String sqlStatement1 = "SELECT * FROM routes where routeName = ? and routeNumber = ? and routeOffice=? and totalDistance=?";
		
		final PreparedStatement ps1 = connection.prepareStatement(sqlStatement1);
		ps1.setString(1,  routeName);
		ps1.setString(2, routeNumber);
		ps1.setString(3, routeOffice);
		ps1.setFloat(4,  totalDistance);
		
		//Execute query and get the result set
		final ResultSet rs1 = ps1.executeQuery(); 
	
		//Read from the result set
		if( rs1.next() ) {
			
			//Close preapredStatement
			if(ps1 != null) {
				ps1.close();
			}
			
			//Close result set
			if(rs1 != null) {
				rs1.close();
			}
			return true;
			
		}
		else {
			
			//Close preapredStatement
			if(ps1 != null) {
				ps1.close();
			}
			
			//Close result set
			if(rs1 != null) {
				rs1.close();
			}
			return false;
		}
				
	}

	@Override
	public Optional<Route> findByName(String routeName, String routeNumber, String routeOffice, Float totalDistance) throws SQLException { 
	
		//Retrieve routes given the routeID
		final String sqlStatement1 = "SELECT * FROM routes where routeName = ? and routeNumber = ? and routeOffice=? and totalDistance=?";
		
		final PreparedStatement ps1 = connection.prepareStatement(sqlStatement1);
		ps1.setString(1,  routeName);
		ps1.setString(2, routeNumber);
		ps1.setString(3, routeOffice);
		ps1.setFloat(4,  totalDistance);
		
		//Execute query and get the result set
		final ResultSet rs1 = ps1.executeQuery(); 
		
		//Create temporary variables to hold result set values
		int routeID2 = 0;
		String routeName2 = null;
		String routeNumber2 = null;
		String origin2 = null;
		String destination2 = null;
		String routeOffice2 = null;
		float totalDistance2 = 0;
	
		//Read from the result set
		while( rs1.next() ) {
			
			routeID2 = rs1.getInt("routeID");
			routeName2 = rs1.getString("routeName");
			routeNumber2 = rs1.getString("routeNumber");
			origin2 = rs1.getString("origin");
			destination2 = rs1.getString("destination");
			routeOffice2 = rs1.getString("routeOffice");
			totalDistance2 = rs1.getFloat("totalDistance");
			
		}
		
		//Close preapredStatement
		if(ps1 != null) {
			ps1.close();
		}
		
		//Close result set
		if(rs1 != null) {
			
			rs1.close();
		}
		
		//Create and return Route object
		return Optional.of(new Route(routeID2, routeName2, routeNumber2, origin2, destination2, routeOffice2, totalDistance2));
		
	}

}
	

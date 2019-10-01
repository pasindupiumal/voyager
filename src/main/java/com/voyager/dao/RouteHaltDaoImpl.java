package com.voyager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.voyager.database.DBConnection;
import com.voyager.model.Halt;
import com.voyager.model.RouteHalt;

public class RouteHaltDaoImpl implements RouteHaltDao {
	
	private Connection connection = null;
	
	public RouteHaltDaoImpl() throws SQLException, ClassNotFoundException {
		
		//Get database connection
		final DBConnection dbConnection = DBConnection.getInstance();
		connection = dbConnection.getConnection();
	}

	@Override
	public Optional<RouteHalt> find(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RouteHalt> findAll() throws SQLException {
		
		//Retrieve all the routes with halts from the database
		final List<RouteHalt> routeHaltList = new ArrayList<RouteHalt>();
		
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
			final RouteHalt newRouteHalt = new RouteHalt(routeID, routeName, routeNumber, origin, destination, routeOffice, totalDistance);
			
			final String sqlStatement2 = "SELECT * FROM halts WHERE routeID = ? ";
			final PreparedStatement ps2 = connection.prepareStatement(sqlStatement2);
			ps2.setInt(1, newRouteHalt.getRouteID());
			final ResultSet rs2 = ps2.executeQuery();
			
			//Read from the result set
			while(rs2.next()) {
				
				final int haltID = rs2.getInt("haltID");
				final int routeId = rs2.getInt("routeID");
				final String haltName = rs2.getString("haltName");
				final float price = rs2.getFloat("price");
				
				final Halt newHalt = new Halt(haltID, routeId, haltName, price);
				
				newRouteHalt.addToHalts(newHalt);
				
			}
			
			//Close preapredStatement
			if(ps2 != null) {
				ps1.close();
			}
			
			//Close result set
			if(rs2 != null) {
				rs1.close();
			}
			
			routeHaltList.add(newRouteHalt);
			
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
		return routeHaltList;
	}

	@Override
	public boolean create(RouteHalt o) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(RouteHalt o) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(RouteHalt o) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}

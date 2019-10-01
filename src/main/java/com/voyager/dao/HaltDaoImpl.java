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

public class HaltDaoImpl implements HalfDao{
	
	private Connection connection = null;
	
	public HaltDaoImpl() throws SQLException, ClassNotFoundException{
		
		//Get database connection
		final DBConnection dbConnection = DBConnection.getInstance();
		connection = dbConnection.getConnection();
	}

	@Override
	public Optional<Halt> find(Integer id) throws SQLException {
		
		//Retrieve halts given the haltID
		final String sqlStatement1 = "SELECT * FROM halts where haltID = ?";
		
		final PreparedStatement ps1 = connection.prepareStatement(sqlStatement1);
		ps1.setInt(1,  id);
		
		//Execute query and get the result set
		final ResultSet rs1 = ps1.executeQuery(); 
		
		//Create temporary variables to hold result set values
		int haltID = 0;
		int routeID = 0;
		String haltName = null;
		float price = 0;
		
		//Read from the result set
		while( rs1.next() ) {
			
			haltID = rs1.getInt("haltID");
			routeID = rs1.getInt("routeID");
			haltName = rs1.getString("haltName");
			price = rs1.getFloat("price");
			
		}
		
		//Close preapredStatement
		if(ps1 != null) {
			ps1.close();
		}
		
		//Close result set
		if(rs1 != null) {
			rs1.close();
		}
		
		//Create and return halt object
		return Optional.of(new Halt(haltID, routeID, haltName, price));
	}

	@Override
	public List<Halt> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Halt halt) throws SQLException {
		
		boolean rowInserted = false;
		
		final String sqlStatement1 = "INSERT INTO halts(routeID, haltName, price) VALUES(?,?,?)";
		final PreparedStatement ps1 = connection.prepareStatement(sqlStatement1);
		
		ps1.setInt(1, halt.getRouteID());
		ps1.setString(2, halt.getHaltName());
		ps1.setFloat(3, halt.getPrice());
		
		//Insert the row and get the confirmation
		rowInserted = ps1.executeUpdate() > 0;
		
		//Close preapredStatement
		if(ps1 != null) {
			ps1.close();
		}
		
		return rowInserted;
	}

	@Override
	public boolean update(Halt halt) throws SQLException {
		
		boolean rowUpdated = false;
		
		final String sqlStatement1 = "UPDATE halts SET routeID=?, haltName=?, price=? WHERE haltID=?";
		final PreparedStatement ps1 = connection.prepareStatement(sqlStatement1);
		
		ps1.setInt(1, halt.getRouteID());
		ps1.setString(2, halt.getHaltName());
		ps1.setFloat(3, halt.getPrice());
		ps1.setInt(4, halt.getHaltID());
		
		//Update row and get confirmation
		rowUpdated = ps1.executeUpdate() > 0;
		
		//Close preapredStatement
		if(ps1 != null) {
			ps1.close();
		}
		
		return rowUpdated;
	}

	@Override
	public boolean delete(Halt halt) throws SQLException {
		
		boolean rowDeleted = false;
		
		final String sqlStatement1 = "DELETE FROM halts WHERE haltID=?";
		final PreparedStatement ps1 = connection.prepareStatement(sqlStatement1);
		
		ps1.setInt(1, halt.getHaltID());
		
		//Delete the row and get confirmation
		rowDeleted = ps1.executeUpdate() > 0;
		
		
		//Close preapredStatement
		if(ps1 != null) {
			ps1.close();
		}
		
		return rowDeleted;
	}

	@Override
	public List<Halt> findHaltsByRouteID(int routeID) throws SQLException {
		
		//Retrieve all the halts from the database based on the route id
		final List<Halt> haltList = new ArrayList<Halt>();
		
		final String sqlStatement1 = "SELECT * FROM halts where routeID=?";
		final PreparedStatement ps1 = connection.prepareStatement(sqlStatement1);
		ps1.setInt(1, routeID);
		final ResultSet rs1 = ps1.executeQuery();
		
		//Read from the result set
		while(rs1.next()) {
			
			final int haltID = rs1.getInt("haltID");
			final int routeId = rs1.getInt("routeID");
			final String haltName = rs1.getString("haltName");
			final float price = rs1.getFloat("price");
			
			//Create halt object and add to the list
			final Halt newHalt = new Halt(haltID, routeId, haltName, price);
			haltList.add(newHalt);
			
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
		return haltList;
	}

	@Override
	public boolean isAdded(Integer routeID, String haltName, Float price) throws SQLException {

		//Retrieve halt
		final String sqlStatement1 = "SELECT * FROM halts where routeID = ? and haltName = ? and price = ?";
		
		final PreparedStatement ps1 = connection.prepareStatement(sqlStatement1);
		ps1.setInt(1, routeID);
		ps1.setString(2, haltName);
		ps1.setFloat(3, price);
		
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


}

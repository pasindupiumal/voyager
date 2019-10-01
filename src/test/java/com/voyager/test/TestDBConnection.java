/**
 * 
 */
package com.voyager.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.voyager.database.DBConnection;
import com.voyager.model.Route;

/**
 * @author pasindu
 *
 */
public class TestDBConnection {

	/**
	 * @throws java.lang.Exception
	 */
	
	private Route route = null;
	private PreparedStatement ps1 = null;
	private PreparedStatement ps2 = null;

	
	@Before
	public void setUp() throws Exception {
		
		//Instantiate route object
		route = new Route(-1, "TestRoute", "Test01", "TestOrigin", "TestDestination", "TestOffice", 120);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
		//Close preapredStatement
		if(ps1 != null) {
			ps1.close();
		}
		
		if(ps1 != null) {
			ps2.close();
		}
		
		System.gc();
	}

	@Test
	public void test() {
		
		
		try {
			//Get database connection
			final DBConnection dbConnection = DBConnection.getInstance();
			final Connection connection = dbConnection.getConnection();
			
			boolean rowInserted = false;
			
			final String sqlStatement1 = "INSERT INTO routes(routeID, routeName, routeNumber, origin, destination, routeOffice, totalDistance) VALUES(?,?,?,?,?,?,?)";
			ps1 = connection.prepareStatement(sqlStatement1);
			
			ps1.setInt(1, route.getRouteID());
			ps1.setString(2,  route.getRouteName());
			ps1.setString(3,  route.getRouteNumber());
			ps1.setString(4,  route.getOrigin());
			ps1.setString(5,  route.getDestination());
			ps1.setString(6,  route.getRouteOffice());
			ps1.setFloat(7, route.getTotalDistance());
			
			//Insert the row and get the confirmation
			rowInserted = ps1.executeUpdate() > 0;
			
			if(rowInserted == false) {
				fail("Database connection error");
			}
			
			boolean rowDeleted = false;
			
			final String sqlStatement2 = "DELETE FROM routes WHERE routeID=?";
			ps2 = connection.prepareStatement(sqlStatement2);
			
			ps2.setInt(1,  route.getRouteID());
			
			//Delete the row and get confirmation
			rowDeleted = ps2.executeUpdate() > 0;
			
			
			if(rowDeleted == false) {
				fail("Database connection error");
			}
			
			
		} catch (ClassNotFoundException e) {
			
			fail("Class not found exception occured: " + e.toString());
			
		} catch (SQLException e) {
			
			fail("SQL exception occured: " + e.toString());
		}
	}

}

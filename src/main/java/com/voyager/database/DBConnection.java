package com.voyager.database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.Connection;
import com.sun.istack.internal.logging.Logger;


public class DBConnection {
	
	private static final Logger LOGGER = Logger.getLogger(DBConnection.class);
	
	private static DBConnection dbConnectionInstance;
	
	private DBConnection() {}
	
	public final static DBConnection getInstance() {
		
		LOGGER.log(Level.INFO, "getInstance of DBConnection called");
		
		if(dbConnectionInstance == null) {
			
			synchronized (DBConnection.class) {
				
				if(dbConnectionInstance == null) {
					
					dbConnectionInstance = new DBConnection();
					LOGGER.log(Level.INFO, "dbConnectionInstance is null. Creating new DBConnection object");
				}
			}
		}
		
		return dbConnectionInstance;
	}
	
	
	public final Connection getConnection() throws ClassNotFoundException, SQLException{
		
		try {
			
			LOGGER.log(Level.INFO, "Retrieving new connection from mysql");
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/voyager", "root", "");
			return connection;
		}
		catch(ClassNotFoundException e) {
			LOGGER.log(Level.SEVERE, "ClassNotFoundException " + e.toString());
			return null;
		}
		catch(SQLException e) {
			LOGGER.log(Level.SEVERE, "SQLException " + e.toString());
			return null;
		}
	}
	
	

}

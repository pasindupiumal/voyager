package com.voyager.database;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class DBConnection {
	
	private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName());
	
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
		
		InputStream input = null;
		
		try {
			
			LOGGER.log(Level.INFO, "Retrieving new connection from mysql");
			
			final MysqlDataSource dataSource = new MysqlDataSource();
			
			final String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("database.properties")).getPath();
			input = new FileInputStream(rootPath);
			
			final Properties props = new Properties();
			props.load(input);
			
			dataSource.setDatabaseName(props.getProperty("database"));
			dataSource.setServerName(props.getProperty("serverName"));
			dataSource.setPort(Integer.parseInt(props.getProperty("port")));
			dataSource.setUser(props.getProperty("user"));
			dataSource.setPassword(props.getProperty("password"));
			
			return dataSource.getConnection();
			
		}
		catch(SQLException e) {
			
			LOGGER.log(Level.SEVERE, "SQLException " + e.toString());
			return null;
			
		} catch (FileNotFoundException e) {
			
			LOGGER.log(Level.SEVERE, "FileNotFoundEException " + e.toString());
			return null;
			
		} catch (IOException e) {
			
			LOGGER.log(Level.SEVERE, "IOException " + e.toString());
			return null;
			
		}
		finally {
			
			if(input != null) {
				try {
					input.close();
				} catch (IOException e) {
					
					LOGGER.log(Level.SEVERE, "IOException " + e.toString());
					
				}
			}
		}
	}
	
	

}

package com.voyager.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.sun.istack.internal.logging.Logger;

public class DBConnection {
	
	private final MysqlDataSource mysqlDataSource;
	private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName(), null);
	
	private DBConnection() {
		
		MysqlDataSource mysql = new MysqlDataSource();
		final String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("database.properties")).getPath();
		InputStream input = null;
		
		try {
			
			input = new FileInputStream(rootPath);
			Properties props = new Properties();
			props.load(input);
			
			mysql.setDatabaseName(props.getProperty("database"));
			mysql.setServerName(props.getProperty("serverName"));
			mysql.setPort(Integer.parseInt(props.getProperty("port")));
			mysql.setUser(props.getProperty("user"));
			mysql.setPassword(props.getProperty("password"));
			
			
		}
		//Handle the exception which occurs if the database.properties file is not found
		catch(FileNotFoundException e) {
			LOGGER.log(Level.SEVERE, "File 'database.properties' file not found", e);
		}
		//Handle the exceptions which occurs because of I/O errors
		catch(IOException e) {
			LOGGER.log(Level.SEVERE, "I/O Error", e);
		}
		finally {
			
			//If the input is not null. Close the input stream
			if(input != null) {
				
				try {
					input.close();
				}
				catch(IOException e) {
					LOGGER.log(Level.SEVERE, "Failed to close input stream", e);
				}
			}
			
		}
		
		this.mysqlDataSource = mysql;
		
		
	}
	
	public final static Connection getConnection() throws SQLException{
		final DBConnection instance = new DBConnection();
		return instance.getConnection();
	}
	
	

}

package com.voyager.dao;

import java.sql.SQLException;

//Implementation of factory pattern to produce required dao objects
public class DaoFactory {
	
	public DaoFactory() {
		super();
	}
	

	public Dao getDataAcessObject(String daoType) throws ClassNotFoundException, SQLException {
		
		if(daoType.equalsIgnoreCase("Route")) {
			
			return new RouteDaoImpl();
		}
		
		return null;
		
	}
	
}

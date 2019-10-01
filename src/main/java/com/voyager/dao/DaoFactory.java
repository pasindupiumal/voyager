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
		else if(daoType.equalsIgnoreCase("Halt")) {
			return new HaltDaoImpl();
		}
		
		throw new IllegalArgumentException("Invalid DAO type passed to DaoFactory. Doesn't exist");
		
	}
	
}

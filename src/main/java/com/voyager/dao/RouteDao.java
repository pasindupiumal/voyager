package com.voyager.dao;

import java.sql.SQLException;

import com.voyager.model.Route;

public interface RouteDao extends Dao<Route, Integer>{
	
	public boolean isAdded(String routeName, String routeNumber) throws SQLException;

}

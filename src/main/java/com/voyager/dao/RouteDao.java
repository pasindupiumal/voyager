package com.voyager.dao;

import java.sql.SQLException;
import java.util.Optional;

import com.voyager.model.Route;

public interface RouteDao extends Dao<Route, Integer, String, Float>{
	
	public boolean isAdded(String id1, String id2, String id3, Float id4) throws SQLException;
	Optional<Route> findByName(String id1, String id2, String id3, Float id4) throws SQLException;
}

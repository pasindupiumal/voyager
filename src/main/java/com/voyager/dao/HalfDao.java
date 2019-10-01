package com.voyager.dao;

import java.sql.SQLException;
import java.util.List;

import com.voyager.model.Halt;

public interface HalfDao extends Dao<Halt, Integer, String, Float>{
	
	public List<Halt> findHaltsByRouteID(int routeID) throws SQLException;
	public boolean isAdded(Integer id1, String id2, Float id3) throws SQLException;

}

package com.voyager.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T, ID, IDString, IDFloat> {
	
	Optional<T> find(ID id) throws SQLException;
	List<T> findAll() throws SQLException;
	boolean create(T o) throws SQLException;
	boolean update(T o) throws SQLException;
	boolean delete(T o) throws SQLException;
	
	

}

package com.voyager.test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.voyager.dao.DaoFactory;
import com.voyager.dao.RouteDaoImpl;
import com.voyager.database.DBConnection;
import com.voyager.model.Route;

class TestInsertRoute {

	private DaoFactory daoFactory = null;
	private RouteDaoImpl routeDao = null;
	private Route route = null;
	
	@BeforeEach
	void setUp() throws Exception {
		
		daoFactory = new DaoFactory();
		routeDao = (RouteDaoImpl) daoFactory.getDataAcessObject("route");
		route = new Route("TestRoute", "Test01", "TestOrigin", "TestDestination", "TestOffice", 120);
	}

	@AfterEach
	void tearDown() throws Exception {
		
		System.gc();
	}

	@Test
	void test() {
		
		try {
			
			boolean rowInserted = routeDao.create(route);
			
			if(rowInserted == false) {
				fail("Database connection error");
			}
			
			//Read from database and check whether values are stored properly
			final Optional<Route> getRoute = routeDao.findByName(route.getRouteName(), route.getRouteNumber(), route.getRouteOffice(), route.getTotalDistance());
			final Route currentRoute = getRoute.get();
			
			assertEquals("TestRoute", currentRoute.getRouteName());
			assertEquals("Test01", currentRoute.getRouteNumber());
			assertEquals("TestOrigin", currentRoute.getOrigin());
			assertEquals("TestDestination", currentRoute.getDestination());
			assertEquals("TestOffice", currentRoute.getRouteOffice());
			assertEquals(120, currentRoute.getTotalDistance());
			
			
			boolean rowDeleted = routeDao.delete(currentRoute);
			
		
			if(rowDeleted == false) {
				fail("Database connection error");
			}
			
			
		} catch (SQLException e) {
			
			fail("SQL exception occured: " + e.toString());
		}
	}

}

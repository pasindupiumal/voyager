package com.voyager.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.voyager.dao.DaoFactory;
import com.voyager.dao.RouteDaoImpl;
import com.voyager.model.Route;

class TestFindByName {

	private DaoFactory daoFactory = null;
	private RouteDaoImpl routeDao = null;
	
	
	@BeforeEach
	void setUp() throws Exception {
		
		daoFactory = new DaoFactory();
		routeDao = (RouteDaoImpl) daoFactory.getDataAcessObject("route");
	}

	@AfterEach
	void tearDown() throws Exception {
		
		System.gc();
	}

	@Test
	void test() {
		try {
			//Get the details of the second route
			final Optional<Route> getRoute = routeDao.findByName("Galwana-Kotikawatta", "143", "Angoda", (float) 100.00);
			final Route currentRoute = getRoute.get();
			
			assertEquals(2, currentRoute.getRouteID());
			assertEquals("Galwana-Kotikawatta", currentRoute.getRouteName());
			assertEquals("143", currentRoute.getRouteNumber());
			assertEquals("Galwana", currentRoute.getOrigin());
			assertEquals("Kotikawatta", currentRoute.getDestination());
			assertEquals("Angoda", currentRoute.getRouteOffice());
			assertEquals(100, currentRoute.getTotalDistance());
			
		} catch (SQLException e) {
			
			fail("Find route failed. SQL exception occured: " + e.toString());
		}
	}

}

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

class TestUpdateRoute {

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
			//Get the details of the second route and check whether they are what we want
			final Optional<Route> getRoute = routeDao.findByName("Galwana-Kotikawatta", "143", "Angoda", (float) 100.00);
			final Route currentRoute = getRoute.get();
			
			assertEquals(2, currentRoute.getRouteID());
			assertEquals("Galwana-Kotikawatta", currentRoute.getRouteName());
			assertEquals("143", currentRoute.getRouteNumber());
			assertEquals("Galwana", currentRoute.getOrigin());
			assertEquals("Kotikawatta", currentRoute.getDestination());
			assertEquals("Angoda", currentRoute.getRouteOffice());
			assertEquals(100, currentRoute.getTotalDistance());
			
			//Update the details of the second route
			currentRoute.setRouteNumber("165");
			currentRoute.setRouteOffice("Colombo");
			currentRoute.setTotalDistance(1000);
			
			routeDao.update(currentRoute);
			
			//Read from the table and check whether the route has been updated
			final Optional<Route> getUpdatedRoute = routeDao.find(currentRoute.getRouteID());
			final Route updatedRoute = getUpdatedRoute.get();
			
			assertEquals(2, updatedRoute.getRouteID());
			assertEquals("Galwana-Kotikawatta", updatedRoute.getRouteName());
			assertEquals("165", updatedRoute.getRouteNumber());
			assertEquals("Galwana", updatedRoute.getOrigin());
			assertEquals("Kotikawatta", updatedRoute.getDestination());
			assertEquals("Colombo", updatedRoute.getRouteOffice());
			assertEquals(1000, updatedRoute.getTotalDistance());
			
			//Test is successful. Update the values to their originals.
			currentRoute.setRouteNumber("143");
			currentRoute.setRouteOffice("Angoda");
			currentRoute.setTotalDistance(100);
			
			routeDao.update(currentRoute);
			
			
		} catch (SQLException e) {
			
			fail("Find route failed. SQL exception occured: " + e.toString());
		}
	}

}

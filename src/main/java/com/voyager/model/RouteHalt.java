package com.voyager.model;

import java.util.ArrayList;
import java.util.List;

public class RouteHalt {
	
	private int routeID;
	private String routeName;
	private String routeNumber;
	private String origin;
	private String destination;
	private String routeOffice;
	private float totalDistance;
	private List<Halt> haltList = null;
	
	public RouteHalt(int routeID, String routeName, String routeNumber, String origin, String destination, String routeOffice, float totalDistance) {
		super();
		this.routeID = routeID;
		this.routeName = routeName;
		this.routeNumber = routeNumber;
		this.origin = origin;
		this.destination = destination;
		this.routeOffice = routeOffice;
		this.totalDistance = totalDistance;
		this.haltList = new ArrayList<Halt>();
	}

	public int getRouteID() {
		return routeID;
	}

	public void setRouteID(int routeID) {
		this.routeID = routeID;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getRouteNumber() {
		return routeNumber;
	}

	public void setRouteNumber(String routeNumber) {
		this.routeNumber = routeNumber;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getRouteOffice() {
		return routeOffice;
	}

	public void setRouteOffice(String routeOffice) {
		this.routeOffice = routeOffice;
	}

	public float getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(float totalDistance) {
		this.totalDistance = totalDistance;
	}
	
	public List<Halt> getHaltList(){
		return this.haltList;
	}
	
	public void addToHalts(Halt halt) {
		this.haltList.add(halt);
	}
	
	public void removeFromHalts(Halt halt) {
		
		int index = this.haltList.indexOf(halt);
		this.haltList.remove(index);
	}

}

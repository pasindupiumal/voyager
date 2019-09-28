package com.voyager.model;

public class Route {
	
	private int routeID;
	private String routeName;
	private String routeNumber;
	private String origin;
	private String destination;
	private String routeOffice;
	private float totalDistance;
	
	
	public Route(int routeID, String routeName, String routeNumber, String origin, String destination, String routeOffice, float totalDistance) {
		super();
		this.routeID = routeID;
		this.routeName = routeName;
		this.routeNumber = routeNumber;
		this.origin = origin;
		this.destination = destination;
		this.routeOffice = routeOffice;
		this.totalDistance = totalDistance;
	}
	
	public Route(String routeName, String routeNumber, String origin, String destination, String routeOffice, float totalDistance) {
		super();
		this.routeName = routeName;
		this.routeNumber = routeNumber;
		this.origin = origin;
		this.destination = destination;
		this.routeOffice = routeOffice;
		this.totalDistance = totalDistance;
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


	@Override
	public String toString() {
		return ("Route ID: " + this.routeID + "\nRoute Name: " + this.routeName + "\nRoute Number: " + this.routeNumber + "\nOrigin: " + this.origin + "\nDestination: " + this.destination + "\nRoute Office: " + this.routeOffice);
		
	}
	
	

}

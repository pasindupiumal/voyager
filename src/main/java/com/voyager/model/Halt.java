package com.voyager.model;

public class Halt {
	
	private int haltID;
	private int routeID;
	private String haltName;
	private float price;
	
	public Halt(int haltID) {
		super();
		this.haltID = haltID;
	}


	public Halt(int haltID, int routeID, String haltName, float price) {
		super();
		this.haltID = haltID;
		this.routeID = routeID;
		this.haltName = haltName;
		this.price = price;
	}
	
	
	public Halt(int routeID, String haltName, float price) {
		super();
		this.routeID = routeID;
		this.haltName = haltName;
		this.price = price;
	}


	public int getRouteID() {
		return routeID;
	}
	public void setRouteID(int routeID) {
		this.routeID = routeID;
	}
	public int getHaltID() {
		return haltID;
	}
	public void setHaltID(int haltID) {
		this.haltID = haltID;
	}
	public String getHaltName() {
		return haltName;
	}
	public void setHaltName(String haltName) {
		this.haltName = haltName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

	
	

}

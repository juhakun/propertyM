package de.muulti.spring.entity;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

//@Component("unit")
public class Unit {

	private String unitName;
	private Renter renter;
	private double sizeM2;
	private double noOfRooms;
	private String floor;
	private ArrayList<Counter> unitCounters = new ArrayList<>();

	public String getUniTName() {
		return unitName;
	}

	public void setUniTName(String uniTName) {
		this.unitName = uniTName;
	}

	public Renter getRenter() {
		return renter;
	}

	public void setRenter(Renter renter) {
		this.renter = renter;
	}

	public double getSizeM2() {
		return sizeM2;
	}

	public void setSizeM2(double sizeM2) {
		this.sizeM2 = sizeM2;
	}

	public double getNoOfRooms() {
		return noOfRooms;
	}

	public void setNoOfRooms(double noOfRooms) {
		this.noOfRooms = noOfRooms;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public ArrayList<Counter> getUnitCounters() {
		return unitCounters;
	}

	public void setUnitCounters(ArrayList<Counter> unitCounters) {
		this.unitCounters = unitCounters;
	}



}

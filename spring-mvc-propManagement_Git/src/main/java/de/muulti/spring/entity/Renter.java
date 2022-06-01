package de.muulti.spring.entity;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

//@Entity
//@Table(name = "Renter")
@Component("renter")
public class Renter extends Person {

	private double rent;
	private double monthlyNkInAdvance;
	private int noOfPeople;
	private LocalDate moveIn;
	private Date sqlDateMoveIn;
	private LocalDate moveOut;
	private Date sqlDateMoveOut;
	private Address address;

	public double getRent() {
		return rent;
	}

	public void setRent(double rent) {
		this.rent = rent;
	}

	public double getMonthlyNkInAdvance() {
		return monthlyNkInAdvance;
	}

	public void setMonthlyNkInAdvance(double monthlyNkInAdvance) {
		this.monthlyNkInAdvance = monthlyNkInAdvance;
	}

	public int getNoOfPeople() {
		return noOfPeople;
	}

	public void setNoOfPeople(int noOfPeople) {
		this.noOfPeople = noOfPeople;
	}

	public LocalDate getMoveIn() {
		return moveIn;
	}

	public void setMoveIn(LocalDate moveIn) {
		this.moveIn = moveIn;
	}

	public Date getSqlDateMoveIn() {
		return sqlDateMoveIn;
	}

	public void setSqlDateMoveIn(Date sqlDateMoveIn) {
		this.sqlDateMoveIn = sqlDateMoveIn;
	}

	public LocalDate getMoveOut() {
		return moveOut;
	}

	public void setMoveOut(LocalDate moveOut) {
		this.moveOut = moveOut;
	}

	public Date getSqlDateMoveOut() {
		return sqlDateMoveOut;
	}

	public void setSqlDateMoveOut(Date sqlDateMoveOut) {
		this.sqlDateMoveOut = sqlDateMoveOut;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	// Constructors
	public Renter() {

	}

	public Renter(String formOfAddress, 
			String firstName, String lastName, 
			Address address, 
			double rent, 
			double monthlyNkInAdvance, 
			int noOfPeople, 
			LocalDate moveIn, 
			LocalDate moveOut) {
		this.rent = rent; 
		this.monthlyNkInAdvance = monthlyNkInAdvance;
		this.noOfPeople = noOfPeople;
		this.moveIn = moveIn; 
		this.moveOut = moveOut;

	}

}

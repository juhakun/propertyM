package de.muulti.spring.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Entity
@DiscriminatorValue("false")
@Component("renter")
public class Renter extends Person {

	@Column(name = "rent")
	private double rent;

	@Column(name = "monthlyNKInAdvance")
	private double monthlyNkInAdvance;

	@Column(name = "noOfPeople")
	private int noOfPeople;

	@Transient
	private LocalDate moveIn;

	@Transient
	private String moveInString;

	@Column(name = "dateMoveIn")
	private Date sqlDateMoveIn;

	@Transient
	private LocalDate moveOut;

	@Transient
	private String moveOutString;

	@Column(name = "dateMoveOut")
	private Date sqlDateMoveOut;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "Address_idAddress")
	private Address address;

	@Column(name = "isRenter")
	private String isRenter = "true";

	public String getIsRenter() {
		return isRenter;
	}

	public void setIsRenter(String isRenter) {
		this.isRenter = isRenter;
	}

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

	public String getMoveInString() {
		return moveInString;
	}

	public void setMoveInString(String moveInString) {
		this.moveInString = moveInString;
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

	public String getMoveOutString() {
		return moveOutString;
	}

	public void setMoveOutString(String moveOutString) {
		this.moveOutString = moveOutString;
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

	public Renter() {
		super();
	}
	
	// Constructors

	public Renter(String formOfAddress, String firstName, String lastName, String telephone, String mobile,
			String eMail, String hasExtraAddress, double rent, double monthlyNkInAdvance, int noOfPeople, String moveInString, String moveOutString) {
		super(formOfAddress, firstName, lastName, telephone, mobile, eMail, hasExtraAddress);
		this.rent = rent;
		this.monthlyNkInAdvance = monthlyNkInAdvance;
		this.noOfPeople = noOfPeople;
		this.moveInString = moveInString;
		this.moveOutString = moveOutString;
	}


}

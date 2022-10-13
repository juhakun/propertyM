package de.muulti.spring.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import de.muulti.spring.pm.validation.InputValidation;
import de.muulti.spring.service.HouseService;
import de.muulti.spring.service.HouseServiceImpl;

@Entity
@Table(name = "Counter")
@Component("counter")
public class Counter extends HouseServiceImpl {

	@Id
	@Column(name = "counterNo")
	private String counterNo;

	@Column(name = "houseOrUnit")
	@NotNull(message = "Bitte treffen Sie eine Auswahl.")
	private String houseOrUnit;

	@Column(name = "type")
	@InputValidation(id = 2, value = "auswählen", message = "Bitte treffen Sie eine Auswahl.")
	private String type;

	@Column(name = "location")
	@InputValidation(id = 2, value = "auswählen", message = "Bitte treffen Sie eine Auswahl.")
	private String location;

	@Column(name = "status")
	private String status;

	@Column(name = "count")
	private int count;

	@Transient
	private LocalDate dateCount;

	@Transient
	private String dateCountString;

	@Column(name = "dateCount")
	private Date sqlDateCount;

	@Column(name = "recentlyReplaced")
	private String recentlyReplaced;

	@Transient
	private LocalDate dateReplacement;

	@Transient
	private String dateReplacementString;

	@Column(name = "dateReplacement")
	private Date sqldateReplacement;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "House_idHouse")
	private House house;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "Unit_idUnit")
	private Unit unit;

	@Column(name = "unitName")
	@InputValidation(id = 2, value = "auswählen", message = "Bitte treffen Sie eine Auswahl.")
	private String unitName;
	


	public String getCounterNo() {
		return counterNo;
	}

	public void setCounterNo(String counterNo) {
		this.counterNo = counterNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHouseOrUnit() {
		return houseOrUnit;
	}

	public void setHouseOrUnit(String houseOrUnit) {
		this.houseOrUnit = houseOrUnit;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int countOld) {
		this.count = countOld;
	}

	public LocalDate getDateCount() {
		return dateCount;
	}

	public void setDateCount(LocalDate dateCount) {
		this.dateCount = dateCount;
	}

	public String getDateCountString() {
		return dateCountString;
	}

	public void setDateCountString(String dateCountString) {
		this.dateCountString = dateCountString;
	}

	public Date getSqlDateCount() {
		return sqlDateCount;
	}

	public void setSqlDateCount(Date sqlDateCount) {
		this.sqlDateCount = sqlDateCount;
	}

	public String getRecentlyReplaced() {
		return recentlyReplaced;
	}

	public void setRecentlyReplaced(String recentlyReplaced) {
		this.recentlyReplaced = recentlyReplaced;
	}

	public LocalDate getDateReplacement() {
		return dateReplacement;
	}

	public void setDateReplacement(LocalDate dateReplacement) {
		this.dateReplacement = dateReplacement;
	}

	public String getDateReplacementString() {
		return dateReplacementString;
	}

	public void setDateReplacementString(String dateReplacementString) {
		this.dateReplacementString = dateReplacementString;
	}

	public Date getSqldateReplacement() {
		return sqldateReplacement;
	}

	public void setSqldateReplacement(Date sqldateReplacement) {
		this.sqldateReplacement = sqldateReplacement;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unit) {
		this.unitName = unit;
	}


}
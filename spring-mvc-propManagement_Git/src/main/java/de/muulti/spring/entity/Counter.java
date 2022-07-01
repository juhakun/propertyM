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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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
	private String houseOrUnit;

	@Column(name = "type")
	private String type;

	@Column(name = "location")
	private String location;

	@Column(name = "status")
	private String status;

	@Column(name = "countOld")
	private int countOld;

	@Column(name = "countNew")
	private int countNew;

	@Transient
	private LocalDate dateCountOld;

	@Transient
	private String dateCountOldString;

	@Column(name = "dateCountOld")
	private Date sqlDateCountOld;

	@Transient
	private LocalDate dateCountNew;

	@Transient
	private String dateCountNewString;

	@Column(name = "dateCountNew")
	private Date sqlDateCountNew;

	@Column(name = "recentlyReplaced")
	private boolean recentlyReplaced;

	@Transient
	private LocalDate dateReplacement;

	@Transient
	private String dateReplacementString;

	@Column(name = "dateReplacement")
	private Date sqldateReplacement;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "House_idHouse")
	private House house;

	@Column(name = "unitName")
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

	public int getCountOld() {
		return countOld;
	}

	public void setCountOld(int countOld) {
		this.countOld = countOld;
	}

	public int getCountNew() {
		return countNew;
	}

	public void setCountNew(int countNew) {
		this.countNew = countNew;
	}

	public LocalDate getDateCountOld() {
		return dateCountOld;
	}

	public void setDateCountOld(LocalDate dateCountOld) {
		this.dateCountOld = dateCountOld;
	}

	public String getDateCountOldString() {
		return dateCountOldString;
	}

	public void setDateCountOldString(String dateCountOldString) {
		this.dateCountOldString = dateCountOldString;
	}

	public Date getSqlDateCountOld() {
		return sqlDateCountOld;
	}

	public void setSqlDateCountOld(Date sqlDateCountOld) {
		this.sqlDateCountOld = sqlDateCountOld;
	}

	public LocalDate getDateCountNew() {
		return dateCountNew;
	}

	public void setDateCountNew(LocalDate dateCountNew) {
		this.dateCountNew = dateCountNew;
	}

	public String getDateCountNewString() {
		return dateCountNewString;
	}

	public void setDateCountNewString(String dateCountNewString) {
		this.dateCountNewString = dateCountNewString;
	}

	public Date getSqlDateCountNew() {
		return sqlDateCountNew;
	}

	public void setSqlDateCountNew(Date sqlDateCountNew) {
		this.sqlDateCountNew = sqlDateCountNew;
	}

	public boolean isRecentlyReplaced() {
		return recentlyReplaced;
	}

	public void setRecentlyReplaced(boolean recentlyReplaced) {
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

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unit) {
		this.unitName = unit;
	}

}
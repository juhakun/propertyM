package de.muulti.spring.entity;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component("counter")
public class Counter implements ObjectActions {

	private boolean houseCounter;
	private String unit;
	private String type;
	private String counterNo;
	private String location;
	private int countOld;
	private int countNew;
	private LocalDate dateCountOld;
	private LocalDate dateCountNew;
	private boolean recentlyReplaced;
	private LocalDate dateReplacement;
	private String unitMeasurement;

	public boolean isHouseCounter() {
		return houseCounter;
	}

	public void setHouseCounter(boolean houseCounter) {
		this.houseCounter = houseCounter;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCounterNo() {
		return counterNo;
	}

	public void setCounterNo(String counterNo) {
		this.counterNo = counterNo;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public LocalDate getDateCountNew() {
		return dateCountNew;
	}

	public void setDateCountNew(LocalDate dateCountNew) {
		this.dateCountNew = dateCountNew;
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

	public String getUnitMeasurement() {
		return unitMeasurement;
	}

	public void setUnitMeasurement(String unitMeasurement) {
		this.unitMeasurement = unitMeasurement;
	}

	@Override
	public void show(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void change(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object o) {
		// TODO Auto-generated method stub

	}

}

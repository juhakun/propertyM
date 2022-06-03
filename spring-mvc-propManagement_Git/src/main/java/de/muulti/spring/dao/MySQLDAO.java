package de.muulti.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import de.muulti.spring.service.HouseServiceImpl;

public interface MySQLDAO {

	public List<Object> getData(String dao);

	public void insertData(Object o);

	public void show(Object o);

	public void updateData(Object o);

	public void deleteData(Object o);

}

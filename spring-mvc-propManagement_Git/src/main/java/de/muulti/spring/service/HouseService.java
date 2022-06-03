package de.muulti.spring.service;

import java.util.List;

import de.muulti.spring.dao.DAOImpl;
import de.muulti.spring.dao.MySQLDAO;

public interface HouseService {
	
	public List<Object> getData(String entity);

	public void insertData(HouseServiceImpl h);

	public void show(HouseServiceImpl h);

	public void updateData(HouseServiceImpl h);

	public void deleteData(HouseServiceImpl h);

}

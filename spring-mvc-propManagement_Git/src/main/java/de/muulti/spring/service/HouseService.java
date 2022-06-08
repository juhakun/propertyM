package de.muulti.spring.service;

import java.util.List;

import de.muulti.spring.dao.DAOImpl;
import de.muulti.spring.dao.MySQLDAO;
import de.muulti.spring.entity.House;

public interface HouseService {
	
	public List<Object> getSelectedData(String select);
	
	public HouseServiceImpl getObject(String select);

	public void saveData(HouseServiceImpl h);

	public void updateData(String update);

	public void deleteData(HouseServiceImpl h);

}

package de.muulti.spring.service;

import java.util.List;

import de.muulti.spring.dao.DAOImpl;
import de.muulti.spring.dao.MySQLDAO;
import de.muulti.spring.entity.House;

public interface HouseService {
	
	public List<HouseServiceImpl> getSelectedData(String select);
	
	public HouseServiceImpl getObject(String select);
	
	public HouseServiceImpl getObjectByID(Class<?> objectClass, int id);

	public void saveData(HouseServiceImpl h);

	public void updateData(String update);

	public void deleteData(Class<?> objectClass, int id);
	
	public boolean checkForDuplicatesByID(String select, int id);

	

}

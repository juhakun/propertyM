package de.muulti.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import de.muulti.spring.entity.House;
import de.muulti.spring.service.HouseServiceImpl;

public interface MySQLDAO {

	public List<HouseServiceImpl> getSelectedData(String select);
	
	public HouseServiceImpl getObject(String select);
	
	public HouseServiceImpl getObjectByID(Class<?> objectClass, int id);

	public void saveData(HouseServiceImpl o);

	public void updateData(String update);

	public void deleteData(Class<?> objectClass, int id);
	
	public boolean checkForDuplicatesByID(String select, int id);

}

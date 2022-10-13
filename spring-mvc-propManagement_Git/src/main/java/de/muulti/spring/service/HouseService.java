package de.muulti.spring.service;

import java.util.List;

import de.muulti.spring.dao.DAOImpl;
import de.muulti.spring.dao.MySQLDAO;
import de.muulti.spring.entity.Counter;
import de.muulti.spring.entity.House;
import de.muulti.spring.entity.Unit;

public interface HouseService  {
	
	public List<HouseServiceImpl> getSelectedData(String select);
	
	public HouseServiceImpl getObject(String select);
	
	public HouseServiceImpl getObjectByID(Class<?> objectClass, int id);

	public void saveData(HouseServiceImpl h);

	public void deleteData(Class<?> objectClass, int id);
	
	public int [] checkForDuplicatesByID(String select,  HouseServiceImpl h);
	
	public HouseServiceImpl getDuplicate(String select, HouseServiceImpl h);

	public void addUnit(int idHouse, Unit unit);
	
	public void addCounter(int idHouse, Counter counter);

}

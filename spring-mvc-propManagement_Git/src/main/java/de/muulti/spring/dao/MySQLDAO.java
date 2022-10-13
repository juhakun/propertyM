package de.muulti.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import de.muulti.spring.entity.Counter;
import de.muulti.spring.entity.House;
import de.muulti.spring.entity.Unit;
import de.muulti.spring.service.HouseServiceImpl;

public interface MySQLDAO {

	public List<HouseServiceImpl> getSelectedData(String select);

	public HouseServiceImpl getObject(String select);

	public HouseServiceImpl getObjectByID(Class<?> objectClass, int id);

	public void saveData(HouseServiceImpl o);

	public void deleteData(Class<?> objectClass, int id);
	
	public int [] checkForDuplicatesByID(String select, HouseServiceImpl h);

	public HouseServiceImpl getDuplicate(String select, HouseServiceImpl h);

	public void addUnit(int idHouse, Unit unit);
	
	public void addCounter(int idHouse, Counter counter);
}

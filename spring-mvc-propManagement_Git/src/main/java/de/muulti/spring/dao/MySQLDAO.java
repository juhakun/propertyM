package de.muulti.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import de.muulti.spring.entity.House;
import de.muulti.spring.service.HouseServiceImpl;

public interface MySQLDAO {

	public List<Object> getSelectedData(String select);
	
	public Object getObject(String select);

	public void saveData(Object o);

	public void updateData(String update);

	public void deleteData(Object o);

}

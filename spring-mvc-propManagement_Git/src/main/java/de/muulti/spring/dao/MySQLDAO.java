package de.muulti.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;


public interface MySQLDAO {

//	List<DAOImpl> getData();

	public List<DAOImpl> getData(String dao);

//	public void saveObjects();

	void saveObject(DAOImpl o);
	
	public void show(DAOImpl o);

	public void change(DAOImpl o);

	public void delete(DAOImpl o);

}

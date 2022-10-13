package de.muulti.spring.service;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.muulti.spring.dao.DAOImpl;
import de.muulti.spring.dao.MySQLDAO;
import de.muulti.spring.entity.Counter;
import de.muulti.spring.entity.House;
import de.muulti.spring.entity.Unit;

@Service(value = "HouseServiceImpl")
public class HouseServiceImpl implements HouseService {

	@Autowired
	private MySQLDAO dao;

	@Override
	@Transactional
	public List<HouseServiceImpl> getSelectedData(String select) {
		return dao.getSelectedData(select);
	}

	@Override
	@Transactional
	public HouseServiceImpl getObject(String select) {
		return (HouseServiceImpl) dao.getObject(select);
	}

	@Override
	@Transactional
	public HouseServiceImpl getObjectByID(Class<?> objectClass, int id) {
		return (HouseServiceImpl) dao.getObjectByID(objectClass, id);
	}

	@Override
	@Transactional
	public void saveData(HouseServiceImpl h) {
		dao.saveData(h);

	}

	@Override
	@Transactional
	public void deleteData(Class<?> objectClass, int id) {
		dao.deleteData(objectClass, id);
	}	

	@Override
	@Transactional
	public int[] checkForDuplicatesByID(String select, HouseServiceImpl h) {
		return dao.checkForDuplicatesByID(select, h);

	}

	@Override
	@Transactional
	public HouseServiceImpl getDuplicate(String select, HouseServiceImpl h) {
		return dao.getDuplicate(select, h);
	}

	@Override
	@Transactional
	public void addUnit(int idHouse, Unit unit) {
		dao.addUnit(idHouse, unit);

	}

	@Override
	@Transactional
	public void addCounter(int idHouse, Counter counter) {
		dao.addCounter(idHouse, counter);

	}

}

package de.muulti.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.muulti.spring.dao.DAOImpl;
import de.muulti.spring.dao.MySQLDAO;

@Service(value="HouseServiceImpl")
public class HouseServiceImpl implements HouseService {
	
	@Autowired
	private MySQLDAO dao;

	@Override
	@Transactional
	public List<Object> getData(String entity) {
		return dao.getData(entity);
	}

	@Override
	@Transactional
	public void insertData(HouseServiceImpl h) {
		dao.insertData(h);

	}

	@Override
	@Transactional
	public void show(HouseServiceImpl h) {
		dao.show(h);

	}

	@Override
	@Transactional
	public void updateData(HouseServiceImpl h) {
		dao.updateData(h);

	}

	@Override
	@Transactional
	public void deleteData(HouseServiceImpl h) {
		dao.deleteData(h);

	}

}

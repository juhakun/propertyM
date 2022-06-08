package de.muulti.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.muulti.spring.dao.DAOImpl;
import de.muulti.spring.dao.MySQLDAO;
import de.muulti.spring.entity.House;

@Service(value="HouseServiceImpl")
public class HouseServiceImpl implements HouseService {
	
	@Autowired
	private MySQLDAO dao;

	@Override
	@Transactional
	public List<Object> getSelectedData(String select) {
		return dao.getSelectedData(select);
	}
	
	@Override
	@Transactional
	public HouseServiceImpl getObject(String select) {
		return (HouseServiceImpl) dao.getObject(select);
	}

	@Override
	@Transactional
	public void saveData(HouseServiceImpl h) {
		dao.saveData(h);

	}

	@Override
	@Transactional
	public void updateData(String update) {
		dao.updateData(update);

	}

	@Override
	@Transactional
	public void deleteData(HouseServiceImpl h) {
		dao.deleteData(h);

	}

//	@Override
//	@Transactional
//	public House getHouse(String id) {
//		return dao.getHouse(id);
//	}






}

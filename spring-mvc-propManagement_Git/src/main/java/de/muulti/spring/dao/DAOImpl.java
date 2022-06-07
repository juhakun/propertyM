package de.muulti.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.muulti.spring.entity.House;
import de.muulti.spring.service.HouseServiceImpl;

@Repository
public class DAOImpl implements MySQLDAO {

	// inject session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Object> getSelectedData(String select) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// execute query and get result list
		List<Object> tableObjects = currentSession.createQuery(select).getResultList();

		// return the results
		return tableObjects;
	}

	@Override
	public Object getObject(String select) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// execute query and get result list
		Object theObject = currentSession.createQuery(select).getSingleResult();

		// return the results
		return theObject;
	}

	@Override
	public void insertData(Object o) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// save/update the object
		System.out.println("Saving new object...");
		currentSession.saveOrUpdate(o);

	}

	@Override
	public void show(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateData(String update) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// execute update
		currentSession.createQuery(update).executeUpdate();

	}

	@Override
	public void deleteData(Object o) {
		// TODO Auto-generated method stub

	}

//	@Override
//	public House getHouse(String id) {
//		// get the current hibernate session
//		Session currentSession = sessionFactory.getCurrentSession();
//
//		// execute query and get result list
//		House theHouse = currentSession.get(House.class, id);
//
//		// return the results
//		return theHouse;
//	}

}

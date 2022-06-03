package de.muulti.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.muulti.spring.service.HouseServiceImpl;

@Repository
public class DAOImpl implements MySQLDAO {

	// inject session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Object> getData(String dao) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<Object> theQuery = currentSession.createQuery("from " + dao, Object.class);

		// execute query and get result list
		List<Object> daoObjects = theQuery.getResultList();

		// return the results
		return daoObjects;
	}

	@Override
	@Transactional
	public void insertData(Object o) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save the object
		System.out.println("Saving new object...");
		currentSession.save(o);

	}

	@Override
	public void show(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateData(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteData(Object o) {
		// TODO Auto-generated method stub
		
	}


}

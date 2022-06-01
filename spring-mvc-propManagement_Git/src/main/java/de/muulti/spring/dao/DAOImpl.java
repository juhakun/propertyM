package de.muulti.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DAOImpl implements MySQLDAO {

	// inject session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<DAOImpl> getData(String dao) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<DAOImpl> theQuery = currentSession.createQuery("from " + dao, DAOImpl.class);

		// execute query and get result list
		List<DAOImpl> daoObjects = theQuery.getResultList();

		// return the results
		return daoObjects;
	}

	@Override
	@Transactional
	public void saveObject(DAOImpl dao) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save the object
		System.out.println("Saving new object...");
		currentSession.save(dao);

	}

	@Override
	public void show(DAOImpl o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void change(DAOImpl o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(DAOImpl o) {
		// TODO Auto-generated method stub
		
	}


}

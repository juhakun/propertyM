package de.muulti.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Object;

import de.muulti.spring.entity.House;
import de.muulti.spring.service.HouseServiceImpl;

@Repository
public class DAOImpl implements MySQLDAO {

	// inject session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<HouseServiceImpl> getSelectedData(String select) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// execute query and get result list
		List<HouseServiceImpl> tableObjects = currentSession.createQuery(select).getResultList();

		// return the results
		return tableObjects;
	}

	@Override
	public HouseServiceImpl getObject(String select) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// execute query and get single result
		HouseServiceImpl theObject = (HouseServiceImpl) currentSession.createQuery(select).getSingleResult();

		// return the results
		return theObject;
	}

	@Override
	public HouseServiceImpl getObjectByID(Class<?> objectClass, int id) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// get the Object
		HouseServiceImpl theObject = (HouseServiceImpl) currentSession.get(objectClass, id);

		return theObject;
	}

	@Override
	public void saveData(HouseServiceImpl o) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// save/update the object
		System.out.println("Saving new object...");
		currentSession.saveOrUpdate(o);

	}

	@Override
	public void updateData(String update) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// execute update
		currentSession.createQuery(update).executeUpdate();

	}

	@Override
	public void deleteData(Class<?> objectClass, int id) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// get the Object
		HouseServiceImpl theObject = (HouseServiceImpl) currentSession.get(objectClass, id);

		// delete the Object
		currentSession.delete(theObject);

	}

	@Override
	public boolean checkForDuplicatesByID(String selectList, int id) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// get a list of objects by select
		boolean duplicateID = false;
		List<Integer> allIDs =  currentSession.createQuery(selectList).list();
		System.out.println(allIDs);
		for (Integer i : allIDs) {
			if(i.intValue() == id) {
				duplicateID = true;
			} 
		}
		return duplicateID;
		
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

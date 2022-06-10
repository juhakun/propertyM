package de.muulti.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Object;

import de.muulti.spring.entity.Address;
import de.muulti.spring.entity.House;
import de.muulti.spring.entity.Owner;
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
	public int checkForDuplicatesByID(String select, Class<?> objectClass, int id) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// get a list of objects by select
		int duplicateID = 0;
		List<HouseServiceImpl> allObjects = currentSession.createQuery(select).getResultList();

		// iterate over list and check for each item if id exists
		for (HouseServiceImpl h : allObjects) {
			House theHouse = (House) h;
			if (objectClass == Owner.class) {
				if (theHouse.getOwner().getIdPerson() == id) {
					duplicateID++;
				}
			} else if (objectClass == Address.class) {
				if (theHouse.getAddress().getIdAddress() == id
						|| theHouse.getOwner().getOwnerAddress().getIdAddress() == id) {
					duplicateID++;
				}
			}

		}
		return duplicateID;

	}

	@Override
	public HouseServiceImpl getDuplicate(String select, HouseServiceImpl h) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		HouseServiceImpl duplicate = null;

		List<HouseServiceImpl> allObjects = currentSession.createQuery(select).getResultList();

		if (h instanceof House) {
			House theNewHouse = (House) h;

			for (HouseServiceImpl i : allObjects) {
				House theOldHouse = (House) i;
				if (theOldHouse.getObjectName().equals(theNewHouse.getObjectName())) {
					duplicate = theOldHouse;
				}
			}
				
				
		}
		if (h instanceof Owner) {
			Owner theNewOwner = (Owner) h;
			for (HouseServiceImpl i : allObjects) {
				Owner theOldOwner = (Owner) i;
				if (theOldOwner.getFirstName().equals(theNewOwner.getFirstName())
						&& theOldOwner.getLastName().equals(theNewOwner.getLastName())) {
					duplicate =  theOldOwner;
		
				}

			}

		} 
		if (h instanceof Address) {
			Address theNewAddress = (Address) h;
			for (HouseServiceImpl i : allObjects) {
				Address theOldAddress = (Address) i;
				if (theOldAddress.getStreet().equals(theNewAddress.getStreet())
						&& theOldAddress.getHouseNo().equals(theNewAddress.getHouseNo())
						&& theOldAddress.getPostalCode().equals(theNewAddress.getPostalCode())) {
					duplicate =  theOldAddress;
				}
			}

		}
		return duplicate;
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

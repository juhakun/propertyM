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
	public int[] checkForDuplicatesByID(String select, HouseServiceImpl h) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// get a list of objects by select
		int[] duplicates = new int[3];
		List<HouseServiceImpl> allObjects = currentSession.createQuery(select).getResultList();

		// iterate over list and check for each item if id exists
		for (HouseServiceImpl i : allObjects) {
			if (i instanceof House) {
				House theOldHouse = (House) i;
				if (h instanceof Address) {
					Address theNewAddress = (Address) h;
					Address theOldAddress = theOldHouse.getAddress();
					if (theOldAddress.getIdAddress() == theNewAddress.getIdAddress()) {
						duplicates[0]++;
					}
				}
				if (h instanceof Owner) {
					Owner theNewOwner = (Owner) h;
					Owner theOldOwner = theOldHouse.getOwner();
					if (theOldOwner.getIdPerson() == theNewOwner.getIdPerson()) {
						duplicates[1]++;
					}
				}
			}
			if (i instanceof Owner) {
				Owner theOldOwner = (Owner) i;
				Address theNewOwnerAddress = (Address) h;
				if (theOldOwner.getOwnerAddress().getIdAddress() == theNewOwnerAddress.getIdAddress()) {
					duplicates[2]++;
				}
			}
		}
		return duplicates;

	}

	@Override
	public HouseServiceImpl getDuplicate(String select, HouseServiceImpl h) {
		Session currentSession = sessionFactory.getCurrentSession();

		HouseServiceImpl duplicate = null;

		List<HouseServiceImpl> allObjects = currentSession.createQuery(select).getResultList();
		for (HouseServiceImpl i : allObjects) {
			if (i instanceof House) {
				House theOldHouse = (House) i;
				if (h instanceof House) {
					House theNewHouse = (House) h;
					if (theOldHouse.getObjectName().equals(theNewHouse.getObjectName())) {
						duplicate = theOldHouse;
					}
				}

				if (h instanceof Owner) {
					Owner theNewOwner = (Owner) h;
					Owner theOldOwner = theOldHouse.getOwner();
					if (theOldOwner.getFirstName().equals(theNewOwner.getFirstName())
							&& theOldOwner.getLastName().equals(theNewOwner.getLastName())) {
						duplicate = theOldOwner;
					}
				}

				if (h instanceof Address) {
					Address theNewAddress = (Address) h;
					Address theOldAddress = theOldHouse.getAddress();
							if (theOldAddress.getStreet().equals(theNewAddress.getStreet())
									&& theOldAddress.getHouseNo().equals(theNewAddress.getHouseNo())
									&& theOldAddress.getPostalCode().equals(theNewAddress.getPostalCode())) {
								duplicate = theOldAddress;
							}
				}

			}
			if (i instanceof Address) {
				Address theNewAddress = (Address) h;
				Address theOldAddress = (Address) i;
						if (theOldAddress.getStreet().equals(theNewAddress.getStreet())
								&& theOldAddress.getHouseNo().equals(theNewAddress.getHouseNo())
								&& theOldAddress.getPostalCode().equals(theNewAddress.getPostalCode())) {
							duplicate = theOldAddress;
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

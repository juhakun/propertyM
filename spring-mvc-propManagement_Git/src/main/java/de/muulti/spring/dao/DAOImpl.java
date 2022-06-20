package de.muulti.spring.dao;

import java.util.ArrayList;
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
import de.muulti.spring.entity.Unit;
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
		return tableObjects;
	}

	@Override
	public HouseServiceImpl getObject(String select) {
		Session currentSession = sessionFactory.getCurrentSession();

		// execute query and get single result and return it
		HouseServiceImpl theObject = (HouseServiceImpl) currentSession.createQuery(select).getSingleResult();
		return theObject;
	}

	@Override
	public HouseServiceImpl getObjectByID(Class<?> objectClass, int id) {
		Session currentSession = sessionFactory.getCurrentSession();

		// get the Object and return it
		HouseServiceImpl theObject = (HouseServiceImpl) currentSession.get(objectClass, id);
		return theObject;
	}

	@Override
	public void saveData(HouseServiceImpl o) {
		Session currentSession = sessionFactory.getCurrentSession();

		// save/update the object
		System.out.println("Saving new object...");
		currentSession.saveOrUpdate(o);

	}

	@Override
	public void updateData(String update) {
		Session currentSession = sessionFactory.getCurrentSession();

		// execute update
		currentSession.createQuery(update).executeUpdate();

	}

	@Override
	public void deleteData(Class<?> objectClass, int id) {
		Session currentSession = sessionFactory.getCurrentSession();

		// get the Object
		HouseServiceImpl theObject = (HouseServiceImpl) currentSession.get(objectClass, id);

		// delete the Object
		if (theObject instanceof House)
			((House) theObject).setStatus("deleted");

	}

	@Override
	public int[] checkForDuplicatesByID(String select, HouseServiceImpl h) {
		Session currentSession = sessionFactory.getCurrentSession();

		// get a list of objects by select
		List<HouseServiceImpl> allObjects = currentSession.createQuery(select).getResultList();

		// set array with return value
		int[] duplicates = new int[3];
		// duplicates[0] stores number of address duplicates
		// duplicates[1] stores number of owner duplicates
		// duplicates[2] stores number of owner address duplicates

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
				Address theOldOwnerAddress = theOldOwner.getAddress();
				Address theNewOwnerAddress = (Address) h;
				if (theOldOwnerAddress != null && theNewOwnerAddress != null) {
					if (theOldOwnerAddress.getIdAddress() == theNewOwnerAddress.getIdAddress()) {
						duplicates[2]++;
					}
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

//				if (h instanceof Owner) {
//					Owner theNewOwner = (Owner) h;
//					Owner theOldOwner = theOldHouse.getOwner();
//					if (theOldOwner.getFirstName().equals(theNewOwner.getFirstName())
//							&& theOldOwner.getLastName().equals(theNewOwner.getLastName())) {
//						duplicate = theOldOwner;
//					}
//				}
//
//				if (h instanceof Address) {
//					Address theNewAddress = (Address) h;
//					Address theOldAddress = theOldHouse.getAddress();
//							if (theOldAddress.getStreet().equals(theNewAddress.getStreet())
//									&& theOldAddress.getHouseNo().equals(theNewAddress.getHouseNo())
//									&& theOldAddress.getPostalCode().equals(theNewAddress.getPostalCode())) {
//								duplicate = theOldAddress;
//							}
//				}

			}
			if (i instanceof Address) {
				Address theNewAddress = (Address) h;
				Address theOldAddress = (Address) i;
				if (theOldAddress.getStreet().equals(theNewAddress.getStreet())
						&& theOldAddress.getHouseNo().equals(theNewAddress.getHouseNo())
						&& theOldAddress.getPostalCode().equals(theNewAddress.getPostalCode())
						&& theOldAddress.getCity().equals(theNewAddress.getCity())) {
					duplicate = theOldAddress;
				}
			}
			if (i instanceof Owner) {
				Owner theNewOwner = (Owner) h;
				Owner theOldOwner = (Owner) i;
				if (theOldOwner.getFirstName().equals(theNewOwner.getFirstName())
						&& theOldOwner.getLastName().equals(theNewOwner.getLastName())) {
					duplicate = theOldOwner;
				}
			}
		}
		return duplicate;
	}

	@Override
	public void addUnit(int idHouse, Unit unit) {
		Session currentSession = sessionFactory.getCurrentSession();

		// get the house
		House theHouse = (House) currentSession.get(House.class, idHouse);
		List <Unit> theUnits;

		if (theHouse.getUnits() == null) {
			theUnits = new ArrayList<>();
			theUnits.add(unit);
			System.out.println(theUnits.size());
			this.saveData(unit);
//			this.saveData(theHouse);
			
		} else {
			theUnits = theHouse.getUnits();	
			if (theUnits.size() < theHouse.getNoOfUnits()) {
				theUnits.add(unit);
				System.out.println(theUnits.size());
				unit.setHouse(theHouse);
				this.saveData(unit);
//				this.saveData(theHouse);
			} else {
				System.out.println("Schon alle Wohneinheiten angelegt");
				// TODO: ADD MESSAGE
			}
		}
	}

}

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
import de.muulti.spring.entity.Person;
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
		int[] duplicates = new int[4];
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
						if (duplicates[0] > 1) {
							System.out.println("Address used by other house");
						}
					}
				}
				if (h instanceof Person) {
					Person theNewOwner = (Person) h;
					Person theOldOwner = theOldHouse.getOwner();
					if (theOldOwner.getIdPerson() == theNewOwner.getIdPerson()) {
						duplicates[1]++;
						if (duplicates[1] > 1) {
							System.out.println("Owner used by other house");
						}
					}
				}
			}
			if (i instanceof Person) {
				Person theOldOwner = (Person) i;
				if (h instanceof Address) {
					Address theOldOwnerAddress = theOldOwner.getAddress();
					Address theNewOwnerAddress = (Address) h;
				if (theOldOwnerAddress != null && theNewOwnerAddress != null) {
					if (theOldOwnerAddress.getIdAddress() == theNewOwnerAddress.getIdAddress()) {
						duplicates[2]++;
						if (duplicates[2] > 1) {
							System.out.println("Address used by other person");
						}
						}
					}
				}
				if (h instanceof Person) {
					Person theNewOwner = (Person) h;
					if (theOldOwner.getFirstName().equals(theNewOwner.getFirstName())
							&& theOldOwner.getLastName().equals(theNewOwner.getLastName())) {
						duplicates[2]++;
						if (duplicates[2] > 1) {
							System.out.println("Owner exists");
						}
					}
				}
			}
			if (i instanceof Address) {
				Address theSavedAddress = (Address) i;
				Address theNewAddress = (Address) h;
				if (theSavedAddress.getStreet().equals(theNewAddress.getStreet())
						&& theSavedAddress.getHouseNo().equals(theNewAddress.getHouseNo())
						&& theSavedAddress.getPostalCode().equals(theNewAddress.getPostalCode())
						&& theSavedAddress.getCity().equals(theNewAddress.getCity())) {
					duplicates[3]++;
					if (duplicates[3] > 1) {
						System.out.println("Address exists");
					}
				}
			}
		}
		return duplicates;

	}

	@Override
	public HouseServiceImpl getDuplicate(String select, HouseServiceImpl h) {
		Session currentSession = sessionFactory.getCurrentSession();

		HouseServiceImpl theObject = null;

		List<HouseServiceImpl> allObjects = currentSession.createQuery(select).getResultList();
		for (HouseServiceImpl i : allObjects) {
			if (i instanceof House) {
				House theSavedHouse = (House) i;
				if (h instanceof House) {
					House theNewHouse = (House) h;
					if (theSavedHouse.getObjectName().equals(theNewHouse.getObjectName())) {
						System.out.println("House Name exists");
						theObject = theSavedHouse;
					} else {
						theObject = null;
					}
				}

			}
			if (i instanceof Address) {
				Address theNewAddress = (Address) h;
				Address theSavedAddress = (Address) i;
				if (theSavedAddress.getStreet().equals(theNewAddress.getStreet())
						&& theSavedAddress.getHouseNo().equals(theNewAddress.getHouseNo())
						&& theSavedAddress.getPostalCode().equals(theNewAddress.getPostalCode())
						&& theSavedAddress.getCity().equals(theNewAddress.getCity())) {
					System.out.println("Address exists");
					theSavedAddress.setStatus(null);
					theObject = theSavedAddress;
					break;
				} else {
					theObject = theNewAddress;
				}
			}
			if (i instanceof Person) {
				Person theNewPerson = (Person) h;
				Person theSavedPerson = (Person) i;
				if (theSavedPerson.getFirstName().equals(theNewPerson.getFirstName())
						&& theSavedPerson.getLastName().equals(theNewPerson.getLastName())) {
					System.out.println("Person exists");
					theSavedPerson.setStatus(null);
					theObject = theSavedPerson;
					break;

				} else {
					theObject = theNewPerson;
				}
			}
		}
		return theObject;
	}

	@Override
	public void addUnit(int idHouse, Unit unit) {
		Session currentSession = sessionFactory.getCurrentSession();

		// get the house
		House theHouse = (House) currentSession.get(House.class, idHouse);
		List<Unit> theUnits;

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

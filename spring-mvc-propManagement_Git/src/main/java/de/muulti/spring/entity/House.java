package de.muulti.spring.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.muulti.spring.dao.DAOImpl;
import de.muulti.spring.service.HouseServiceImpl;

@Entity
@Table(name = "House")
@Component("house")
public class House extends HouseServiceImpl {

	public static int counter = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idHouse")
	private int idHouse;

	@Column(name = "objectName")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	@Size(min = 1, message = "Bitte überprüfen Sie Ihre Angaben.")
	@Pattern(regexp = "^[a-zA-Z0-9_äÄöÖüÜß\s]*$", message = "Bitte überprüfen Sie den Objektnamen auf nicht erlaubte Sonderzeichen.")
	private String objectName;

	@Column(name = "totalArea")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	@Min(value = 20, message = "Bitte überprüfen Sie die Gesamtwohnfläche Ihres Objekts")
	@Max(value = 500, message = "Bitte überprüfen Sie die Gesamtfläche Ihres Objekts")
	private double totalAreaM2;

	@Column(name = "noOfUnits")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	@Min(value = 1, message = "Ihr Objekt muss mindestesn eine Wohneinheit haben.")
	@Max(value = 10, message = "Ihr Objekt überschreitet die maximal erlaubte Anzahl an Wohneinheiten.")
	private int noOfUnits;

	@Column(name = "status")
	private String status;

	// ONE house can only have ONE address and this address cannot belong to another
	// house
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "Address_idAddress")
	private Address address;

	// ONE house can only have ONE owner (at least for this program)
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "Owner_idPerson")
	private Person owner;

	// ONE house can have MANY renters
	@OneToMany(mappedBy = "house", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	private List<Renter> renters;

	// ONE house can have MANY units
	@OneToMany(mappedBy = "house", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	private List<Unit> units;

	// ONE house can have MANY counters
	@OneToMany(mappedBy = "house", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	private List<Counter> counters;
	
	@Transient
	private String tableSort;

	public int getIdHouse() {
		return idHouse;
	}

	public void setIdHouse(int idHouse) {
		this.idHouse = idHouse;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public Double getTotalAreaM2() {
		return totalAreaM2;
	}

	public void setTotalAreaM2(double totalAreaM2) {
		this.totalAreaM2 = totalAreaM2;
	}

	public int getNoOfUnits() {
		return noOfUnits;
	}

	public void setNoOfUnits(int noOfUnits) {
		this.noOfUnits = noOfUnits;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Renter> getRenters() {
		return renters;
	}

	public void setRenters(List<Renter> renters) {
		this.renters = renters;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}

	public List<Counter> getCounters() {
		return counters;
	}

	public void setCounters(List<Counter> counters) {
		this.counters = counters;
	}

	public  String getTableSort() {
		return tableSort;
	}

	public  void setTableSort(String tableSort) {
		this.tableSort = tableSort;
	}

}

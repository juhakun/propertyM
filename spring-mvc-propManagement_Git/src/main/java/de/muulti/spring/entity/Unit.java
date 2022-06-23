package de.muulti.spring.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;

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

import org.springframework.stereotype.Component;

import de.muulti.spring.service.HouseServiceImpl;

@Entity
@Table(name = "Unit")
@Component("unit")
public class Unit extends HouseServiceImpl {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUnit")
	private int idUnit;

	@Column(name="unitName")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	@Size(min = 2, message = "Bitte überprüfen Sie Ihre Angaben.")
	@Pattern(regexp = "^[a-zA-Z0-9\s]*$", message = "Bitte überprüfen Sie den Namen auf nicht erlaubte Sonderzeichen.")
	private String unitName;
	
	@Column(name="size")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	@Min(value = 15, message = "Bitte überprüfen Sie die Gesamtwohnfläche der Wohneinheit")
	@Max(value = 250, message = "Bitte überprüfen Sie die Gesamtwohnfläche der Wohneinheit")
	private double sizeM2;
	
	@Column(name="noOfRooms")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	@Min(value = 1, message = "Bitte überprüfen Sie die Zimmeranzahl")
	@Max(value = 8, message = "Bitte überprüfen Sie die Zimmeranzahl")
	private double noOfRooms;
	
	@Column(name="floor")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	private String floor;
	
	@Transient
	private LinkedHashMap<String, String> floors;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "Person_idPerson")
	private Renter renter;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "House_idHouse")
	private House house;
	
	@Column(name = "status")
	private String status;
	
//	@OneToMany
//	private ArrayList<Counter> unitCounters = new ArrayList<>();

	public int getIdUnit() {
		return idUnit;
	}

	public void setIdUnit(int idUnit) {
		this.idUnit = idUnit;
	}
	
	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String uniTName) {
		this.unitName = uniTName;
	}

	public Renter getRenter() {
		return renter;
	}

	public void setRenter(Person renter) {
		this.renter = (Renter) renter;
	}
	
	public void setRenter(Renter renter) {
		this.renter =  renter;
	}

	public double getSizeM2() {
		return sizeM2;
	}

	public void setSizeM2(double sizeM2) {
		this.sizeM2 = sizeM2;
	}

	public double getNoOfRooms() {
		return noOfRooms;
	}

	public void setNoOfRooms(double noOfRooms) {
		this.noOfRooms = noOfRooms;
	}

	
	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public LinkedHashMap<String, String> getFloors() {
		return floors;
	}

//	public ArrayList<Counter> getUnitCounters() {
//		return unitCounters;
//	}
//
//	public void setUnitCounters(ArrayList<Counter> unitCounters) {
//		this.unitCounters = unitCounters;
//	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}
	


	public Unit() {
		floors = new LinkedHashMap<>();
		floors.put("ST", "Souterrain");		
		floors.put("EG", "Erdgeschoss");
		floors.put("1. OG", "1. Obergeschoss");
		floors.put("2. OG", "2. Obergeschoss");
		floors.put("3. OG", "3. Obergeschoss");
		floors.put("DG", "Dachgeschoss");
		
	}

}

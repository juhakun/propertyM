package de.muulti.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import de.muulti.spring.dao.DAOImpl;

@Entity
@Table(name = "address")
@Component("address")
public class Address  {

	public static int counter = 0;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idAddress")
	private int idAddress;

	@Column(name = "street")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	@Size(min = 4, message = "Bitte überprüfen Sie Ihre Angaben.")
	@Pattern(regexp = "^[a-zA-Z0-9\s]*$", message = "Bitte überprüfen Sie den Straßennamen auf nicht erlaubte Sonderzeichen.")
	private String street;

	@Column(name = "houseNo")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	@Size(min = 1, message = "Bitte überprüfen Sie Ihre Angaben.")
	@Pattern(regexp = "^[a-zA-Z0-9\s]*$", message = "Bitte überprüfen Sie den Hausnummer auf nicht erlaubte Sonderzeichen.")
	private String houseNo;

	@Column(name = "plz")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	@Pattern(regexp = "^[0-9]{5}", message = "Bitte überprüfen Sie Ihre Postleitzahl auf nicht erlaubte Sonderzeichen.")
	private String postalCode;

	@Column(name = "city")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	@Size(min = 1, message = "Bitte überprüfen Sie Ihre Angaben.")
	@Pattern(regexp = "^[a-zA-Z\s]*$", message = "Bitte überprüfen Sie den Städtenamen auf nicht erlaubte Sonderzeichen.")
	private String city;

	public int getIdAddress() {
		return idAddress;
	}
	
	public void setIdAddress(int idAddress) {
		this.idAddress = idAddress;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	// Constructors
	public Address() {
		
	}
	
	public Address(int id) {
		idAddress = id;
	}

	public Address(String street, String houseNo, String postalCode, String city, String telephone, String mobile,
			String eMail) {
		this.street = street;
		this.houseNo = houseNo;
		this.postalCode = postalCode;
		this.city = city;

	}

	@Override
	public String toString() {
		return "Address [idAddress=" + idAddress + ", street=" + street + ", houseNo=" + houseNo + ", postalCode="
				+ postalCode + ", city=" + city + "]";
	}

}

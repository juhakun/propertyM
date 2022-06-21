package de.muulti.spring.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import de.muulti.spring.dao.DAOImpl;
import de.muulti.spring.service.HouseServiceImpl;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "isOwner")
@DiscriminatorValue("true")
@Table(name = "Person")
@Component("person")
public class Person extends HouseServiceImpl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPerson")
	private int idPerson;

	@Column(name = "formOfAddress")
//	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	private String formOfAddress;

	@Column(name = "firstName")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	@Size(min = 1, message = "Bitte überprüfen Sie Ihre Angaben.")
	@Pattern(regexp = "^[a-zA-Z0-9_äÄöÖüÜß.\s]*$", message = "Bitte überprüfen Sie den Vornamen auf nicht erlaubte Sonderzeichen.")
	private String firstName;

	@Column(name = "lastName")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	@Size(min = 1, message = "Bitte überprüfen Sie Ihre Angaben.")
	@Pattern(regexp = "^[a-zA-Z0-9_äÄöÖüÜß\s]*$", message = "Bitte überprüfen Sie den Nachnamen auf nicht erlaubte Sonderzeichen.")
	private String lastName;

	@Column(name = "telephone")
//	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	private String telephone;

	@Column(name = "mobile")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	private String mobile;

	@Column(name = "eMail")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	@Size(min = 1, message = "Bitte überprüfen Sie Ihre Angaben.")
	@Pattern(regexp = "^[a-zA-Z0-9@.\s]*$", message = "Bitte überprüfen Sie die E-Mail-Adresse auf nicht erlaubte Sonderzeichen.")
	private String eMail;

	@Column(name = "isRenter")
	protected String isRenter = "false";
	
	@Transient
	private String isNew = "false";

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "Address_idAddress")
	private Address address;

	@Column(name = "hasExtraAddress")
	private String hasExtraAddress = "false";

	@Column(name = "status")
	private String status;

	public int getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public String getFormOfAddress() {
		return formOfAddress;
	}

	public void setFormOfAddress(String formOfAddress) {
		this.formOfAddress = formOfAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getIsRenter() {
		return isRenter;
	}

	public void setIsRenter(String isRenter) {
		this.isRenter = isRenter;
	}
	
	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address ownerAddress) {
		this.address = ownerAddress;
	}

	public String getHasExtraAddress() {
		return hasExtraAddress;
	}

	public void setHasExtraAddress(String hasExtraAddress) {
		this.hasExtraAddress = hasExtraAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Person() {
		
	}
	
	public Person(String formOfAddress, String firstName, String lastName, String telephone, String mobile,
			String eMail, String hasExtraAddress) {
		this.setFormOfAddress(formOfAddress);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setTelephone(telephone);
		this.setMobile(mobile);
		this.seteMail(eMail);
		this.setHasExtraAddress(hasExtraAddress);
		this.setStatus(null);
	}
	
	public Person(Person p) {
		this.formOfAddress = p.formOfAddress;
		this.firstName = p.firstName;
		this.lastName = p.lastName;
		this.telephone = p.telephone;
		this.mobile = p.mobile;
		this.eMail = p.eMail;
		this.isRenter = p.isRenter;
		this.address = p.address;
		this.hasExtraAddress = p.hasExtraAddress;
		this.status = p.status;
	}

}
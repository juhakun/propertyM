package de.muulti.spring.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import de.muulti.spring.dao.DAOImpl;
import de.muulti.spring.service.HouseServiceImpl;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "isOwner")
@Table(name = "Person")
@Component("person")
public abstract class Person extends HouseServiceImpl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPerson")
	private int idPerson;

	@Column(name = "formOfAddress")
//	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	private String formOfAddress;

	@Column(name = "firstName")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	private String firstName;

	@Column(name = "lastName")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	private String lastName;

	@Column(name = "telephone")
//	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	private String telephone;

	@Column(name = "mobile")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	private String mobile;

	@Column(name = "eMail")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	private String eMail;

	protected String isRenter;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "Address_idAddress")
	private Address address;

	@Column(name = "hasExtraAddress")
	private String hasExtraAddress = "false";

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

}

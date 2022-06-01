package de.muulti.spring.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import de.muulti.spring.dao.DAOImpl;

@MappedSuperclass
@Table(name = "Person")
@Component("person")
public abstract class Person extends DAOImpl {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idPerson")
	private int idPerson;
	
	@Column(name = "formOfAddress")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	protected String formOfAddress;
	
	@Column(name = "firstName")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	protected String firstName;
	
	@Column(name = "lastName")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	protected String lastName;
	
	@Column(name = "telephone")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	protected String telephone;
	
	@Column(name = "mobile")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	protected String mobile;
	
	@Column(name = "eMail")
	@NotNull(message = "Dieses Feld darf nicht leer sein.")
	protected String eMail;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="address_idAddress")
	protected Address address = new Address();



}

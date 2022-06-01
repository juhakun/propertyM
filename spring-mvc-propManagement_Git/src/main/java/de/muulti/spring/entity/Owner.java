package de.muulti.spring.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@DiscriminatorValue("Owner")
@Component("owner")
public class Owner extends Person {

	public static int counter;
	
	
//	@OneToOne(cascade = {CascadeType.ALL})
//	@JoinColumn(name="Person_idPerson")
//	private Person p = new Owner();
	
	

	

//	// Constructors
	public Owner() {
	}


//	// if Owner != Renter
//	public Owner(Person p) {
//		this.setFormOfAddress(p.getFormOfAddress());
//		this.setFirstName(p.getFirstName());
//		this.setLastName(lastName);
//		this.setAddress(address);
//	}
//	
//	// if Owner == Renter
//	public Owner(String formOfAddress, 
//			String firstName, String lastName, 
//			Address address, 
//			double rent, 
//			double monthlyNkInAdvance, 
//			int noOfPeople, 
//			LocalDate moveIn, 
//			LocalDate moveOut) {
//		Renter ownerAsRenter = new Renter(formOfAddress, firstName, lastName, address);
//		
//	}

}

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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

//@Entity
//@DiscriminatorValue("true")
//@Component("owner")
public class Owner extends Person {

	public static int counter;

	@Column(name = "isRenter")
	private String isRenter = "false";
	
	@Transient
	private String isNew = "false";

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

	public Owner() {
		
	}

	public Owner(String formOfAddress, String firstName, String lastName, String telephone, String mobile,
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

}

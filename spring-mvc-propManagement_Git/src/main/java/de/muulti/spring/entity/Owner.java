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
@DiscriminatorValue("true")
@Component("owner")
public class Owner extends Person {

	public static int counter;

	

	@Column(name = "isRenter")
	private String isRenter = "false";

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "Address_idAddress")
	private Address ownerAddress;
	
	@Column(name="hasExtraAddress")
	private String hasExtraAddress = "false";

	public String getIsRenter() {
		return isRenter;
	}

	public void setIsRenter(String isRenter) {
		this.isRenter = isRenter;
	}

	public String getHasExtraAddress() {
		return hasExtraAddress;
	}

	public void setHasExtraAddress(String hasExtraAddress) {
		this.hasExtraAddress = hasExtraAddress;
	}

	public Address getOwnerAddress() {
		return ownerAddress;
	}

//	public Address setOwnerAddress() {
//		if (hasExtraAddress == null) {
//			ownerAddress = null;
//		} else if (hasExtraAddress == "true") {
//			ownerAddress = new Address();
//		}
//		return ownerAddress;
//	
//	}

	public void setOwnerAddress(Address ownerAddress) {
//		if (hasExtraAddress == "true") {
			this.ownerAddress = ownerAddress;
//		} else {
//			this.ownerAddress = null;
//		}
	}

	public Owner() {
	}

}

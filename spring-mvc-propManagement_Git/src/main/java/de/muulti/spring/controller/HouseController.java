package de.muulti.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import de.muulti.spring.dao.DAOImpl;
import de.muulti.spring.dao.MySQLDAO;
import de.muulti.spring.entity.Address;
import de.muulti.spring.entity.House;
import de.muulti.spring.service.HouseService;
import de.muulti.spring.service.HouseServiceImpl;

@Controller
@RequestMapping("/house")
public class HouseController {

	// need to inject the house dao
	@Autowired
	@Qualifier("HouseServiceImpl")
	private HouseService houseService;
	
	private String houseName;

	// add an initbinder to remove whitespace for validation
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@RequestMapping("/showForm")
	public String showForm(Model theModel) {
		theModel.addAttribute("house", new House());
		theModel.addAttribute("ownerAddress", new Address());
		return "house-form";

	}


	@RequestMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("house") House theHouse, BindingResult theBindingResult
) {
		System.out.println("Binding result: " + theBindingResult);
		boolean hasErrors = theBindingResult.hasErrors();
		if (hasErrors) {
			hasErrors = false;
			return "house-form";

		} else if (theHouse.getOwner().getHasExtraAddress() != "false") {
			this.houseName = theHouse.getObjectName();
			houseService.insertData(theHouse);
			return "owner-address-form";
			
		} else {
			theHouse.getOwner().setOwnerAddress(theHouse.getAddress());
			houseService.insertData(theHouse);
			return "house-confirmation";
		}

	}

	@RequestMapping("/updateOwnerAddress")
	public String addOwnerAddress(@ModelAttribute("ownerAddress") Address ownerAddress, @ModelAttribute("house") House theHouse) {
		// get house data form DB with objectname = housename and then update the address
		theHouse.getOwner().getOwnerAddress().setStreet(ownerAddress.getStreet());
		theHouse.getOwner().getOwnerAddress().setHouseNo(ownerAddress.getHouseNo());
		theHouse.getOwner().getOwnerAddress().setPostalCode(ownerAddress.getPostalCode());
		theHouse.getOwner().getOwnerAddress().setCity(ownerAddress.getCity());
		houseService.insertData(theHouse);
		return "house-confirmation";

	}

	@GetMapping("/listHouses")
	public String listHouses(Model theModel) {

		// get houses from dao
		List<Object> theHouses = houseService.getData("House");
		// add houses to the model
		theModel.addAttribute("houses", theHouses);

		return "list-houses";

	}

}

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
import org.springframework.web.bind.annotation.RequestParam;

import de.muulti.spring.dao.DAOImpl;
import de.muulti.spring.dao.MySQLDAO;
import de.muulti.spring.entity.Address;
import de.muulti.spring.entity.House;
import de.muulti.spring.entity.Owner;
import de.muulti.spring.service.HouseService;
import de.muulti.spring.service.HouseServiceImpl;

@Controller
@RequestMapping("/house")
public class HouseController {

	// need to inject the house dao
	@Autowired
	@Qualifier("HouseServiceImpl")
	private HouseService houseService;

	private static String houseName = "";

	// add an initbinder to remove whitespace for validation
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@RequestMapping("/showForm")
	public String showForm(Model theModel) {
		theModel.addAttribute("house", new House());
		return "house-form";

	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("houseName") String theName, Model theModel) {
		// get house from service
		House theHouse = (House) houseService.getObject("FROM House h WHERE h.objectName='" + theName + "'");

		// set house as model attribute
		theModel.addAttribute("house", theHouse);

		// send to form
		return "house-form";

	}

	@RequestMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("house") House theHouse, BindingResult theBindingResult) {
		System.out.println("Binding result: " + theBindingResult);
		boolean hasErrors = theBindingResult.hasErrors();
		if (hasErrors) {
			hasErrors = false;
			return "house-form";

		} else if (theHouse.getOwner().getHasExtraAddress() != "false") {
			HouseController.houseName = theHouse.getObjectName();
			houseService.insertData(theHouse);
			return "owner-address-form";

		} else {
			theHouse.getOwner().setOwnerAddress(theHouse.getAddress());
			houseService.insertData(theHouse);
			return "house-confirmation";
		}

	}

	@RequestMapping("/updateOwnerAddress")
	public String updateOwnerAddress(Model theModel, 
			@ModelAttribute("house") House theHouse) {
		// create new address from model
		Address ownerAddress = new Address(theHouse.getOwner().getOwnerAddress().getStreet(),
				theHouse.getOwner().getOwnerAddress().getHouseNo(),
				theHouse.getOwner().getOwnerAddress().getPostalCode(), theHouse.getOwner().getOwnerAddress().getCity());
		House theHouse2 = (House) houseService.getObject("FROM House h WHERE h.objectName='" + houseName + "'");
		//		House theHouse2 = houseService.getHouse(houseName);
		theHouse2.getOwner().setOwnerAddress(ownerAddress);
		houseService.insertData(theHouse2);
		theModel.addAttribute(theHouse2);
		return "house-confirmation";

	}

	@GetMapping("/listHouses")
	public String listHouses(Model theModel) {

		// get houses from s
		List<Object> theHouses = houseService.getSelectedData("from House");
		// add houses to the model
		theModel.addAttribute("houses", theHouses);

		return "list-houses";

	}

}

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
		theModel.addAttribute("newHouse", new House());
		return "house-form";

	}

	@RequestMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("newHouse") House theNewHouse, BindingResult theBindingResult) {
		System.out.println("Binding result: " + theBindingResult);
		boolean hasErrors = theBindingResult.hasErrors();
		if (hasErrors) {
			hasErrors = false;
			return "house-form";

		} else if (theNewHouse.getOwner().getHasExtraAddress() != "false") {
			HouseController.houseName = theNewHouse.getObjectName();
			houseService.saveData(theNewHouse);
			return "owner-address-form";

		} else {
			theNewHouse.getOwner().setOwnerAddress(theNewHouse.getAddress());
			houseService.saveData(theNewHouse);
//			return "house-confirmation";
			return "redirect:/house/listHouses";
		}

	}

	@RequestMapping("/updateOwnerAddress")
	public String updateOwnerAddress(Model theModel, @ModelAttribute("newHouse") House theNewHouse) {
		// create new address from model
		Address ownerAddress = new Address(theNewHouse.getOwner().getOwnerAddress().getStreet(),
				theNewHouse.getOwner().getOwnerAddress().getHouseNo(),
				theNewHouse.getOwner().getOwnerAddress().getPostalCode(),
				theNewHouse.getOwner().getOwnerAddress().getCity());
		House theSavedHouse = (House) houseService.getObject("FROM House h WHERE h.objectName='" + houseName + "'");
		theSavedHouse.getOwner().setOwnerAddress(ownerAddress);
		houseService.saveData(theSavedHouse);
		theModel.addAttribute(theSavedHouse);
		return "house-confirmation";

	}

	@GetMapping("/listHouses")
	public String listHouses(Model theModel) {

		// get houses from s
		List<Object> allHouses = houseService.getSelectedData("from House order by objectName");
		// add houses to the model
		theModel.addAttribute("houses", allHouses);

		return "list-houses";

	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("idHouse") int theId, Model theModel) {
		// get house from service
		House theSavedHouse = (House) houseService.getObject("FROM House h WHERE h.idHouse='" + theId + "'");

		// set house as model attribute
		theModel.addAttribute("savedHouse", theSavedHouse);

		// send to form
		return "house-owner-form";

	}

	@RequestMapping("/saveUpdate")
	public String saveUpdate(Model theModel, @Valid @ModelAttribute("savedHouse") House theUpdatedHouse,
			BindingResult theBindingResult) {
		System.out.println("Binding result: " + theBindingResult);
		boolean hasErrors = theBindingResult.hasErrors();
		if (hasErrors) {
			hasErrors = false;
			return "house-owner-form";

		} else {
			// get saved data from DB
			
			House theSavedHouse = (House) houseService
					.getObject("FROM House h WHERE h.idHouse='" + theUpdatedHouse.getIdHouse() + "'");
			theSavedHouse.setObjectName(theUpdatedHouse.getObjectName());
			theSavedHouse.setNoOfUnits(theUpdatedHouse.getNoOfUnits());
			theSavedHouse.setTotalAreaM2(theUpdatedHouse.getTotalAreaM2());
			houseService.saveData(theSavedHouse);

			int idSavedAddress = theSavedHouse.getAddress().getIdAddress();
			int idSavedOwner = theSavedHouse.getOwner().getIdPerson();
			int idSavedOwnerAddress = theSavedHouse.getOwner().getOwnerAddress().getIdAddress();
			
			Address theSavedAddress = (Address) houseService.getObject("FROM Address a WHERE a.idAddress='" + idSavedAddress + "'");
			theSavedAddress.setStreet(theUpdatedHouse.getAddress().getStreet());
			theSavedAddress.setHouseNo(theUpdatedHouse.getAddress().getHouseNo());
			theSavedAddress.setPostalCode(theUpdatedHouse.getAddress().getPostalCode());
			theSavedAddress.setCity(theUpdatedHouse.getAddress().getCity());
			houseService.saveData(theSavedAddress);
			
			Owner theSavedOwner = (Owner) houseService.getObject("FROM Person p WHERE p.idPerson='" + idSavedOwner + "'");
			theSavedOwner.setFirstName(theUpdatedHouse.getOwner().getFirstName());
			theSavedOwner.setLastName(theUpdatedHouse.getOwner().getLastName());
			theSavedOwner.setFormOfAddress(theUpdatedHouse.getOwner().getFormOfAddress());
			theSavedOwner.setTelephone(theUpdatedHouse.getOwner().getTelephone());
			theSavedOwner.setMobile(theUpdatedHouse.getOwner().getMobile());
			theSavedOwner.seteMail(theUpdatedHouse.getOwner().geteMail());
			houseService.saveData(theSavedOwner);
			
			Address theSavedOwnerAddress = (Address) houseService.getObject("FROM Address a WHERE a.idAddress='" + idSavedOwnerAddress + "'");
			theSavedOwnerAddress.setStreet(theUpdatedHouse.getOwner().getOwnerAddress().getStreet());
			theSavedOwnerAddress.setHouseNo(theUpdatedHouse.getOwner().getOwnerAddress().getHouseNo());
			theSavedOwnerAddress.setPostalCode(theUpdatedHouse.getOwner().getOwnerAddress().getPostalCode());
			theSavedOwnerAddress.setCity(theUpdatedHouse.getOwner().getOwnerAddress().getCity());
			houseService.saveData(theSavedOwnerAddress);
			
//			//create new Address, Owner and Address Owner from model
//			Address updatedAddress = new Address(theUpdatedHouse.getAddress());
//			Owner updatedOwner = new Owner();
//			Address updatedOwnerAddress = new Address(theUpdatedHouse.getOwner().getOwnerAddress());
//			theSavedHouse.setAddress(theUpdatedHouse.getAddress());
//			theSavedHouse.setOwner(theUpdatedHouse.getOwner());
//			theSavedHouse.getOwner().setOwnerAddress(theUpdatedHouse.getOwner().getOwnerAddress());
//			houseService.updateData(theSavedHouse);
//			theModel.addAttribute(theSavedHouse);

//			return "house-confirmation";
			return "redirect:/house/listHouses";
		}
	}

	@RequestMapping("/deleteObject")
	public String deleteObject(@RequestParam("idHouse") int theId, Model theModel) {
		// get house from service
		houseService.deleteData(houseService.getObject("FROM House h WHERE h.idHouse='" + theId + "'"));

		// send to form
		return "redirect:/house/listHouses";

	}

}

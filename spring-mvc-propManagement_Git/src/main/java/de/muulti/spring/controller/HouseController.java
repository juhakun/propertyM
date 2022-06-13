package de.muulti.spring.controller;

import java.util.ArrayList;
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
		if (theBindingResult.hasErrors()) {
			hasErrors = false;
			return "house-form";

		} else {
			House duplicateHouse = (House) houseService.getDuplicate("FROM House", theNewHouse);
			Address duplicateAddress = (Address) houseService.getDuplicate("FROM Address", theNewHouse.getAddress());
			Owner duplicateOwner = (Owner) houseService.getDuplicate("FROM House", theNewHouse.getOwner());
			if (duplicateHouse != null) {
				System.out.println("House Name exists");
				theNewHouse.setObjectName(theNewHouse.getObjectName() + " Kopie");
				return "house-form";
				// Meldung ergänzen, dass Name bereits vergeben ist
			} 
			if (duplicateAddress != null) {
				System.out.println("Address exists");
				theNewHouse.setAddress(duplicateAddress);
			} 
			if (duplicateOwner != null) {
				System.out.println("Owner exists");
				theNewHouse.setOwner(duplicateOwner);
//				theNewHouse.getOwner().setOwnerAddress(duplicateOwner.getOwnerAddress());
				
			} else if (duplicateOwner == null ){
					if (theNewHouse.getOwner().getHasExtraAddress() != "false") {

						// save house name for later use
						HouseController.houseName = theNewHouse.getObjectName();
						houseService.saveData(theNewHouse);
						return "owner-address-form";
					} else {
						theNewHouse.getOwner().setAddress(theNewHouse.getAddress());
						
						
					}
				
			}
			houseService.saveData(theNewHouse);
//			return "house-confirmation";
			return "redirect:/house/listHouses";
		}
		

	}

	@RequestMapping("/updateOwnerAddress")
	public String updateOwnerAddress(Model theModel, @ModelAttribute("newHouse") House theNewHouse) {
		// create new address from model
		Address ownerAddress = new Address(theNewHouse.getOwner().getAddress().getStreet(),
				theNewHouse.getOwner().getAddress().getHouseNo(),
				theNewHouse.getOwner().getAddress().getPostalCode(),
				theNewHouse.getOwner().getAddress().getCity());

		// get house from service
		House theSavedHouse = (House) houseService.getObject("FROM House h WHERE h.objectName='" + houseName + "'");

		Address duplicateOwnerAddress = (Address) houseService.getDuplicate("FROM Address", ownerAddress);
		if (duplicateOwnerAddress != null) {
			System.out.println("Owner address exists");
			theSavedHouse.getOwner().setAddress(duplicateOwnerAddress);
			houseService.saveData(theSavedHouse);
			theModel.addAttribute(theSavedHouse);
			return "house-confirmation";
			// Meldung ergänzen

			// change address properties

		} else {
			theSavedHouse.getOwner().setAddress(ownerAddress);
			houseService.saveData(theSavedHouse);
			theModel.addAttribute(theSavedHouse);

			return "house-confirmation";
		}

	}

	@GetMapping("/listHouses")
	public String listHouses(Model theModel) {

		// get houses from service
		List<HouseServiceImpl> allHouses = houseService.getSelectedData("FROM House ORDER BY objectName");
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
			// get saved house from service
			House theSavedHouse = (House) houseService
					.getObject("FROM House h WHERE h.idHouse='" + theUpdatedHouse.getIdHouse() + "'");

			// change house properties
			theSavedHouse.setObjectName(theUpdatedHouse.getObjectName());
			theSavedHouse.setNoOfUnits(theUpdatedHouse.getNoOfUnits());
			theSavedHouse.setTotalAreaM2(theUpdatedHouse.getTotalAreaM2());

			// get ids based on saved house
			int idSavedAddress = theSavedHouse.getAddress().getIdAddress();
			int idSavedOwner = theSavedHouse.getOwner().getIdPerson();
			int idSavedOwnerAddress = theSavedHouse.getOwner().getAddress().getIdAddress();

			// Change properties
			Address theSavedAddress = (Address) houseService
					.getObject("FROM Address a WHERE a.idAddress='" + idSavedAddress + "'");
			theSavedAddress.setStreet(theUpdatedHouse.getAddress().getStreet());
			theSavedAddress.setHouseNo(theUpdatedHouse.getAddress().getHouseNo());
			theSavedAddress.setPostalCode(theUpdatedHouse.getAddress().getPostalCode());
			theSavedAddress.setCity(theUpdatedHouse.getAddress().getCity());

			Owner theSavedOwner = (Owner) houseService
					.getObject("FROM Person p WHERE p.idPerson='" + idSavedOwner + "'");
			theSavedOwner.setFirstName(theUpdatedHouse.getOwner().getFirstName());
			theSavedOwner.setLastName(theUpdatedHouse.getOwner().getLastName());
			theSavedOwner.setFormOfAddress(theUpdatedHouse.getOwner().getFormOfAddress());
			theSavedOwner.setTelephone(theUpdatedHouse.getOwner().getTelephone());
			theSavedOwner.setMobile(theUpdatedHouse.getOwner().getMobile());
			theSavedOwner.seteMail(theUpdatedHouse.getOwner().geteMail());

			Address theSavedOwnerAddress = (Address) houseService
					.getObject("FROM Address a WHERE a.idAddress='" + idSavedOwnerAddress + "'");
			theSavedOwnerAddress.setStreet(theUpdatedHouse.getOwner().getAddress().getStreet());
			theSavedOwnerAddress.setHouseNo(theUpdatedHouse.getOwner().getAddress().getHouseNo());
			theSavedOwnerAddress.setPostalCode(theUpdatedHouse.getOwner().getAddress().getPostalCode());
			theSavedOwnerAddress.setCity(theUpdatedHouse.getOwner().getAddress().getCity());

			// save changes
			houseService.saveData(theSavedHouse);
			houseService.saveData(theSavedAddress);
			houseService.saveData(theSavedOwner);
			houseService.saveData(theSavedOwnerAddress);

//			return "house-confirmation";
			return "redirect:/house/listHouses";
		}
	}

	@RequestMapping("/deleteObject")
	public String deleteObject(@RequestParam("idHouse") int theId, Model theModel) {
		// get house from service
		House theSavedHouse = (House) houseService.getObject("FROM House h WHERE h.idHouse='" + theId + "'");

		// check for duplicate use of address and person
		// get address, owner and owner address based on saved house
		Address theSavedAddress = theSavedHouse.getAddress();
		Owner theSavedOwner = theSavedHouse.getOwner();
		Address theSavedOwnerAddress = theSavedHouse.getOwner().getAddress();

		// check if address is used by any other house or owner
		int duplicateAddress = houseService.checkForDuplicatesByID("FROM House", theSavedAddress)[0];
		int duplicateAddressInOwner = houseService.checkForDuplicatesByID("FROM Owner", theSavedAddress)[2];

		// check if any other house has same owner
		int duplicateOwner = houseService.checkForDuplicatesByID("FROM House", theSavedOwner)[1];

		// check if owner address is used by any other house or owner
		int duplicateOwnerAddressInHouse = houseService.checkForDuplicatesByID("FROM House", theSavedOwnerAddress)[0];
		int duplicateOwnerAddress = houseService.checkForDuplicatesByID("FROM Owner", theSavedOwnerAddress)[2];

		if (duplicateAddress <= 1 && duplicateAddressInOwner <= 1) {
			houseService.deleteData(Address.class, theSavedAddress.getIdAddress());
		}
		if (duplicateOwner <= 1) {
			houseService.deleteData(Owner.class, theSavedOwner.getIdPerson());
		}
		if (duplicateOwnerAddress <= 1 && duplicateOwnerAddressInHouse <= 1) {
			houseService.deleteData(Address.class, theSavedOwnerAddress.getIdAddress());
		}

		houseService.deleteData(House.class, theId);

		// send to form
		return "redirect:/house/listHouses";

	}

}

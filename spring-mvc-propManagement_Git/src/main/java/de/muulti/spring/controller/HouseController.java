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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.muulti.spring.dao.DAOImpl;
import de.muulti.spring.dao.MySQLDAO;
import de.muulti.spring.entity.Address;
import de.muulti.spring.entity.House;
import de.muulti.spring.entity.Owner;
import de.muulti.spring.entity.Person;
import de.muulti.spring.entity.Renter;
import de.muulti.spring.service.HouseService;
import de.muulti.spring.service.HouseServiceImpl;

@Controller
@RequestMapping("/house")
public class HouseController {

	// need to inject the house service layer
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

	// WORKING
	@RequestMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("newHouse") House theNewHouse, BindingResult theBindingResult,
			Model theModel) {
		System.out.println("Binding result: " + theBindingResult);
		boolean hasErrors = theBindingResult.hasErrors();
		if (theBindingResult.hasErrors()) {
			hasErrors = false;
			return "house-form";

		} else {

			// check if house with same name exists
			House duplicateHouse = (House) houseService.getDuplicate("FROM House", theNewHouse);
			if (duplicateHouse != null) {

				theNewHouse.setObjectName(theNewHouse.getObjectName() + " Kopie");
				return "house-form";
				// TODO: ADD MESSAGE
			}

			// create address from model
			Address theNewAddress = new Address(theNewHouse.getAddress().getStreet(),
					theNewHouse.getAddress().getHouseNo(), theNewHouse.getAddress().getPostalCode(),
					theNewHouse.getAddress().getCity());

			// check if address exists and return either the saved or the new address
			Address houseAddress = (Address) houseService.getDuplicate("FROM Address", theNewAddress);
			houseService.saveData(houseAddress);
			theNewHouse.setAddress(houseAddress);

			// create owner from model
			Person theNewOwner = new Person(theNewHouse.getOwner());
			if (!theNewOwner.getHasExtraAddress().equals("true") || theNewOwner.getAddress() == null) {
				theNewOwner.setAddress(houseAddress);
			}

			Address ownerAddress = (Address) houseService.getDuplicate("FROM Address", theNewOwner.getAddress());
			houseService.saveData(ownerAddress);
			theNewOwner.setAddress(ownerAddress);

			// check if person exists and return either the saved or the new person
			Person owner = (Person) houseService.getDuplicate("FROM Person", theNewOwner);
			houseService.saveData(owner.getAddress());
			houseService.saveData(owner);

			theNewHouse.setOwner(owner);
			houseService.saveData(theNewHouse);
			return "redirect:/house/listHouses";
		}
	}

	@GetMapping("/showHouse/{idHouse}")
	public String showHouse(@PathVariable("idHouse") int idHouse, Model theModel) {

		// get houses from service
		List<HouseServiceImpl> theHouse = houseService.getSelectedData("FROM House WHERE idHouse = '" + idHouse + "'");
		// add houses to the model
		theModel.addAttribute("houses", theHouse);
		theModel.addAttribute("noOfHouses", 1);

		return "list-houses";

	}

	// WORKING
	@GetMapping("/listHouses")
	public String listHouses(Model theModel) {

		// get houses from service
		List<HouseServiceImpl> allHouses = houseService
				.getSelectedData("FROM House WHERE status = null ORDER BY objectName");
		// add houses to the model
		theModel.addAttribute("houses", allHouses);
		theModel.addAttribute("noOfHouses", allHouses.size());

		return "list-houses";

	}

	// WORKING
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("idHouse") int theId, Model theModel) {

		// get house from service
		House theSavedHouse = (House) houseService.getObjectByID(House.class, theId);
		if (theSavedHouse.getOwner().getAddress() == null) {
			theSavedHouse.getOwner().setAddress(theSavedHouse.getAddress());
		}

		// set house as model attribute
		theModel.addAttribute("savedHouse", theSavedHouse);

		// send to form
		return "house-owner-form";

	}

	// TODO:
	@RequestMapping("/saveUpdate")
	public String saveUpdate(Model theModel, @Valid @ModelAttribute("savedHouse") House theUpdatedHouse,
			BindingResult theBindingResult) {
		System.out.println("Binding result: " + theBindingResult);
		boolean hasErrors = theBindingResult.hasErrors();
		if (hasErrors) {
			hasErrors = false;
			return "house-owner-form";

		} else {
			// get saved house, address and owner from service
			House theSavedHouse = (House) houseService.getObjectByID(House.class, theUpdatedHouse.getIdHouse());
			Address theSavedAddress = theSavedHouse.getAddress();
			Person theSavedOwner = theSavedHouse.getOwner();
			Address theSavedOwnerAddress = theSavedHouse.getOwner().getAddress();

//			 Create updated objects from model
			Address theUpdatedAddress = new Address(theUpdatedHouse.getAddress().getStreet(),
					theUpdatedHouse.getAddress().getHouseNo(), theUpdatedHouse.getAddress().getPostalCode(),
					theUpdatedHouse.getAddress().getCity());

			Person theUpdatedOwner = new Person(theUpdatedHouse.getOwner().getFormOfAddress(),
					theUpdatedHouse.getOwner().getFirstName(), theUpdatedHouse.getOwner().getLastName(),
					theUpdatedHouse.getOwner().getTelephone(), theUpdatedHouse.getOwner().getMobile(),
					theUpdatedHouse.getOwner().geteMail(), theUpdatedAddress,
					theUpdatedHouse.getOwner().getHasExtraAddress());

			Address theUpdatedOwnerAddress = new Address(theUpdatedHouse.getOwner().getAddress().getStreet(),
					theUpdatedHouse.getOwner().getAddress().getHouseNo(),
					theUpdatedHouse.getOwner().getAddress().getPostalCode(),
					theUpdatedHouse.getOwner().getAddress().getCity());

			// change house properties
			theSavedHouse.setObjectName(theUpdatedHouse.getObjectName());
			theSavedHouse.setNoOfUnits(theUpdatedHouse.getNoOfUnits());
			theSavedHouse.setTotalAreaM2(theUpdatedHouse.getTotalAreaM2());
			

			// update address properties
			// duplicate check not really necessary as house cannot move to another address :-)
			// only correction of typos likely
			theSavedAddress.setStreet(theUpdatedHouse.getAddress().getStreet());
			theSavedAddress.setHouseNo(theUpdatedHouse.getAddress().getHouseNo());
			theSavedAddress.setPostalCode(theUpdatedHouse.getAddress().getPostalCode());
			theSavedAddress.setCity(theUpdatedHouse.getAddress().getCity());
			houseService.saveData(theSavedAddress);

			// update owner properties
			// update owner

			// if the house owner has NOT changed
			if(theUpdatedHouse.getOwnerHasChanged() != null) {	
				// check for address duplicates (if new owners address exists)
				Address newOwnerAddress = (Address) houseService.getDuplicate("FROM Address", theUpdatedOwnerAddress);
				houseService.saveData(newOwnerAddress);
			// check for duplicates (if new owner owns any other houses)
				Person newHouseOwner = (Person) houseService.getDuplicate("FROM Person", theUpdatedOwner);
				newHouseOwner.setAddress(newOwnerAddress);
				houseService.saveData(newHouseOwner);
				theSavedHouse.setOwner(newHouseOwner);
				
			} else {
				System.out.println(theUpdatedHouse.getStatus());
				System.out.println("Owner has not changed");
				theSavedOwner.setFormOfAddress(theUpdatedOwner.getFormOfAddress());
				theSavedOwner.setFirstName(theUpdatedOwner.getFirstName());
				theSavedOwner.setLastName(theUpdatedOwner.getLastName());
				System.out.println(theUpdatedOwner.getLastName());
				theSavedOwner.setTelephone(theUpdatedOwner.getTelephone());
				theSavedOwner.setMobile(theUpdatedOwner.getMobile());
				theSavedOwner.seteMail(theUpdatedOwner.geteMail());
				theSavedOwner.getAddress().setStreet(theUpdatedOwnerAddress.getStreet());
				theSavedOwner.getAddress().setHouseNo(theUpdatedOwnerAddress.getHouseNo());
				theSavedOwner.getAddress().setPostalCode(theUpdatedOwnerAddress.getPostalCode());
				theSavedOwner.getAddress().setCity(theUpdatedOwnerAddress.getCity());
				houseService.saveData(theSavedOwner.getAddress());
				houseService.saveData(theSavedOwner);
			}

			// save changes
			houseService.saveData(theSavedHouse);
			System.out.println(theUpdatedHouse.getIdHouse());
			return "redirect:/house/showHouse/" + theSavedHouse.getIdHouse();
		}

	}

	// WORKING
	@RequestMapping("/deleteObject")
	public String deleteObject(@RequestParam("idHouse") int theId, Model theModel) {

		// set status of house to "deleted"
		houseService.deleteData(House.class, theId);

//		TODO: SETTING STATUS OF HOUSE ADDRESS AND HOUSE OWNER TO "DELETED" WHEN NO OTHER HOUSE USES THEM

		// send to form
		return "redirect:/house/listHouses";

	}

}

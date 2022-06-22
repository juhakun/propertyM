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

	@RequestMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("newHouse") House theNewHouse, BindingResult theBindingResult) {
		System.out.println("Binding result: " + theBindingResult);
		boolean hasErrors = theBindingResult.hasErrors();
		if (theBindingResult.hasErrors()) {
			hasErrors = false;
			return "house-form";

		} else {
			// create properties from model
			Address newAddress = new Address(theNewHouse.getAddress().getStreet(),
					theNewHouse.getAddress().getHouseNo(), theNewHouse.getAddress().getPostalCode(),
					theNewHouse.getAddress().getCity());

			// check if house with same name exists
			House duplicateHouse = (House) houseService.getDuplicate("FROM House", theNewHouse);
			if (duplicateHouse != null) {

				theNewHouse.setObjectName(theNewHouse.getObjectName() + " Kopie");
				return "house-form";
				// TODO: ADD MESSAGE
			}

			// check if address exists and return either the saved address or the new
			// address
			Address houseAddress = (Address) houseService.getDuplicate("FROM Address", theNewHouse.getAddress());
			System.out.println(houseAddress.getIdAddress());
			houseService.saveData(houseAddress);
			theNewHouse.setAddress(houseAddress);

			// check if person (owner or renter) exists and return either the saved owner or
			// the new owner
			Person owner = (Person) houseService.getDuplicate("FROM Person", theNewHouse.getOwner());
			System.out.println(owner.getIdPerson());
			houseService.saveData(owner);
			theNewHouse.setOwner(owner);

			if (theNewHouse.getOwner().getHasExtraAddress() != "false") {

				// save house name for later use
				HouseController.houseName = theNewHouse.getObjectName();
				houseService.saveData(theNewHouse);
				return "owner-address-form";
			} else {
				owner.setAddress(houseAddress);
				houseService.saveData(owner);
			}
			houseService.saveData(theNewHouse);
			return "redirect:/house/listHouses";
		}

	}

	@RequestMapping("/updateOwnerAddress")
	public String updateOwnerAddress(Model theModel, @ModelAttribute("newHouse") House theNewHouse) {

		// get house from service
		House theSavedHouse = (House) houseService.getObject("FROM House h WHERE h.objectName='" + houseName + "'");

		// check if owner address exists and return either the saved address or the new
		// address
		Address ownerAddress = (Address) houseService.getDuplicate("FROM Address", theNewHouse.getOwner().getAddress());
		theSavedHouse.getOwner().setAddress(ownerAddress);
		houseService.saveData(theSavedHouse.getOwner());
		theModel.addAttribute(theSavedHouse);
		// TODO: ADD MESSAGE

//			return "house-confirmation";
		return "redirect:/house/listHouses";

	}

	@GetMapping("/listHouses")
	public String listHouses(Model theModel) {

		// get houses from service
		List<HouseServiceImpl> allHouses = houseService
				.getSelectedData("FROM House WHERE status = null ORDER BY objectName");
		// add houses to the model
		theModel.addAttribute("houses", allHouses);

		return "list-houses";

	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("idHouse") int theId, Model theModel) {

		// get house from service
		House theSavedHouse = (House) houseService.getObject("FROM House h WHERE h.idHouse='" + theId + "'");
		if (theSavedHouse.getOwner().getAddress() == null) {
			theSavedHouse.getOwner().setAddress(theSavedHouse.getAddress());
		}

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
			// get saved house, address and owner from service
			House theSavedHouse = (House) houseService
					.getObject("FROM House h WHERE h.idHouse='" + theUpdatedHouse.getIdHouse() + "'");
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
					theUpdatedHouse.getOwner().geteMail(), theUpdatedHouse.getOwner().getHasExtraAddress());

			Address theUpdatedOwnerAddress = new Address(theUpdatedHouse.getOwner().getAddress().getStreet(),
					theUpdatedHouse.getOwner().getAddress().getHouseNo(),
					theUpdatedHouse.getOwner().getAddress().getPostalCode(),
					theUpdatedHouse.getOwner().getAddress().getCity());

			// change house properties
			theSavedHouse.setObjectName(theUpdatedHouse.getObjectName());
			theSavedHouse.setNoOfUnits(theUpdatedHouse.getNoOfUnits());
			theSavedHouse.setTotalAreaM2(theUpdatedHouse.getTotalAreaM2());

			// change or update address properties
			// check not really necessary as house cannot move to other address :-)

			int addressUsedByOtherHouse = houseService.checkForDuplicatesByID("FROM House", theSavedAddress)[0];
			int addressUsedByOtherPerson = houseService.checkForDuplicatesByID("FROM Person", theSavedAddress)[2];
			if (addressUsedByOtherHouse > 1 || addressUsedByOtherPerson > 1) {
				// create new address
				// check if new address already exists first
				Address newHouseAddress = (Address) houseService.getDuplicate("FROM Address",
						theUpdatedHouse.getAddress());
				houseService.saveData(newHouseAddress);
				theSavedHouse.setAddress(newHouseAddress);
			} else {
				theSavedAddress.setStreet(theUpdatedHouse.getAddress().getStreet());
				theSavedAddress.setHouseNo(theUpdatedHouse.getAddress().getHouseNo());
				theSavedAddress.setPostalCode(theUpdatedHouse.getAddress().getPostalCode());
				theSavedAddress.setCity(theUpdatedHouse.getAddress().getCity());
				houseService.saveData(theSavedAddress);
			}

			// change owner properties
			// update owner and owner address

			int ownerUsedByOtherHouse = houseService.checkForDuplicatesByID("FROM House", theSavedOwner)[1];
			Person newHouseOwner = (Person) houseService.getDuplicate("FROM Person", theUpdatedOwner);
			if (ownerUsedByOtherHouse > 1) {
				// create new owner
				// check if new owner already exists first			
				houseService.saveData(newHouseOwner);
				theSavedHouse.setOwner(newHouseOwner);
			} else {
				int ownerExists = houseService.checkForDuplicatesByID("FROM Person", theUpdatedOwner)[2];
				if (ownerExists > 0) {
					theSavedHouse.setOwner(newHouseOwner);
				} else {
					theSavedOwner.setFormOfAddress(theUpdatedOwner.getFormOfAddress());
					theSavedOwner.setFirstName(theUpdatedOwner.getFirstName());
					theSavedOwner.setLastName(theUpdatedOwner.getLastName());
					theSavedOwner.setTelephone(theUpdatedOwner.getTelephone());
					theSavedOwner.setMobile(theUpdatedOwner.getMobile());
					theSavedOwner.seteMail(theUpdatedOwner.geteMail());
					houseService.saveData(theSavedOwner);
				}
			}

			// change owner address properties
			// check if updated owner address exists
			int ownerAddressUsedByOtherHouse = houseService.checkForDuplicatesByID("FROM House",
					theSavedOwnerAddress)[0];
			int ownerAddressUsedByOtherPerson = houseService.checkForDuplicatesByID("FROM Person",
					theSavedOwnerAddress)[2];
			Address newOwnerAddress = (Address) houseService.getDuplicate("FROM Address", theUpdatedOwnerAddress);
			if (ownerAddressUsedByOtherHouse > 1 || ownerAddressUsedByOtherPerson > 1) {
				// create new address
				// check if new address already exists first
				
				houseService.saveData(newOwnerAddress);
				theSavedOwner.setAddress(newOwnerAddress);
				houseService.saveData(theSavedOwner);
			} else {
				int ownerAddressExists = houseService.checkForDuplicatesByID("FROM Address", theUpdatedOwnerAddress)[3];
				if (ownerAddressExists > 0) {
					theSavedHouse.setAddress(newOwnerAddress);
				} else {
				theSavedOwnerAddress.setStreet(theUpdatedOwnerAddress.getStreet());
				theSavedOwnerAddress.setHouseNo(theUpdatedOwnerAddress.getHouseNo());
				theSavedOwnerAddress.setPostalCode(theUpdatedOwnerAddress.getPostalCode());
				theSavedOwnerAddress.setCity(theUpdatedOwnerAddress.getCity());
				houseService.saveData(theSavedOwnerAddress);
				}
			}

			// save changes
			houseService.saveData(theSavedHouse);
			return "redirect:/house/listHouses";
		}

	}

	@RequestMapping("/deleteObject")
	public String deleteObject(@RequestParam("idHouse") int theId, Model theModel) {
		// get house from service
//		House theSavedHouse = (House) houseService.getObject("FROM House h WHERE h.idHouse='" + theId + "'");

		// set status of house to "deleted"
		houseService.deleteData(House.class, theId);

//		TODO: SETTING STATUS OF HOUSE ADDRESS AND HOUSE OWNER TO "DELETED" WHEN NO OTHER HOUSE USES THEM

//		if (duplicateAddress <= 1 && duplicateAddressInOwner <= 1) {
//			houseService.deleteData(Address.class, theSavedAddress.getIdAddress());
//		}
//		if (theSavedOwnerAddress != null) {
//			if (duplicateOwnerAddress <= 1 && duplicateOwnerAddressInHouse <= 1) {
//				houseService.deleteData(Address.class, theSavedOwnerAddress.getIdAddress());
//			}
//		}
//		if (duplicateOwner <= 1) {
//			houseService.deleteData(Owner.class, theSavedOwner.getIdPerson());
//		}

		// send to form
		return "redirect:/house/listHouses";

	}

}

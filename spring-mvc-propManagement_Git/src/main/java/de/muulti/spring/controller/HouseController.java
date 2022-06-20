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
			House duplicateHouse = (House) houseService.getDuplicate("FROM House", theNewHouse);
			Address duplicateAddress = (Address) houseService.getDuplicate("FROM Address", theNewHouse.getAddress());
			Owner duplicateOwner = (Owner) houseService.getDuplicate("FROM Owner", theNewHouse.getOwner());
			Address newAddress = new Address(theNewHouse.getAddress().getStreet(),
					theNewHouse.getAddress().getHouseNo(), theNewHouse.getAddress().getPostalCode(),
					theNewHouse.getAddress().getCity());

			if (duplicateHouse != null) {
				System.out.println("House Name exists");
				theNewHouse.setObjectName(theNewHouse.getObjectName() + " Kopie");
				return "house-form";
				// TODO: ADD MESSAGE
			}
			if (duplicateAddress != null) {
				System.out.println("Address exists");
				duplicateAddress.setStatus(null);
				houseService.saveData(duplicateAddress);
				theNewHouse.setAddress(duplicateAddress);
			} else {

				houseService.saveData(newAddress);
				theNewHouse.setAddress(newAddress);
			}
			if (duplicateOwner != null) {
				System.out.println("Owner exists");
				duplicateOwner.setStatus(null);
				houseService.saveData(duplicateOwner);
				theNewHouse.setOwner(duplicateOwner);

			} else if (duplicateOwner == null) {
				Owner newOwner = new Owner(theNewHouse.getOwner().getFormOfAddress(),
						theNewHouse.getOwner().getFirstName(), theNewHouse.getOwner().getLastName(),
						theNewHouse.getOwner().getTelephone(), theNewHouse.getOwner().getMobile(),
						theNewHouse.getOwner().geteMail(), theNewHouse.getOwner().getHasExtraAddress());
				houseService.saveData(newOwner);
				theNewHouse.setOwner(newOwner);

				if (theNewHouse.getOwner().getHasExtraAddress() != "false") {

					// save house name for later use
					HouseController.houseName = theNewHouse.getObjectName();
					houseService.saveData(theNewHouse);
					return "owner-address-form";
				}

			}
			houseService.saveData(theNewHouse);
			return "redirect:/house/listHouses";
		}

	}

	@RequestMapping("/updateOwnerAddress")
	public String updateOwnerAddress(Model theModel, @ModelAttribute("newHouse") House theNewHouse) {
		// create new address from model
		Address ownerAddress = new Address(theNewHouse.getOwner().getAddress().getStreet(),
				theNewHouse.getOwner().getAddress().getHouseNo(), theNewHouse.getOwner().getAddress().getPostalCode(),
				theNewHouse.getOwner().getAddress().getCity());

		// get house from service
		House theSavedHouse = (House) houseService.getObject("FROM House h WHERE h.objectName='" + houseName + "'");

		Address duplicateOwnerAddress = (Address) houseService.getDuplicate("FROM Address",
				theNewHouse.getOwner().getAddress());
		if (duplicateOwnerAddress != null) {
			System.out.println("Owner address exists");
			theSavedHouse.getOwner().setAddress(duplicateOwnerAddress);
			houseService.saveData(theSavedHouse.getOwner());
			theModel.addAttribute(theSavedHouse);
			return "house-confirmation";
			// TODO: ADD MESSAGE

			// change address properties

		} else {
			houseService.saveData(ownerAddress);
			theSavedHouse.getOwner().setAddress(ownerAddress);
			houseService.saveData(theSavedHouse.getOwner());
			theModel.addAttribute(theSavedHouse);

//			return "house-confirmation";
			return "redirect:/house/listHouses";
		}

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
			// get saved house from service
			House theSavedHouse = (House) houseService
					.getObject("FROM House h WHERE h.idHouse='" + theUpdatedHouse.getIdHouse() + "'");
			Address theSavedAddress = theSavedHouse.getAddress();
			Owner theSavedOwner = theSavedHouse.getOwner();

//			 Create updated objects from model attribute
			Address theUpdatedAddress = new Address(theUpdatedHouse.getAddress().getStreet(),
					theUpdatedHouse.getAddress().getHouseNo(), theUpdatedHouse.getAddress().getPostalCode(),
					theUpdatedHouse.getAddress().getCity());

			Owner theUpdatedOwner = new Owner(theUpdatedHouse.getOwner().getFormOfAddress(),
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
			theSavedAddress.setStreet(theUpdatedHouse.getAddress().getStreet());
			theSavedAddress.setHouseNo(theUpdatedHouse.getAddress().getHouseNo());
			theSavedAddress.setPostalCode(theUpdatedHouse.getAddress().getPostalCode());
			theSavedAddress.setCity(theUpdatedHouse.getAddress().getCity());
			houseService.saveData(theSavedAddress);

		
			// change owner properties
			// update owner and owner address
			if (theUpdatedHouse.getOwner().getIsNew() == "false") {
				System.out.println(theUpdatedHouse.getOwner().getIsNew());
				
				theSavedOwner.setFormOfAddress(theUpdatedOwner.getFormOfAddress());
				theSavedOwner.setFirstName(theUpdatedOwner.getFirstName());
				theSavedOwner.setLastName(theUpdatedOwner.getLastName());
				theSavedOwner.setTelephone(theUpdatedOwner.getTelephone());
				theSavedOwner.setMobile(theUpdatedOwner.getMobile());
				theSavedOwner.seteMail(theUpdatedOwner.geteMail());
				houseService.saveData(theSavedOwner);

				// add new owner an owner address
			} else {
				theSavedOwner.setStatus("deleted");
				houseService.saveData(theSavedOwner);
				// check if owner already exists
				Owner duplicateOwner = (Owner) houseService.getDuplicate("FROM Owner", theUpdatedOwner);
				if (duplicateOwner == null) {
					
					houseService.saveData(theUpdatedOwner);
					theSavedHouse.setOwner(theUpdatedOwner);
					
				} else {
					duplicateOwner.setStatus(null);
					houseService.saveData(duplicateOwner);
					theSavedHouse.setOwner(duplicateOwner);
					
				}
				houseService.saveData(theSavedHouse.getOwner());
			}
				
			// change owner address properties
			// check if updated owner address exists
			Address duplicateOwnerAddress = (Address) houseService.getDuplicate("FROM Address", theUpdatedOwnerAddress);
			Address theOwnerAddress;
			if (duplicateOwnerAddress == null) {
				houseService.saveData(theUpdatedOwnerAddress);
				theOwnerAddress = theUpdatedOwnerAddress;
				
			} else {
				duplicateOwnerAddress.setStatus(null);
				houseService.saveData(duplicateOwnerAddress);
				theOwnerAddress = duplicateOwnerAddress;
				
				}
				
			if (theOwnerAddress.getStreet().equals(theUpdatedAddress.getStreet())
						&& theOwnerAddress.getHouseNo().equals(theUpdatedAddress.getHouseNo())
						&& theOwnerAddress.getPostalCode().equals(theUpdatedAddress.getPostalCode())
						&& theOwnerAddress.getCity().equals(theUpdatedAddress.getCity())) {
					theSavedHouse.getOwner().setHasExtraAddress("false");
					theSavedHouse.getOwner().setAddress(null);
					houseService.saveData(theSavedHouse.getOwner());
				} else {
					theSavedHouse.getOwner().setHasExtraAddress("true");
					theSavedHouse.getOwner().setAddress(theOwnerAddress);
					houseService.saveData(theSavedHouse.getOwner());
				}

//			}

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

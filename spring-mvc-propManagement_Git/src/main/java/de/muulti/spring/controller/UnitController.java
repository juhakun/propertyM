package de.muulti.spring.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Status;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.muulti.spring.dao.DAOImpl;
import de.muulti.spring.dao.MySQLDAO;
import de.muulti.spring.entity.Address;
import de.muulti.spring.entity.House;
import de.muulti.spring.entity.Owner;
import de.muulti.spring.entity.Person;
import de.muulti.spring.entity.Renter;
import de.muulti.spring.entity.Unit;
import de.muulti.spring.service.HouseService;
import de.muulti.spring.service.HouseServiceImpl;

@Controller
@RequestMapping("/unit")
public class UnitController {

	// need to inject the house service layer
	@Autowired
	@Qualifier("HouseServiceImpl")
	private HouseService houseService;

	private static String unitName = "";
	private static int houseID = 0;

	// add an initbinder to remove whitespace for validation
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@RequestMapping("/showForm")
	@Transactional
	public String showForm(Model theModel) {
		House selectedHouse = (House) houseService.getObjectByID(House.class, houseID);
		System.out.println(selectedHouse.getObjectName());
		int idHouse = houseID;
		if (selectedHouse.getUnits().size() < selectedHouse.getNoOfUnits()) {
			theModel.addAttribute("newUnit", new Unit());
			return "unit-form";
		} else {
			// HIER IST AUCH NOCH TEIL DES PROBLEMS
			return "redirect:/unit/showUnits/" + idHouse;
		}

	}

	@RequestMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("newUnit") Unit theNewUnit, BindingResult theBindingResult) {
		System.out.println("Binding result: " + theBindingResult);
		boolean hasErrors = theBindingResult.hasErrors();
		if (theBindingResult.hasErrors()) {
			hasErrors = false;
			return "unit-form";

		} else {
			System.out.println(houseID);
			theNewUnit.getRenter().setMoveIn(LocalDate.parse(theNewUnit.getRenter().getMoveInString()));
			theNewUnit.getRenter().setSqlDateMoveIn(Date.valueOf(theNewUnit.getRenter().getMoveIn()));
			theNewUnit.getRenter().setMoveOut(LocalDate.parse(theNewUnit.getRenter().getMoveOutString()));
			theNewUnit.getRenter().setSqlDateMoveOut(Date.valueOf(theNewUnit.getRenter().getMoveOut()));
			houseService.saveData(theNewUnit.getRenter());
			houseService.addUnit(houseID, theNewUnit);
			int idHouse = houseID;

			return "redirect:/unit/showUnits/" + idHouse;
		}

	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("idUnit") int theId, Model theModel) {

		// get house from service
		Unit theSavedUnit = (Unit) houseService.getObject("FROM Unit u WHERE u.idUnit='" + theId + "'");

		// set house as model attribute
		theModel.addAttribute("newUnit", theSavedUnit);

		// send to form
		return "unit-form";

	}

	@RequestMapping("/setOwnerAsRenter")
	public String setOwnerAsRenter(@ModelAttribute("newUnit") Unit theUpdatedUnit, @RequestParam("idUnit") int theId, Model theModel) {

		// get house from service
		Unit theSavedUnit = (Unit) houseService.getObject("FROM Unit u WHERE u.idUnit='" + theId + "'");
		Renter houseOwner = (Renter) houseService.getObjectByID(Person.class, theSavedUnit.getHouse().getOwner().getIdPerson());
		houseOwner.setRent(theUpdatedUnit.getRenter().getRent());
		// set house as model attribute
		theSavedUnit.getRenter().setFirstName(houseOwner.getFirstName());
		houseService.saveData(theSavedUnit);
		theModel.addAttribute("newUnit", theSavedUnit);

		// send to form
		return "unit-form";

	}

	@GetMapping(value = "/showUnits/{idHouse}")
	public String showUnits(@PathVariable("idHouse") int idHouse, Model theModel) {

		// get houses from service
		List<HouseServiceImpl> allUnits = houseService.getSelectedData(
				"FROM Unit WHERE House_idHouse = '" + idHouse + "' AND status = null ORDER BY unitName");
		House house = (House) houseService.getObject("FROM House WHERE idHouse = '" + idHouse + "'");
		UnitController.houseID = idHouse;
		// add houses to the model
		theModel.addAttribute("units", allUnits);
		theModel.addAttribute("unitsUsed", allUnits.size());
		theModel.addAttribute("house", house);
		theModel.addAttribute("noOfUnits", house.getNoOfUnits());
//		theModel.addAttribute("idHouse", house.getIdHouse());

		return "show-units";

	}

//	@RequestMapping("/saveUpdate")
//	public String saveUpdate(Model theModel, @Valid @ModelAttribute("newUnit") Unit theUpdatedUnit,
//			BindingResult theBindingResult) {
//		System.out.println("Binding result: " + theBindingResult);
//		boolean hasErrors = theBindingResult.hasErrors();
//		if (hasErrors) {
//			hasErrors = false;
//			return "unit-form";
//
//		} else {
//			// get saved house, address and owner from service
//			Unit theSavedUnit = (Unit) houseService
//					.getObject("FROM Unit where idUnit = '" + theUpdatedUnit.getIdUnit() + "'");
//			Person theSavedRenter = theSavedUnit.getRenter();
//
////		 Create updated objects from mode
//
//			Renter theUpdatedRenter = new Renter(theUpdatedUnit.getRenter().getFormOfAddress(),
//					theUpdatedUnit.getRenter().getFirstName(), theUpdatedUnit.getRenter().getLastName(),
//					theUpdatedUnit.getRenter().getTelephone(), theUpdatedUnit.getRenter().getMobile(),
//					theUpdatedUnit.getRenter().geteMail(), theUpdatedUnit.getRenter().getHasExtraAddress(),
//					theUpdatedUnit.getRenter().getRent(), theUpdatedUnit.getRenter().getMonthlyNkInAdvance(),
//					theUpdatedUnit.getRenter().getNoOfPeople(), theUpdatedUnit.getRenter().getMoveInString(),
//					theUpdatedUnit.getRenter().getMoveOutString());
//
//			// change house properties
//			theSavedHouse.setObjectName(theUpdatedHouse.getObjectName());
//			theSavedHouse.setNoOfUnits(theUpdatedHouse.getNoOfUnits());
//			theSavedHouse.setTotalAreaM2(theUpdatedHouse.getTotalAreaM2());
//
//			// change or update address properties
//			// check not really necessary as house cannot move to other address :-)
//
//			int addressUsedByOtherHouse = houseService.checkForDuplicatesByID("FROM House", theSavedAddress)[0];
//			int addressUsedByOtherPerson = houseService.checkForDuplicatesByID("FROM Person", theSavedAddress)[2];
//			if (addressUsedByOtherHouse > 1 || addressUsedByOtherPerson > 1) {
//				// create new address
//				// check if new address already exists first
//				Address newHouseAddress = (Address) houseService.getDuplicate("FROM Address",
//						theUpdatedHouse.getAddress());
//				houseService.saveData(newHouseAddress);
//				theSavedHouse.setAddress(newHouseAddress);
//			} else {
//				theSavedAddress.setStreet(theUpdatedHouse.getAddress().getStreet());
//				theSavedAddress.setHouseNo(theUpdatedHouse.getAddress().getHouseNo());
//				theSavedAddress.setPostalCode(theUpdatedHouse.getAddress().getPostalCode());
//				theSavedAddress.setCity(theUpdatedHouse.getAddress().getCity());
//				houseService.saveData(theSavedAddress);
//			}
//
//			// change owner properties
//			// update owner and owner address
//
//			int ownerUsedByOtherHouse = houseService.checkForDuplicatesByID("FROM House", theSavedOwner)[1];
//			Person newHouseOwner = (Person) houseService.getDuplicate("FROM Person", theUpdatedOwner);
//			if (ownerUsedByOtherHouse > 1) {
//				// create new owner
//				// check if new owner already exists first
//				houseService.saveData(newHouseOwner);
//				theSavedHouse.setOwner(newHouseOwner);
//			} else {
//				int ownerExists = houseService.checkForDuplicatesByID("FROM Person", theUpdatedOwner)[2];
//				if (ownerExists > 0) {
//					theSavedHouse.setOwner(newHouseOwner);
//				} else {
//					theSavedOwner.setFormOfAddress(theUpdatedOwner.getFormOfAddress());
//					theSavedOwner.setFirstName(theUpdatedOwner.getFirstName());
//					theSavedOwner.setLastName(theUpdatedOwner.getLastName());
//					theSavedOwner.setTelephone(theUpdatedOwner.getTelephone());
//					theSavedOwner.setMobile(theUpdatedOwner.getMobile());
//					theSavedOwner.seteMail(theUpdatedOwner.geteMail());
//					houseService.saveData(theSavedOwner);
//				}
//			}
//
//			// change owner address properties
//			// check if updated owner address exists
//			int ownerAddressUsedByOtherHouse = houseService.checkForDuplicatesByID("FROM House",
//					theSavedOwnerAddress)[0];
//			int ownerAddressUsedByOtherPerson = houseService.checkForDuplicatesByID("FROM Person",
//					theSavedOwnerAddress)[2];
//			Address newOwnerAddress = (Address) houseService.getDuplicate("FROM Address", theUpdatedOwnerAddress);
//			if (ownerAddressUsedByOtherHouse > 1 || ownerAddressUsedByOtherPerson > 1) {
//				// create new address
//				// check if new address already exists first
//
//				houseService.saveData(newOwnerAddress);
//				theSavedOwner.setAddress(newOwnerAddress);
//				houseService.saveData(theSavedOwner);
//			} else {
//				int ownerAddressExists = houseService.checkForDuplicatesByID("FROM Address", theUpdatedOwnerAddress)[3];
//				if (ownerAddressExists > 0) {
//					theSavedHouse.setAddress(newOwnerAddress);
//				} else {
//					theSavedOwnerAddress.setStreet(theUpdatedOwnerAddress.getStreet());
//					theSavedOwnerAddress.setHouseNo(theUpdatedOwnerAddress.getHouseNo());
//					theSavedOwnerAddress.setPostalCode(theUpdatedOwnerAddress.getPostalCode());
//					theSavedOwnerAddress.setCity(theUpdatedOwnerAddress.getCity());
//					houseService.saveData(theSavedOwnerAddress);
//				}
//			}
//
//			// save changes
//			houseService.saveData(theSavedHouse);
//			return "redirect:/house/listHouses";
//		}
//
//	}
//
//	@RequestMapping("/deleteObject")
//	public String deleteObject(@RequestParam("idHouse") int theId, Model theModel) {
//		// get house from service
////	House theSavedHouse = (House) houseService.getObject("FROM House h WHERE h.idHouse='" + theId + "'");
//
//		// set status of house to "deleted"
//		houseService.deleteData(House.class, theId);
//
////	TODO: SETTING STATUS OF HOUSE ADDRESS AND HOUSE OWNER TO "DELETED" WHEN NO OTHER HOUSE USES THEM
//
////	if (duplicateAddress <= 1 && duplicateAddressInOwner <= 1) {
////		houseService.deleteData(Address.class, theSavedAddress.getIdAddress());
////	}
////	if (theSavedOwnerAddress != null) {
////		if (duplicateOwnerAddress <= 1 && duplicateOwnerAddressInHouse <= 1) {
////			houseService.deleteData(Address.class, theSavedOwnerAddress.getIdAddress());
////		}
////	}
////	if (duplicateOwner <= 1) {
////		houseService.deleteData(Owner.class, theSavedOwner.getIdPerson());
////	}
//
//		// send to form
//		return "redirect:/house/listHouses";
//
//	}

}

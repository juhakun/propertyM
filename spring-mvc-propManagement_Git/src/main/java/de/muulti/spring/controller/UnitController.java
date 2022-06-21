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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.muulti.spring.dao.DAOImpl;
import de.muulti.spring.dao.MySQLDAO;
import de.muulti.spring.entity.Address;
import de.muulti.spring.entity.House;
import de.muulti.spring.entity.Owner;
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
	public String showForm(@RequestParam("idHouse") int theId, Model theModel) {
		UnitController.houseID = theId;
		House selectedHouse = (House) houseService.getObjectByID(House.class, theId);
		if (selectedHouse.getUnits().size() < selectedHouse.getNoOfUnits()) {
			theModel.addAttribute("newUnit", new Unit());
			return "unit-form";
		} else {
			return "redirect:/unit/showHouseWithUnits";
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

			return "redirect:/unit/showHouseWithUnits";
		}

	}

	@GetMapping("/showHouseWithUnits")
	public String showHouseWithUnits(Model theModel) {

		// get houses from service
		List<HouseServiceImpl> allUnits = houseService
				.getSelectedData("FROM Unit WHERE House_idHouse = '" + houseID + "' AND status = null ORDER BY unitName");
		// add houses to the model
		theModel.addAttribute("units", allUnits);

		return "show-house";

	}

}

//		@RequestMapping("/showFormForUpdate")
//		public String showFormForUpdate(@RequestParam("idUnit") int theId, Model theModel) {
//
//			// get house from service
//			House theSavedHouse = (House) houseService.getObject("FROM House h WHERE h.idHouse='" + theId + "'");
//			if (theSavedHouse.getOwner().getAddress() == null) {
//				theSavedHouse.getOwner().setAddress(theSavedHouse.getAddress());
//			}
//
//			// set house as model attribute
//			theModel.addAttribute("savedHouse", theSavedHouse);
//
//			// send to form
//			return "unit-form";
//
//		}
//
//		@RequestMapping("/saveUpdate")
//		public String saveUpdate(Model theModel, @Valid @ModelAttribute("savedUnit") Unit theUpdatedUnit,
//				BindingResult theBindingResult) {
//			System.out.println("Binding result: " + theBindingResult);
//			boolean hasErrors = theBindingResult.hasErrors();
//			if (hasErrors) {
//				hasErrors = false;
//				return "unit-form";
//
//			} else {
//				// get saved house from service
//				Unit theSavedUnit = (Unit) houseService
//						.getObject("FROM Unit u WHERE u.idHouse='" + theUpdatedUnit.getHouse().getIdHouse() + "'");
//
//				// change house properties
////				theSavedHouse.setObjectName(theUpdatedHouse.getObjectName());
////				theSavedHouse.setNoOfUnits(theUpdatedHouse.getNoOfUnits());
////				theSavedHouse.setTotalAreaM2(theUpdatedHouse.getTotalAreaM2());
////
////				// Create updated objects from model attribute
////				Address theUpdatedAddress = new Address(theUpdatedHouse.getAddress().getStreet(),
////						theUpdatedHouse.getAddress().getHouseNo(), theUpdatedHouse.getAddress().getPostalCode(),
////						theUpdatedHouse.getAddress().getCity());
////
////				Owner theUpdatedOwner = new Owner(theUpdatedHouse.getOwner().getFormOfAddress(),
////						theUpdatedHouse.getOwner().getFirstName(), theUpdatedHouse.getOwner().getLastName(),
////						theUpdatedHouse.getOwner().getTelephone(), theUpdatedHouse.getOwner().getMobile(),
////						theUpdatedHouse.getOwner().geteMail(), theUpdatedHouse.getOwner().getHasExtraAddress());
////
////				Address theUpdatedOwnerAddress = new Address(theUpdatedHouse.getOwner().getAddress().getStreet(),
////						theUpdatedHouse.getOwner().getAddress().getHouseNo(),
////						theUpdatedHouse.getOwner().getAddress().getPostalCode(),
////						theUpdatedHouse.getOwner().getAddress().getCity());
////
////				// check for duplicates
////				Address duplicateAddress = (Address) houseService.getDuplicate("FROM Address", theUpdatedAddress);
////				Owner duplicateOwner = (Owner) houseService.getDuplicate("FROM Owner", theUpdatedOwner);
////				Address duplicateOwnerAddress = (Address) houseService.getDuplicate("FROM Address", theUpdatedOwnerAddress);
////
////				// update address
////				if (duplicateAddress != null) {
////					System.out.println("Address exists");
////					theSavedHouse.setAddress(duplicateAddress);
////				} else {
////					houseService.saveData(theUpdatedAddress);
////					theSavedHouse.setAddress(theUpdatedAddress);
////				}
////
////				// update owner and owner address
////				if (duplicateOwner != null) {
////					System.out.println("Owner exists");
////					theSavedHouse.setOwner(duplicateOwner);
////					if (duplicateOwner.getAddress() == null) {
////						houseService.saveData(theSavedHouse);
////					} else if (duplicateOwner.getAddress() != null) {
////						if (duplicateOwnerAddress != null) {
////
////							duplicateOwner.setAddress(duplicateOwnerAddress);
////							houseService.saveData(duplicateOwner);
////						} else {
////							houseService.saveData(theUpdatedOwnerAddress);
////							duplicateOwner.setAddress(theUpdatedOwnerAddress);
////							houseService.saveData(duplicateOwner);
////						}
////
////					}
////
////				} else if (duplicateOwner == null) {
////					houseService.saveData(theUpdatedOwner);
////					if (theUpdatedOwnerAddress.getStreet().equals(theUpdatedAddress.getStreet())
////							&& theUpdatedOwnerAddress.getHouseNo().equals(theUpdatedAddress.getHouseNo())
////							&& theUpdatedOwnerAddress.getPostalCode().equals(theUpdatedAddress.getPostalCode())
////							&& theUpdatedOwnerAddress.getCity().equals(theUpdatedAddress.getCity())) {
////						theUpdatedOwner.setHasExtraAddress("false");
////						theUpdatedOwner.setAddress(null);
////						houseService.saveData(theUpdatedOwner);
////					} else {
////						theUpdatedOwner.setHasExtraAddress("true");
////						theUpdatedOwner.setAddress(theUpdatedOwnerAddress);
////						theSavedHouse.setOwner(theUpdatedOwner);
////					}
////
////				}
//
//				// save changes
//				houseService.saveData(theSavedUnit);
//				return "redirect:/unit/listUnits";
//			}
//
//		}
//
//		@RequestMapping("/deleteObject")
//		public String deleteObject(@RequestParam("idHouse") int theId, Model theModel) {
//			// get house from service
////			House theSavedHouse = (House) houseService.getObject("FROM House h WHERE h.idHouse='" + theId + "'");
//
//			// set status of house to "deleted"
//			houseService.deleteData(House.class, theId);
//
////			TODO: SETTING STATUS OF HOUSE ADDRESS AND HOUSE OWNER TO "DELETED" WHEN NO OTHER HOUSE USES THEM
//
////			if (duplicateAddress <= 1 && duplicateAddressInOwner <= 1) {
////				houseService.deleteData(Address.class, theSavedAddress.getIdAddress());
////			}
////			if (theSavedOwnerAddress != null) {
////				if (duplicateOwnerAddress <= 1 && duplicateOwnerAddressInHouse <= 1) {
////					houseService.deleteData(Address.class, theSavedOwnerAddress.getIdAddress());
////				}
////			}
////			if (duplicateOwner <= 1) {
////				houseService.deleteData(Owner.class, theSavedOwner.getIdPerson());
////			}
//
//			// send to form
//			return "redirect:/house/listHouses";
//
//		}
//
//	
//
//
//}

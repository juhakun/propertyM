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
import de.muulti.spring.entity.Counter;
import de.muulti.spring.entity.House;
import de.muulti.spring.entity.Owner;
import de.muulti.spring.entity.Person;
import de.muulti.spring.entity.Renter;
import de.muulti.spring.entity.Unit;
import de.muulti.spring.service.HouseService;
import de.muulti.spring.service.HouseServiceImpl;

@Controller
@RequestMapping("/counter")
public class CounterController {

	// need to inject the house service layer
	@Autowired
	@Qualifier("HouseServiceImpl")
	private HouseService houseService;

	private static int houseID;
	public static ArrayList<String> classUnitNames = new ArrayList<>();

	// add an initbinder to remove whitespace for validation
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@RequestMapping(value = "/showForm")
	public String showForm(@RequestParam("idHouse") int idHouse, Model theModel) {
//		House selectedHouse = (House) houseService.getObjectByID(House.class, idHouse);
		ArrayList<String> unitNames = new ArrayList<>();
		theModel.addAttribute("newCounter", new Counter());
		List<HouseServiceImpl> houseUnits = houseService
				.getSelectedData("FROM Unit WHERE House_idHouse = '" + idHouse + "'");

		unitNames.add("Wohneinheit auswählen");
		for (int i = 0; i < houseUnits.size(); i++) {
			Unit unit = (Unit) houseUnits.get(i);
			String unitName = unit.getUnitName();
			unitNames.add(unitName);
		}
		classUnitNames = unitNames;
		theModel.addAttribute("unitNames", unitNames);

		return "counter-form";

	}

	@RequestMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("newCounter") Counter newCounter, BindingResult theBindingResult,
			Model theModel) {
		System.out.println("Binding result: " + theBindingResult);
		int idHouse = houseID;

		if (theBindingResult.getErrorCount() > 1
				|| (theBindingResult.getErrorCount() == 1 && !(theBindingResult.hasFieldErrors("unitName")
						&& newCounter.getHouseOrUnit().equals("Hauszähler")))) {
			theModel.addAttribute("unitNames", classUnitNames);
			System.out.println(classUnitNames.get(0));
			return "counter-form";

		} else {
			if (newCounter.getHouseOrUnit().equals("Hauszähler")) {
				newCounter.setUnitName("-");
			} else {
			System.out.println(houseID + newCounter.getUnitName());
// get unit ID
			Unit unit = (Unit) houseService.getObject("FROM Unit WHERE unitName = '" + newCounter.getUnitName() + "'");
			newCounter.setUnit(unit);
			}
//			newCounter.setDateCount(LocalDate.parse(newCounter.getDateCountString()));
//			newCounter.setSqlDateCount(Date.valueOf(newCounter.getDateCount()));
			houseService.saveData(newCounter);
			houseService.addCounter(idHouse, newCounter);
//			
//
			return "redirect:/counter/showCounters/" + idHouse;
		}

	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("counterNo") int theId, Model theModel) {

		// get house from service
		Counter theSavedCounter = (Counter) houseService.getObject("FROM Counter c WHERE c.counterNo='" + theId + "'");

		// set house as model attribute
		theModel.addAttribute("newCounter", theSavedCounter);

		// send to form
		return "counter-form";

	}

	@GetMapping(value = "/showCounters/{idHouse}")
	public String showCounters(@PathVariable("idHouse") int idHouse, Model theModel) {
		houseID = idHouse;
		System.out.println("Sorting");
		// get houses from service
		House house = (House) houseService.getObject("FROM House WHERE idHouse = '" + idHouse + "'");
		List<HouseServiceImpl> counters = houseService
				.getSelectedData("FROM Counter WHERE House_idHouse = '" + idHouse + "' ORDER BY " + house.getTableSort() + " ");
		
		CounterController.houseID = idHouse;
		theModel.addAttribute("counters", counters);
		theModel.addAttribute("counter", new Counter());
		theModel.addAttribute("unit", null);
		theModel.addAttribute("house", house);
		theModel.addAttribute("noOfCounters", counters.size());
		List<HouseServiceImpl> houseUnits = houseService
				.getSelectedData("FROM Unit WHERE House_idHouse = '" + idHouse + "'");
		ArrayList<String> unitNames = new ArrayList<>();
		unitNames.add("Wohneinheit auswählen");
		for (int i = 0; i < houseUnits.size(); i++) {
			Unit unit = (Unit) houseUnits.get(i);
			String unitName = unit.getUnitName();
			unitNames.add(unitName);
		}
		theModel.addAttribute("unitNames", unitNames);

		return "show-counters";

	}

	@GetMapping(value = "/showCountersOfUnit/{idUnit}")
	public String showCountersOfUnit(@PathVariable("idUnit") int idUnit,
			Model theModel) {
int idHouse = houseID;
		// get houses from service
		List<HouseServiceImpl> counters = houseService
				.getSelectedData("FROM Counter WHERE Unit_idUnit = '" + idUnit + "'");
		Unit unit = (Unit) houseService.getObject("FROM Unit WHERE idUnit = " + idUnit + "");
		House house = (House) houseService.getObject("FROM House WHERE idHouse = '" + unit.getHouse().getIdHouse() + "'");

		theModel.addAttribute("counters", counters);
		theModel.addAttribute("counter", new Counter());
		theModel.addAttribute("unit", unit);
		theModel.addAttribute("house", house);
		theModel.addAttribute("noOfCounters", counters.size());
		List<HouseServiceImpl> houseUnits = houseService
				.getSelectedData("FROM Unit WHERE House_idHouse = '" + idHouse + "'");
		ArrayList<String> unitNames = new ArrayList<>();
		unitNames.add("Wohneinheit auswählen");
		for (int i = 0; i < houseUnits.size(); i++) {
			Unit unit2 = (Unit) houseUnits.get(i);
			String unitName = unit2.getUnitName();
			unitNames.add(unitName);
		}
		theModel.addAttribute("unitNames", unitNames);

		return "show-counters";

	}
	
//	@GetMapping(value = "/sortCounter/{idHouse}")
//	public String sortCounters(@PathVariable("idHouse") int idHouse) {
//		 System.out.println("Hello");
//		 return "counter-form";
//	}

}

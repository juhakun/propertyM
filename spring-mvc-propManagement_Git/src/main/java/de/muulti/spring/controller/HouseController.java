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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import de.muulti.spring.dao.DAOImpl;
import de.muulti.spring.dao.MySQLDAO;
import de.muulti.spring.entity.House;

@Controller
@RequestMapping("/house")
public class HouseController {

	// need to inject the house dao
	@Autowired
	@Qualifier("DAOImpl")
	private MySQLDAO houseDAO;

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

	@RequestMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("house") House theHouse, BindingResult theBindingResult) {
		System.out.println("Binding result: " + theBindingResult);
		boolean hasErrors = theBindingResult.hasErrors();
		if (hasErrors) {
			hasErrors = false;
			return "house-form";

		} else {
//			houseDAO.saveObject(theHouse.getAddress());
			houseDAO.saveObject(theHouse);
			return "house-confirmation";
		}
	}

	@RequestMapping("/listHouses")
	public String listHouses(Model theModel) {

		// get houses from dao
		List<DAOImpl> theHouses = houseDAO.getData("House");
		// add houses to the model
		theModel.addAttribute("houses", theHouses);

		return "list-houses";

	}

}

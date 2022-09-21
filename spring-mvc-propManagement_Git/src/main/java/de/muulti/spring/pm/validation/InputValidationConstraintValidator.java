package de.muulti.spring.pm.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InputValidationConstraintValidator implements ConstraintValidator<InputValidation, String> {

	private String chooseOption;
	private int id;

	@Override
	public void initialize(InputValidation dropDownChoiceMade) {
		chooseOption = dropDownChoiceMade.value();
		id = dropDownChoiceMade.id();
	}

	@Override
	public boolean isValid(String theCode, ConstraintValidatorContext arg1) {

		boolean result = false;

		if (id == 1) {

			if (theCode != null) {
				result = !theCode.startsWith(chooseOption);
			} else {
				result = true;
			}
		} else if (id == 2) {
			if (theCode != null) {

				result = !theCode.contains(chooseOption);
			} else {
				result = true;
			}
		}
		return result;
	}

}

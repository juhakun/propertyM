package de.muulti.spring.pm.validation;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = InputValidationConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface InputValidation {
	
	// define default course code
	public String value() default "LUV";
	
	// define default error message
	public String message() default "must start with LUV"; 
	
	// define default groups
	public Class <?> [] groups() default {};
	
	// define default payloads
	public Class <? extends Payload> [] payload() default {};

	public int id();
	

}

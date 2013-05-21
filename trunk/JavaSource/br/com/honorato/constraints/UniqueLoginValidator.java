package br.com.honorato.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueLoginValidator implements ConstraintValidator<UniqueLoginCheck, String> {

    private String login;

    public void initialize(UniqueLoginCheck constraintAnnotation) {
        this.login = constraintAnnotation.value();
    }

    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
    	
    	/*TODO: EFETUAR VALIDAÇÃO DO LOGIN*/ 

        if (object == null)
            return true;
        else
        	return true;

/*        if (caseMode == CaseMode.UPPER)
            return object.equals(object.toUpperCase());
        else
            return object.equals(object.toLowerCase());*/
    }

}

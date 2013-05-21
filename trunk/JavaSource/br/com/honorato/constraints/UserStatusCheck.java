package br.com.honorato.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.honorato.dao.enumeration.EUserStatus;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = UserStatusValidator.class)
@Documented
public @interface UserStatusCheck {
	
	String message() default "{com.mycompany.constraints.checkcase}";

	String[] properties();
	 
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
///    UserStatusEnum value();	

}



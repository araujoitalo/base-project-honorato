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

/*TODO: Tentar transformar esta classe em uma classe genérica de validação de campo unique*/
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueLoginValidator.class)
@Documented
public @interface UniqueLoginCheck {
	
    String message() default "{br.com.honorato.constraints.uniqueLogin}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    String value();

}

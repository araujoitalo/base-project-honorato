package br.com.honorato.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SampleStatusValidator implements ConstraintValidator<SampleStatusCheck, Object> {

	private String[] fields;

	public void initialize(SampleStatusCheck constraintAnnotation) {
		this.fields = constraintAnnotation.properties();
	}

	public boolean isValid(Object object, ConstraintValidatorContext constraintContext) {
		
		boolean out = false;

		if (object == null)
			return true;
		
		System.out.println(object);
		
		
//		for (UserStatusEnum userStatusEnumList : UserStatusEnum.values()) {
//			
//			if (userStatusEnum == userStatusEnumList){
//				out = true;
//			}
//		}
		
		return out;
	}

}
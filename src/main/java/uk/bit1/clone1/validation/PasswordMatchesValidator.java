package uk.bit1.clone1.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import uk.bit1.clone1.vo.Password;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, ObjectWithMatchingPasswords> {

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {

	}

	@Override
	public boolean isValid(ObjectWithMatchingPasswords object, ConstraintValidatorContext context) {
		return validatePasswords(object.getPassword(), object.getMatchingPassword());
	}
	
	private boolean validatePasswords(Password password1, Password password2) {
		if(password1.equals(password2)) {
			return true;
		}
		return false;
	}

}

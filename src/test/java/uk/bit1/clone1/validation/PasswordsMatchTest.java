package uk.bit1.clone1.validation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import uk.bit1.clone1.dto.UserDto;
import uk.bit1.clone1.vo.Password;


public class PasswordsMatchTest implements ObjectWithMatchingPasswords {

	private Password password;
	
	private Password matchingPassword;
	
	@Override
	public Password getPassword() {
		return password;
	}

	@Override
	public Password getMatchingPassword() {
		return matchingPassword;
	}

	private static ValidatorFactory validatorFactory;

	private static Validator validator;

	@BeforeClass
	public static void setup() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@Test
	public void validPassword_noViolations() {
		this.password = new Password("password");
		this.matchingPassword = new Password("password");
		Set<ConstraintViolation<PasswordsMatchTest>> violations = validator.validate(this);
		assertThat(violations.size()).isEqualTo(0);
	}

	@Test
	public void passwordsDoNotMatch_violations() {
		this.password = new Password("password");
		this.matchingPassword = new Password("password2");
		Set<ConstraintViolation<PasswordsMatchTest>> violations = validator.validate(this);
		assertThat(violations.size()).isEqualTo(1);
	}
}

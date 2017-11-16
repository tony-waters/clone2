package uk.bit1.clone1.validation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import uk.bit1.clone1.validation.EmailValid;

public class EmailValidatorTest {

	@EmailValid
	private String email;

	private static ValidatorFactory validatorFactory;

	private static Validator validator;

	@BeforeClass
	public static void setup() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@Test
	public void validEmail_noViolations() {
		email = "tony@bit1.uk";
		Set<ConstraintViolation<EmailValidatorTest>> violations = validator.validate(this);
		assertThat(violations.size()).isEqualTo(0);
	}

	@Test
	public void missingAtSign_violations() {
		email = "tonybit1.uk";
		Set<ConstraintViolation<EmailValidatorTest>> violations = validator.validate(this);
		assertThat(violations.size()).isEqualTo(1);
	}

	@Test
	public void missingDomain_violations() {
		email = "tony@bit1";
		Set<ConstraintViolation<EmailValidatorTest>> violations = validator.validate(this);
		assertThat(violations.size()).isEqualTo(1);
	}
	
	@Test
	public void missingStart_violations() {
		email = "@bit1.uk";
		Set<ConstraintViolation<EmailValidatorTest>> violations = validator.validate(this);
		assertThat(violations.size()).isEqualTo(1);
	}
	
	@Test
	public void missingEnd_violations() {
		email = "tony@";
		Set<ConstraintViolation<EmailValidatorTest>> violations = validator.validate(this);
		assertThat(violations.size()).isEqualTo(1);
	}
	
	@Test
	public void illegalAtCharacter_violations() {
		email = "tony@@bit1.uk";
		Set<ConstraintViolation<EmailValidatorTest>> violations = validator.validate(this);
		assertThat(violations.size()).isEqualTo(1);
	}

}

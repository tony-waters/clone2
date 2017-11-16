package uk.bit1.clone1.validation;

import uk.bit1.clone1.vo.Password;

@PasswordMatches
public interface ObjectWithMatchingPasswords {
	
	Password getPassword();
	
	Password getMatchingPassword();

}

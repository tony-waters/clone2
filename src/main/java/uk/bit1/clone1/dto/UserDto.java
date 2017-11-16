package uk.bit1.clone1.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import uk.bit1.clone1.validation.EmailValid;
import uk.bit1.clone1.validation.ObjectWithMatchingPasswords;
import uk.bit1.clone1.vo.Password;

public class UserDto implements ObjectWithMatchingPasswords {

	@NotNull
	@Size(min = 1)
	private String firstName;
	
	@NotNull
	@Size(min = 1)
	private String lastName;
	
	@NotNull
	@Size(min = 1)
	private Password password;
	
	@NotNull
	@Size(min = 1)
	private Password matchingPassword;
	
	@EmailValid
	@NotNull
	@Size(min = 1)
	private String email;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	@Override
	public Password getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(Password matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}

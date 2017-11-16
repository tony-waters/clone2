package uk.bit1.clone1.web;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import uk.bit1.clone1.dto.UserDto;

public class RegistrationController {

	@RequestMapping(value = "/user/registration", method = RequestMethod.GET)
	public String showRegistrationForm(WebRequest request, Model model) {
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		return "registration";
	}

	public ModelAndView registerUserAccount(
			@ModelAttribute("user") @Valid UserDto userDto, 
			BindingResult result, 
			WebRequest request, 
			Errors errors) {
		
		return null;
	}
}

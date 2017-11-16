package uk.bit1.clone1.web;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.bit1.clone1.model.ResponseType;
import uk.bit1.clone1.service.AnswerService;

@Controller
public class AnswerController {
	
	private static Logger log = LoggerFactory.getLogger(AnswerController.class);
	
	private final MessageSource messageSource;
	
	private final AnswerService answerService;
	
	@Autowired
	public AnswerController(AnswerService answerService, MessageSource messageSource) {
		this.answerService = answerService;
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/{surveyId}/{clientId}/{answerId}", method = RequestMethod.GET)
	public String recordSurvey(
			@PathVariable String surveyId, 
			@PathVariable String clientId, 
			@PathVariable String answerId, 
			Model model,
			Locale locale) {
		log.info("Procesing request for surveyId [{}] idGuid[{}] answerId[{}]", surveyId, clientId, answerId);

		ResponseType responseType = answerService.processAnswer(surveyId, clientId, answerId);
		
		String heading;
		String message;
		
		switch(responseType) {
			case SUCCESS:
				heading = messageSource.getMessage("message.heading", null, locale);
				message = messageSource.getMessage("message.success", null, locale);
				break;
			case DUPLICATE:
				heading = messageSource.getMessage("message.heading", null, locale);
				message = messageSource.getMessage("message.duplicate", null, locale);
				break;
			default:
				heading = messageSource.getMessage("message.error.heading", null, locale);
				message = messageSource.getMessage("message.error", null, locale);
				break;
		}
		
		model.addAttribute("message", message);
		model.addAttribute("heading", heading);
		
		return "home";
	}
	
}
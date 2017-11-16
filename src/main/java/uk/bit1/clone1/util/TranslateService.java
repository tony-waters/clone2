package uk.bit1.clone1.util;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class TranslateService {
	
	private static Logger log = LoggerFactory.getLogger(TranslateService.class);
	
	private final MessageSource messageSource;
	
	@Autowired
	public TranslateService(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	public String translate(String message) {
		Locale locale = Locale.getDefault();
		return messageSource.getMessage(message, null, locale);
	}
	
	public String translate(String message, Locale locale) {
		return messageSource.getMessage(message, null, locale);
	}

}

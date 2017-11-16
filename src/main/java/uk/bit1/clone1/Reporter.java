package uk.bit1.clone1;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.bit1.clone1.util.TranslateService;

/**
 * Report the active properties of interest.
 * 
 * @author Tony Waters
 *
 */
@Component
public class Reporter implements InitializingBean {
	
	private static Logger log = LoggerFactory.getLogger(InitializingBean.class);
	
//	private final MessageSource messageSource;

	private final TranslateService translateService;
	
	@Autowired
	public Reporter(TranslateService translateService) {
		this.translateService = translateService;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Locale locale = Locale.getDefault();
		log.info("Using Locale: " + locale);
		log.info("+++++++++++++++ Active Properties +++++++++++++++");
		log.info("Using the following messages:");
		log.info("message.error.heading: " + translateService.translate("message.error.heading"));
		log.info("message.error: " + translateService.translate("message.error"));
		log.info("message.heading: " + translateService.translate("message.heading"));
		log.info("message.success: " + translateService.translate("message.success"));
		log.info("message.duplicate: " + translateService.translate("message.duplicate"));
		log.info("+++++++++++++ Active Properties End +++++++++++++");
	}

}

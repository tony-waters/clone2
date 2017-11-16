package uk.bit1.clone1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class Application extends SpringBootServletInitializer {
	
	private static Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws Exception {
		log.info("Using classpath " + System.getProperty("java.class.path"));
		log.info("Starting system");
		ApplicationContext context = SpringApplication.run(Application.class, args);
		
		log.info("Using classpath " + System.getProperty("java.class.path"));
		
		String [] activeProfiles = context.getEnvironment().getActiveProfiles();
		
		if(activeProfiles.length == 0) {
			log.info("No Active Profiles");
		} else {
			log.info("Active Profiles=>");
			for(String profile : activeProfiles) {
				log.info("   Profile: " + profile);
			}
		}
	}

}

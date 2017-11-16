package uk.bit1.clone1;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * When auto-creating a database we must create the schema first.
 * 
 * The usual Spring hooks don't work for this.
 * 
 * @author Tony Waters
 *
 */
@Profile("scratch")
@Configuration
public class SchemaConfig {
	
	private static Logger log = LoggerFactory.getLogger(SchemaConfig.class);

	@Bean
	public DataSource dataSource() throws SQLException {
		log.info("Running script to create schema");
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		builder.addScript("classpath:create_schema.sql");
		return builder.setType(EmbeddedDatabaseType.HSQL).build();
	}

}

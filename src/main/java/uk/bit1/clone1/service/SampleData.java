package uk.bit1.clone1.service;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;

/**
 * Add some sample data for testing/demo.
 * 
 * @author Tony Waters
 *
 */
@Profile("sample-data")
@Component
@EnableScheduling
public class SampleData {
	
	private static Logger log = LoggerFactory.getLogger(SampleData.class);
	
	@Autowired
	private DataSource dataSource;
	
	@Scheduled(fixedDelay=600000)
	public void setUpSampleData() {
		log.info("===============> ");
		log.info("===============> ");
		log.info("===============> Populating sample data");
		log.info("===============> ");
		log.info("===============> ");
		
		Operation operation = sequenceOf(
				deleteAllFrom("G00.CUSTOMERSATISFACTIONSURVEYANSWERS"),
				insertInto("G00.CUSTOMERSATISFACTIONSURVEYANSWERS")
					.columns("ID", "SURVEY_ID", "CLIENT_ID", "ANSWER_ID", "TIMESTAMP")
					.values("1", "acp-survey", "client-1", null, null)
					.values("2", "aem-survey", "client-1", null, null)
					.build());
		DbSetup dbSetup = new DbSetup( new DataSourceDestination(dataSource), operation );
		dbSetup.launch();
	}
	
}

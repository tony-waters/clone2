package uk.bit1.clone1.web;


import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;

import uk.bit1.clone1.Application;
import uk.bit1.clone1.model.ResponseType;
import uk.bit1.clone1.service.AnswerService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
public class AnswerServiceTest {
	
	private static Logger log = LoggerFactory.getLogger(AnswerServiceTest.class);
	
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void setUp() throws Exception {
		log.info("===============> ");
		log.info("===============> ");
		log.info("===============> Populating test data");
		log.info("===============> ");
		log.info("===============> ");
		
		Operation operation = sequenceOf(
				deleteAllFrom("G00.CUSTOMERSATISFACTIONSURVEYANSWERS"),
				insertInto("G00.CUSTOMERSATISFACTIONSURVEYANSWERS")
					.columns("ID", "SURVEY_ID", "CLIENT_ID", "ANSWER_ID")
					.values("1", "SURVEY-1", "CLIENT-1", null)
					.build());
		DbSetup dbSetup = new DbSetup( new DataSourceDestination(dataSource), operation );
		dbSetup.launch();
	}

	@Test
	public void test_success() {
		ResponseType result = answerService.processAnswer("SURVEY-1", "CLIENT-1", "ANSWER-1");
		assertThat(result).isEqualTo(ResponseType.SUCCESS);
	}
	
	@Test
	public void test_missing() {
		ResponseType result = answerService.processAnswer("SURVEY-0", "CLIENT-0", "ANSWER-1");
		assertThat(result).isEqualTo(ResponseType.PROBLEM);
	}
	
	@Test
	public void test_duplicate() {
		answerService.processAnswer("SURVEY-1", "CLIENT-1", "ANSWER-1");
		ResponseType result = answerService.processAnswer("SURVEY-1", "CLIENT-1", "ANSWER-1");
		assertThat(result).isEqualTo(ResponseType.DUPLICATE);
	}
	
	@Test
	public void test_problem() {
		ResponseType result = answerService.processAnswer("SURVEY-0", "CLIENT-0", "ANSWER-1");
		assertThat(result).isEqualTo(ResponseType.PROBLEM);
	}

}

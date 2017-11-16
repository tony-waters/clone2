package uk.bit1.clone1.service;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;

import uk.bit1.clone1.Application;
import uk.bit1.clone1.service.AnswerService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class AnswerControllerTest {
	
	private static Logger log = LoggerFactory.getLogger(AnswerControllerTest.class);
	
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		
		log.info("===============> ");
		log.info("===============> ");
		log.info("===============> Populating test data");
		log.info("===============> ");
		log.info("===============> ");
		
		Operation operation = sequenceOf(
				deleteAllFrom("G00.CUSTOMERSATISFACTIONSURVEYANSWERS"),
				insertInto("G00.CUSTOMERSATISFACTIONSURVEYANSWERS")
					.columns("ID", "SURVEY_ID", "CLIENT_ID", "ANSWER_ID")
					.values("1", "survey-1", "client-1", null)
					.build());
		DbSetup dbSetup = new DbSetup( new DataSourceDestination(dataSource), operation );
		dbSetup.launch();
	}

	@Test
	public void test() throws Exception {
		MvcResult result = mockMvc.perform(get("/survey-1/client-1/1"))
			.andExpect(status().isOk())
			.andReturn();
		System.out.println("RESULT:" + result);
	}

}

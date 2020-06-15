package com.ibm.fsb.spock.service

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.awt.print.Book

import javax.sql.DataSource
import javax.transaction.Transactional

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders

import com.ibm.fsb.service.UserService
import com.ibm.fsb.spock.BaseSpec;

import spock.lang.Shared

@WebAppConfiguration
@ContextConfiguration(loader = SpringBootContextLoader.class)
public class UserServiceSpec extends BaseSpec {
	
	@Autowired
	private ConfigurableApplicationContext context;
	@Shared
	boolean sharedSetupDone = false;
	@Autowired
	private DataSource ds;
	@Autowired
	private UserService userService;
	@Shared
	private MockMvc mockMvc;

	def setup() {
		if (!sharedSetupDone) {
			mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
			sharedSetupDone = true;
		}
		ResourceDatabasePopulator populator = new
		ResourceDatabasePopulator(context.getResource("classpath:/test-data.sql"));
        DatabasePopulatorUtils.execute(populator, ds);
	}

	@Transactional
	def "Test login RESTful POST"() {
		when:
		def result = mockMvc.perform(post("/api/login/", "${username}", "${password}"));
  
		then:
		result.andExpect(status().isOk())
	    result.andExpect(content().string(containsString(content)));

	   where:
	   username | password   || content
	   "sam"    | "passw0rd" || "You have been logged in successfully."
	   "tom"    | "12345678" || "INVALID_CREDENTIALS"
	}

}

package com.ibm.fsb.spock

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.mock.jndi.SimpleNamingContextBuilder
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import javax.sql.DataSource

@ContextConfiguration(locations = ["classpath:applicationContext.yml"])
@WebAppConfiguration("src/main/resources")
class BaseSpec extends Specification {

    protected MockMvc mockMvc

    @Autowired
    protected WebApplicationContext wac

    def setupSpec() {
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("classpath:dataSource.xml")
        DataSource dsUtils = (DataSource) app.getBean("dataSourceStockMarket")
        SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder()
        builder.bind("java:/SMDS", dsUtils)
        builder.activate()
    }

    def setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build()
    }
	
}
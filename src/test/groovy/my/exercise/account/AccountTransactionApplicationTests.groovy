package my.exercise.account

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.jdbc.datasource.DataSourceUtils
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.ShallowEtagHeaderFilter
import spock.lang.Specification
import java.sql.Connection
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountTransactionApplicationTests extends Specification{

    @Autowired
    protected WebApplicationContext context

    protected MockMvc mvc

    def setup() {
        mvc = buildDefaultMockMvc()
    }

    def "Get all Accounts"() {
        when: "Call - get all Accounts"

        def response = mvc.perform(get("/api/accounts")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())

        then: "We expect we got a successful response"
        response.andExpect(status().isOk())
        println (getClass().protectionDomain.codeSource.location.path+"data" + "/" + "AllAccounts.json")
        and: "The response contains the expected data"
        response.andExpect(content().json(getAllResponseFileData("AllAccounts.json")))
    }


    def "Get a valid Account"() {
        when: "Call get an Account"
        Long resourceId = 585309209

        def response = mvc.perform(get("/api/accounts/"+resourceId)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())

        then: "We expect we got a successful response"
        response.andExpect(status().isOk())
        println (getClass().protectionDomain.codeSource.location.path+"data" + "/" + "ValidAccount.json")
        and: "The response contains the expected data"
        response.andExpect(content().json(getAllResponseFileData("ValidAccount.json")))
    }

    def "Get Transactions for an Account"() {
        when: "Call get an Account"
        Long resourceId = 791066619

        def response = mvc.perform(get("/api/accounts/"+resourceId+"/transactions")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())

        then: "We expect we got a successful response"
        response.andExpect(status().isOk())
        println (getClass().protectionDomain.codeSource.location.path+"data" + "/" + "AccountWithTransactions.json")
        and: "The response contains the expected data"
        response.andExpect(content().json(getAllResponseFileData("AccountWithTransactions.json")))
    }

    String getAllResponseFileData(String filename) {
        return readResourceFile("data" + "/" + filename)
    }

    void runDbScript(String scriptName, boolean commit) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator()
        populator.addScript(new ClassPathResource(scriptName))
        Connection connection = null

        try {
            connection = DataSourceUtils.getConnection()
            populator.populate(connection)
            if (commit) {
                connection.commit()
            }
        }
        finally {
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection)
            }
        }
    }

    MockMvc buildDefaultMockMvc() {
        return MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new ShallowEtagHeaderFilter())
                .build()
    }


    String readResourceFile(String filename) {
        // ensure that the input stream is closed when we are done with it

        String response = new File(getClass().protectionDomain.codeSource.location.path + filename).text

            return new String(response)

    }

}
package com.epam.jmp.bolat.atdd.test;

import com.epam.jmp.bolat.atdd.App;
import com.epam.jmp.bolat.atdd.PersonController;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Bolat_Tussupzhanov on 3/15/2017.
 */
@SpringBootTest(classes= App.class)
@AutoConfigureMockMvc
public class SpringIntegrationTest {
    private MockMvc mockMvc;

    private PersonController personController;

    private ResultActions resultActions;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @When("^the client submits POST request at /person following data (.+)$")
    public void the_client_submits_POST_request_person(String data) throws Throwable{
        resultActions = this.mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON_UTF8).content(data));
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        resultActions.andExpect(status().is(statusCode));
    }

    @And("^the client receives id of added person (.+)$")
    public void the_client_receives_id_of_added_person(String personId) throws Throwable {
        resultActions.andExpect(content().string(personId));
    }
}

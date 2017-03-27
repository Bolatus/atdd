package com.epam.jmp.bolat.atdd.test;

import com.epam.jmp.bolat.atdd.App;
import com.epam.jmp.bolat.atdd.PersonController;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.SmartContextLoader;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.AbstractGenericWebContextLoader;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Bolat_Tussupzhanov on 3/15/2017.
 */
@ContextConfiguration(classes=App.class, loader=AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class WebStepDefs {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private ResultActions resultActions;


    //Testing add person
    @When("^the client submits POST request at /person following data (.+)$")
    public void the_client_submits_POST_request_person(String data) throws Throwable{
        Assert.notNull(webApplicationContext);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        resultActions = this.mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON_UTF8).content(data));
    }

    @Then("^after POST request the client receives status code of (\\d+)$")
    public void after_post_the_client_receives_status_code_of(int statusCode) throws Throwable {
        resultActions.andExpect(status().is(statusCode));
    }

    @And("^the client receives id of added person (.+)$")
    public void the_client_receives_id_of_added_person(String personId) throws Throwable {
        resultActions.andExpect(content().string(personId));
    }

    //Testing fetch all people
    @When("^the client submits GET request at /person$")
    public void the_client_submits_GET_request_person() throws Throwable{
        Assert.notNull(webApplicationContext);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.resultActions = mockMvc.perform(get("/person"));
    }

    @Then("^after GET request the client receives status code of (\\d+)$")
    public void after_get_request_the_client_receives_status_code_of(int statusCode) throws Throwable {
        resultActions.andExpect(status().isOk());
    }

    @And("^the client receives arraylist json (.+)$")
    public void the_client_receives_arraylist_json(String json) throws Throwable {
        resultActions.andExpect(content().string(json));
    }
}

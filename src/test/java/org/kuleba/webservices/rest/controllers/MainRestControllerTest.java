package org.kuleba.webservices.rest.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuleba.webservices.rest.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainRestControllerTest {

    @Autowired
    private MainRestController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindContactsLastPage() throws Exception {
        this.mockMvc.perform(get("/hello/contacts/{page}", 6)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"last\" : true")));
    }

    @Test
    public void testFindContactsNegativePageNumber() throws Exception {
        this.mockMvc.perform(get("/hello/contacts/{page}", -1)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"first\" : true")));
    }

    @Test
    public void testFindContactsByRegex() throws Exception {
        String regex = ".*[A].*$";
        this.mockMvc.perform(get("/hello/contacts?nameFilter=" + regex)).andDo(print())
                .andExpect(status().isOk()).andExpect(content().string(not(containsString("A"))));
    }

}

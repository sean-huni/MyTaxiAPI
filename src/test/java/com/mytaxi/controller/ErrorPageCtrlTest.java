package com.mytaxi.controller;


import com.mytaxi.MyTaxiApiApp;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.controller
 * USER      : sean
 * DATE      : 12-Fri-Oct-2018
 * TIME      : 16:57
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MyTaxiApiApp.class)
@WebAppConfiguration(value = "src/main/resources/templates")
public class ErrorPageCtrlTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorPageCtrlTest.class);

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @BeforeAll
    public static void setup() {
        LOGGER.info("Commencing {} tests...", ErrorPageCtrlTest.class.getSimpleName());
    }

    @AfterAll
    public static void tearDown() {
        LOGGER.info("Completed {} tests...", ErrorPageCtrlTest.class.getSimpleName());
    }

    @BeforeEach
    public void pretest() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void handleError() throws Exception {
        mockMvc.perform(get("/error")).andDo(print())
                .andExpect(view().name("404"));
    }
}
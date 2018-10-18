package com.mytaxi;

import com.mytaxi.controller.CarControllerIntTest;
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
import org.springframework.web.util.NestedServletException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi
 * USER      : sean
 * DATE      : 12-Fri-Oct-2018
 * TIME      : 20:32
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MyTaxiApiApp.class)
@WebAppConfiguration(value = "src/main/resources/templates")
public class SecurityTest {
    private static final String THROWN_ERROR_MSG = "Request processing failed; nested exception is org.springframework.security.authentication.AuthenticationCredentialsNotFoundException: An Authentication object was not found in the SecurityContext";
    private static final Logger LOGGER = LoggerFactory.getLogger(CarControllerIntTest.class);

    /*
        TEST THE ENDPOINTS TO AVOID UNAUTHENTICATED ACCESS.
     */

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @BeforeAll
    public static void setup() {
        LOGGER.info("Kicking off Security Tests...");
    }

    @AfterAll
    public static void tearDown() {
        LOGGER.info("Completed Security Tests...");
    }

    @BeforeEach
    public void pretest() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    /**
     * Expected: Authentication-Credentials-Not-Found-Exception to pass the test.
     */
    @Test
    public void givenNoAuthentication_whenFindDriverByID_thenThrowAuthenticationCredentialsNotFoundException() {
        LOGGER.debug("Testing Driver-Controller for Authentication...");
        Throwable exception = assertThrows(NestedServletException.class, ()
                -> mockMvc.perform(get("/v1/drivers/5").contentType("application/json;charset=UTF-8")).andDo(print())
                .andExpect(status().isOk()));

        assertEquals(THROWN_ERROR_MSG, exception.getMessage());
        assertNotNull(exception.getMessage());
        assertTrue(exception.getMessage().contains("AuthenticationCredentialsNotFoundException"));
    }

    /**
     * Expected: Authentication-Credentials-Not-Found-Exception to pass the test.
     */
    @Test
    public void givenNoAuthentication_whenFindCarByLicenceNo_thenThrowAuthenticationCredentialsNotFoundException() {
        LOGGER.debug("Testing Car-Controller for Authentication...");
        Throwable exception = assertThrows(NestedServletException.class, ()
                -> mockMvc.perform(get("/v1/cars/303-707").contentType("application/json;charset=UTF-8")).andDo(print())
                .andExpect(status().isOk()));

        assertEquals(THROWN_ERROR_MSG, exception.getMessage());
        assertNotNull(exception.getMessage());
        assertTrue(exception.getMessage().contains("AuthenticationCredentialsNotFoundException"));
    }

}

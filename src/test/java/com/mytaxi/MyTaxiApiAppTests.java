package com.mytaxi;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MyTaxiApiApp.class)
public class MyTaxiApiAppTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyTaxiApiAppTests.class);

    @Autowired
    private WebApplicationContext wac;

    @BeforeAll
    public static void setup() {
        LOGGER.info("Kicking off {} Tests...", MyTaxiApiAppTests.class.getSimpleName());
    }

    @AfterAll
    public static void tearDown() {
        LOGGER.info("Completed {} Tests...", MyTaxiApiAppTests.class.getSimpleName());
    }


    @Test
    public void givenWebApplicationContext_whenServletContextIsSet_thenLoadsHomeController() {
        ServletContext servletContext = wac.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(wac.getBean("homeController"));
    }
}

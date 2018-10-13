package com.mytaxi.controller;

import com.mytaxi.MyTaxiApiApp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.controller
 * USER      : sean
 * DATE      : 11-Thu-Oct-2018
 * TIME      : 18:21
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MyTaxiApiApp.class)
public class HomeControllerIntTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeControllerIntTest.class);

    @Before
    public void setUp() {
        LOGGER.info("Setup completed...");
    }

    @After
    public void tearDown() {
        LOGGER.info("Test completed...");
    }

    @Test
    public void home() {
        final HomeController homeController = new HomeController();
        final String viewName = homeController.home();

        assertEquals("redirect:swagger-ui.html", viewName);
    }
}
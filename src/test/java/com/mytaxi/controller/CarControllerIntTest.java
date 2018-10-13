package com.mytaxi.controller;

import com.google.gson.Gson;
import com.mytaxi.MyTaxiApiApp;
import com.mytaxi.datatransferobject.SelectDTO;
import com.mytaxi.domainvalue.SelectionState;
import com.mytaxi.util.AuthenticationUtil;
import org.junit.jupiter.api.*;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.controller
 * USER      : sean
 * DATE      : 11-Thu-Oct-2018
 * TIME      : 14:20
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@SpringBootTest(classes = MyTaxiApiApp.class)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration(value = "src/main/resources/templates")
public class CarControllerIntTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarControllerIntTest.class);
    private static final String DESIGNATED_ROLE = "ROLE_ADMIN";

    /*
        ...INTEGRATION TESTS...
     */

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @BeforeAll
    public static void setUpAll() {
        LOGGER.info("{} tests commenced...", CarControllerIntTest.class.getSimpleName());
    }

    @AfterAll
    public static void tearDownAll() {
        LOGGER.info("{} tests completed...", CarControllerIntTest.class.getSimpleName());
    }

    @BeforeEach
    public void pretest() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @BeforeEach
    public void setUp() {
        LOGGER.info("Configuring Authentication...");
        AuthenticationUtil.configureAuthentication(DESIGNATED_ROLE);
    }

    @AfterEach
    public void tearDown() {
        LOGGER.info("Clearing Authentication...");
        AuthenticationUtil.clearAuthentication();
    }

    @Test
    public void givenAnyCarStatus_whenUserSearchesCarsByStatus_thenReturnListOf2Cars() throws Exception {
        mockMvc.perform(get("/v1/cars?carStatus=READY").contentType("application/json;charset=UTF-8")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].carId").value(1001))
                .andExpect(jsonPath("$[1].carId").value(2001));
    }

    @Test
    public void givenStatusREADY_whenUserSearchesCarsByLicenceNo_thenReturnListOfCarDTO() throws Exception {
        mockMvc.perform(get("/v1/cars/303-707").contentType("application/json;charset=UTF-8")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.carId").value(1001));
    }

    @Test
    public void givenDriverIdAndSelectionStateAndLicenceNo_whenSelectingACar_thenUpdateCarSelection() throws Exception {
        final Gson gson = new Gson();
        final SelectDTO selection = new SelectDTO(5L, SelectionState.SELECT, "303-707");
        final String jsonString = gson.toJson(selection);

        LOGGER.debug("JSON String HTTP.PUT: \n{}", jsonString);

        mockMvc.perform(put("/v1/cars/select")
                .content(jsonString)
                .contentType("application/json;charset=UTF-8")).andDo(print())
                .andExpect(status().isCreated());
    }


    @Test
    public void givenLicenceNoMakeModelSeatCount_whenUserSearchesCarsWithOptionalAttributes_thenReturnListOfCarDTO() throws Exception {
        mockMvc.perform(get("/v1/cars")
                .contentType("application/json;charset=UTF-8")
                .requestAttr("licenceNo", "303-707")
                .requestAttr("make", "bmw")
                .requestAttr("model", "x6")
                .requestAttr("seatCount", 6)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.[0].carId").value(1001));
    }

}
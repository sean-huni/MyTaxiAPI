package com.mytaxi.controller;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.SelectDTO;
import com.mytaxi.domainvalue.CarStatus;
import com.mytaxi.domainvalue.SelectionState;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.controller
 * USER      : sean
 * DATE      : 11-Thu-Oct-2018
 * TIME      : 14:20
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */

@ExtendWith(SpringExtension.class)
public class CarControllerUnitTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarControllerUnitTest.class);

    @Mock
    private CarService carService;
    private CarController carController;
    private List<CarDTO> carDTOListReq;

    @BeforeAll
    public static void setUpAll() {
        LOGGER.info("{} tests commenced...", CarControllerUnitTest.class.getSimpleName());
    }

    @AfterAll
    public static void tearDownAll() {
        LOGGER.info("{} tests completed...", CarControllerUnitTest.class.getSimpleName());
    }

    @BeforeEach
    public void setUp() {
        this.carService = Mockito.spy(carService);
        MockitoAnnotations.initMocks(this);

        carController = new CarController(carService);
        carDTOListReq = new ArrayList<>();

        LOGGER.info("Mocking & Spying on Car Service...");
    }

    @AfterEach
    public void tearDown() {
        LOGGER.info("Mocking & Spying on Car Service...");
    }

    @Test
    public void givenAnyCarStatus_whenUserSearchesCarsByStatus_thenReturnListOf2Cars() throws Exception {
        final CarDTO carDTO1 = new CarDTO();
        final CarDTO carDTO2 = new CarDTO();

        carDTO1.setNextService(Date.from(new Date().toInstant().plus(60, ChronoUnit.DAYS)));
        carDTO1.setMileage(100.15);
        carDTO1.setLicensePlate("303-707");
        carDTO1.setCarStatus(CarStatus.READY);
        carDTO1.setAverageRating(4.8);
        carDTO1.setCarId(1L);
        carDTO1.setId(1L);

        carDTO2.setNextService(Date.from(new Date().toInstant().plus(10, ChronoUnit.DAYS)));
        carDTO2.setMileage(500.15);
        carDTO2.setLicensePlate("303-606");
        carDTO2.setCarStatus(CarStatus.READY);
        carDTO2.setAverageRating(4.2);
        carDTO2.setCarId(2L);
        carDTO2.setId(2L);

        carDTOListReq.add(carDTO1);
        carDTOListReq.add(carDTO2);

        Mockito.doReturn(carDTOListReq).when(carService).findAllCarsByStatus(any());

        final List<CarDTO> carDTOListResp = carController.findCarsByStatus(CarStatus.READY);

        assertNotNull(carDTOListResp, "carDTOListReq must not be null.");
        assertEquals(2, carDTOListResp.size());
        assertEquals(carDTOListReq, carDTOListResp);

        // Lets verify that findAllCarsByStatus was invoked exactly once.
        verify(carService, times(1)).findAllCarsByStatus(any());
    }

    @Test
    public void givenStatusREADY_whenUserSearchesCarsByLicenceNo_thenReturnListOfCarDTO() throws EntityNotFoundException {
        final CarDTO carDTOReq = new CarDTO();
        carDTOReq.setNextService(Date.from(new Date().toInstant().plus(90, ChronoUnit.DAYS)));
        carDTOReq.setMileage(30.65);
        carDTOReq.setLicensePlate("303-507");
        carDTOReq.setCarStatus(CarStatus.UNAVAILABLE);
        carDTOReq.setAverageRating(4.8);
        carDTOReq.setCarId(1L);
        carDTOReq.setId(1L);

        Mockito.doReturn(carDTOReq).when(carService).findCarByLicenceNo("303-507");

        final CarDTO carDTOResp = carController.findCarByLicenceNo("303-507");

        assertNotNull(carDTOResp, "carDTOResp must not be null.");
        assertEquals(carDTOReq, carDTOResp);

        // Lets verify that findCarByLicenceNo was invoked exactly once.
        verify(carService, times(1)).findCarByLicenceNo(any());
    }

    @Test
    public void givenDriverIdAndSelectionStateAndLicenceNo_whenSelectingACar_thenReturnNothing() throws Exception {
        final String licenceNo = "303-707";
        final Long driverId = 123456879L;
        final SelectionState selectionState = SelectionState.SELECT;

        Mockito.doNothing().when(carService).toggleCar(any(), any(), any());
        carController.toggleCarByLicenceNo(new SelectDTO(driverId, selectionState, licenceNo));

        // Lets verify that findCarByLicenceNo was invoked exactly once.
        verify(carService, times(1)).toggleCar(any(), any(), any());
    }
}
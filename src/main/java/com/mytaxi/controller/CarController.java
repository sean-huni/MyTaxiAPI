package com.mytaxi.controller;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainvalue.CarStatus;
import com.mytaxi.domainvalue.Selection;
import com.mytaxi.exception.*;
import com.mytaxi.service.persistence.car.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.controller
 * USER      : sean
 * DATE      : 27-Thu-Sep-2018
 * TIME      : 11:24
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@RestController
@RequestMapping("v1/cars")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
public class CarController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);
    private final CarService carService;

    @Autowired
    public CarController(final CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<CarDTO> findCarsByStatus(@RequestParam(required = false) CarStatus carStatus) throws EntityNotFoundException {
        LOGGER.info("findCarsByStatus: " + carStatus);
        if (null == carStatus) {
            return carService.findAllCars();
        }
        return carService.findAllCarsByStatus(carStatus);
    }

    @GetMapping("/{licenceNo}")
    public CarDTO findCarByLicenceNo(@PathVariable String licenceNo) throws EntityNotFoundException {
        return carService.findCarByLicenceNo(licenceNo);
    }

    @PutMapping("/select/{licenceNo}/{driverId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void toggleCarByLicenceNo(@PathVariable Long driverId,
                                     @RequestParam Selection selection,
                                     @PathVariable String licenceNo) throws EntityNotFoundException,
            CarAlreadyInUseException, CarAlreadySelectedException, CarAlreadyDeselectedException, DriverNotOnlineException, CarUnavailableException, DeselectionNotAllowedException, NoCarSelectionException {
        carService.toggleCar(driverId, licenceNo, selection);
    }


    @GetMapping("/find-cars")
    public List<CarDTO> carDTOList(@RequestParam String licenceNo,
                                   @RequestParam String make,
                                   @RequestParam String model,
                                   @RequestParam Integer seatCount) {
        return carService.findCarCharacteristics(licenceNo, make, model, seatCount);
    }

}

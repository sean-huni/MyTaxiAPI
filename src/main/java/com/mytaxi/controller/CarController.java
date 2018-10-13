package com.mytaxi.controller;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.SelectDTO;
import com.mytaxi.domainvalue.CarStatus;
import com.mytaxi.exception.*;
import com.mytaxi.service.car.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        LOGGER.debug("findCarsByStatus: {}", carStatus);
        if (null == carStatus) {
            return carService.findAllCars();
        }
        return carService.findAllCarsByStatus(carStatus);
    }

    @GetMapping("/{licenceNo}")
    public CarDTO findCarByLicenceNo(@PathVariable String licenceNo) throws EntityNotFoundException {
        LOGGER.debug("licenceNo: {}", licenceNo);
        return carService.findCarByLicenceNo(licenceNo);
    }

    @PutMapping("/select")
    @ResponseStatus(HttpStatus.CREATED)
    public void toggleCarByLicenceNo(@RequestBody @Valid SelectDTO selectDTO) throws EntityNotFoundException,
            CarAlreadyInUseException, CarAlreadySelectedException, CarAlreadyDeselectedException,
            DriverNotOnlineException, CarUnavailableException, DeselectionNotAllowedException, NoCarSelectionException {
        LOGGER.debug("Updating toggleCarByLicenceNo >>> \ndriverId: {} \nlicenceNo: {} \nselectionState: {}",
                selectDTO.getDriverId(), selectDTO.getLicenceNo(), selectDTO.getSelectionState());
        carService.toggleCar(selectDTO.getDriverId(), selectDTO.getLicenceNo(), selectDTO.getSelectionState());
    }


    @GetMapping("/find-cars")
    public List<CarDTO> carDTOList(@RequestParam(required = false) String licenceNo,
                                   @RequestParam(required = false) String make,
                                   @RequestParam(required = false) String model,
                                   @RequestParam(required = false) Integer seatCount) {
        LOGGER.debug("toggleCarByLicenceNo >>> \nlicenceNo: {} \nmake: {} \nmodel: {} \nseatCount: {}", licenceNo, make, model, seatCount);
        return carService.findCarCharacteristics(licenceNo, make, model, seatCount);
    }

}

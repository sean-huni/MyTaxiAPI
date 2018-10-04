package com.mytaxi.service.persistence.car;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainvalue.CarStatus;
import com.mytaxi.domainvalue.Selection;
import com.mytaxi.exception.*;

import java.util.List;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.service.car
 * USER      : sean
 * DATE      : 27-Thu-Sep-2018
 * TIME      : 20:09
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public interface CarService {
    /**
     * Driver wishes to de/select a car.
     *
     * @param driverId  ID of the driver.
     * @param licenceNo Licence-Plate-Number of the car.
     * @throws EntityNotFoundException       if driver/car is not found.
     * @throws CarAlreadySelectedException   if the driver has already selected the same car.
     * @throws CarAlreadyInUseException      if the car is in use by another driver.
     * @throws CarAlreadyDeselectedException if the driver has already deselected the same car.
     */
    void toggleCar(Long driverId, String licenceNo, Selection selection) throws EntityNotFoundException,
            CarAlreadySelectedException, CarAlreadyInUseException, CarAlreadyDeselectedException, DriverNotOnlineException, NoCarSelectionException, CarUnavailableException, DeselectionNotAllowedException;

    /**
     * Find car by licenceNo.
     *
     * @param licenceNo Licence-Plate-Number of the car.
     * @return {@link CarDTO} found car.
     * @throws EntityNotFoundException if car not found.
     */
    CarDTO findCarByLicenceNo(String licenceNo) throws EntityNotFoundException;


    /**
     * Find all cars.
     *
     * @return {@link List<CarDTO>} of cars.
     */
    List<CarDTO> findAllCars();

    /**
     * Find all the cars that match the criteria.
     *
     * @param carStatus {@link CarStatus}
     * @return {@link List<CarDTO>}
     */
    List<CarDTO> findAllCarsByStatus(CarStatus carStatus) throws EntityNotFoundException;

    List<CarDTO> findCarCharacteristics(String licenceNo, String make, String model, Integer seatCount);
}

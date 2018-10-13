package com.mytaxi.service.car.impl;

import com.mytaxi.dataaccessobject.CarRepo;
import com.mytaxi.dataaccessobject.CarStateRepo;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainobject.car.CarDO;
import com.mytaxi.domainobject.car.CarStateDO;
import com.mytaxi.domainvalue.CarStatus;
import com.mytaxi.domainvalue.SelectionState;
import com.mytaxi.engine.CarSelection;
import com.mytaxi.exception.*;
import com.mytaxi.service.car.CarService;
import com.mytaxi.service.driver.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.service.car.impl
 * USER      : sean
 * DATE      : 27-Thu-Sep-2018
 * TIME      : 20:10
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Service
@Transactional
public class CarServiceImpl implements CarService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);
    private static final String NO_CAR_SELECTED_EXCEPTION_MSG = "No car selection made.";
    private static final String CAR_NOT_FOUND_EXCEPTION_MSG = "Couldn't find car with licence-Plate-Number: ";
    private final DriverService driverService;
    private final CarStateRepo carStateRepo;
    private final Converter carDOtoDTO;
    private final CarSelection carSelection;
    private CarRepo carRepo;

    @Autowired
    public CarServiceImpl(CarRepo carRepo, final DriverService driverService, final CarStateRepo carStateRepo,
                          @Qualifier("carDOtoDTO") final Converter carDOtoDTO, final CarSelection carSelection) {
        this.carRepo = carRepo;
        this.driverService = driverService;
        this.carStateRepo = carStateRepo;
        this.carDOtoDTO = carDOtoDTO;
        this.carSelection = carSelection;
    }


    /**
     * Driver wishes to select a car.
     * Only a single thread a time time should be allowed to execute this section.
     *
     * @param driverId  ID of the driver.
     * @param licenceNo Licence-Plate-Number of the car.
     * @param selectionState choice of the car, De/Select.
     * @throws EntityNotFoundException        if driver/car is not found.
     * @throws CarAlreadySelectedException    if the driver has already selected the same car.
     * @throws CarAlreadyInUseException       if the car is in use by another driver.
     * @throws DriverNotOnlineException       If teh driver isn't online.
     * @throws NoCarSelectionException
     * @throws CarUnavailableException
     * @throws DeselectionNotAllowedException if the driver tries to deselected an unassigned car.
     */
    @Override
    @Transactional
    public synchronized void toggleCar(Long driverId, String licenceNo, SelectionState selectionState) throws EntityNotFoundException,
            CarAlreadySelectedException, CarAlreadyInUseException, DriverNotOnlineException, NoCarSelectionException, CarUnavailableException, DeselectionNotAllowedException {

        DriverDO driverDO = driverService.find(driverId);
        CarStateDO carStateDO = carStateRepo.findByCarDO_LicenceNo(licenceNo)
                .orElseThrow(() -> new EntityNotFoundException(CAR_NOT_FOUND_EXCEPTION_MSG + licenceNo));

        LOGGER.debug("carStateDO Before selectionState: {}", carStateDO.toString());

        if (selectionState == SelectionState.SELECT) {
            carSelection.selectCar(driverDO, carStateDO);
        } else if (selectionState == SelectionState.DESELECT) {
            carSelection.deselectCar(driverDO, carStateDO);
        } else {
            throw new NoCarSelectionException(NO_CAR_SELECTED_EXCEPTION_MSG);
        }
        LOGGER.debug("carStateDO After selectionState: {}", carStateDO.toString());

        carStateRepo.save(carStateDO);
    }


    /**
     * Find car by licenceNo.
     *
     * @param licenceNo Licence-Plate-Number of the car.
     * @return {@link CarDTO} found car.
     * @throws EntityNotFoundException if car not found.
     */
    @Override
    public CarDTO findCarByLicenceNo(String licenceNo) throws EntityNotFoundException {
        try {
            Optional<CarDO> optionalCarDO = carRepo.findByLicenceNo(licenceNo);

            return (CarDTO) carDOtoDTO.convert(optionalCarDO.get());
        } catch (javax.persistence.EntityNotFoundException enf) {
            LOGGER.warn("Licence No: " + licenceNo + " not found.", enf);
            throw new EntityNotFoundException("Car with licence-Number: " + licenceNo + " not found.");
        }
    }

    /**
     * Find all cars.
     *
     * @return {@link List<CarDTO>} of cars.
     */
    @Override
    public List<CarDTO> findAllCars() {
        Iterable<CarDO> allCars = carRepo.findAll();
        List<CarDTO> carDTOList = getCarDTOS(allCars);
        return carDTOList;
    }

    /**
     * Find all the cars that match the criteria.
     *
     * @param carStatus {@link CarStatus}
     * @return {@link List<CarDTO>}
     */
    @Override
    public List<CarDTO> findAllCarsByStatus(CarStatus carStatus) throws EntityNotFoundException {
        try {
            return getCarDTOS(carRepo.findByCarStateDO_CarStatus(carStatus));
        } catch (javax.persistence.EntityNotFoundException enf) {
            LOGGER.warn("Record not found.", enf);
            throw new EntityNotFoundException("Car Records not found.");
        }
    }

    public List<CarDTO> findCarCharacteristics(String licenceNo, String make, String model, Integer seatCount) {
//        carRepo.findAll
        return getCarDTOS(carRepo.findAllByLicenceNoLikeOrCarType_MakeOrCarType_ModelOrCarType_SeatCount(licenceNo, make, model, seatCount));
    }

    private List<CarDTO> getCarDTOS(Iterable<CarDO> allCars) {
        List<CarDTO> carDTOList = new ArrayList<>();
        allCars.forEach(carDO -> carDTOList.add(((CarDTO) carDOtoDTO.convert(carDO))));
        return carDTOList;
    }


}

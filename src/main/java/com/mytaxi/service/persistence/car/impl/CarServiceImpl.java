package com.mytaxi.service.persistence.car.impl;

import com.mytaxi.dataaccessobject.CarRepo;
import com.mytaxi.dataaccessobject.CarStateRepo;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainobject.car.CarDO;
import com.mytaxi.domainobject.car.CarLogDO;
import com.mytaxi.domainobject.car.CarStateDO;
import com.mytaxi.domainvalue.CarStatus;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.domainvalue.Selection;
import com.mytaxi.exception.*;
import com.mytaxi.service.persistence.car.CarService;
import com.mytaxi.service.persistence.driver.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
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
    private static final String DRIVER_NOT_ONLINE_EXCEPTION_MSG = "The driver is OFFLINE. Driver must be ONLINE to select a car.";
    private static final String NO_CAR_SELECTED_EXCEPTION_MSG = "No car selection made.";
    private static final String CAR_NOT_FOUND_EXCEPTION_MSG = "Couldn't find car with licence-Plate-Number: ";
    private static final String CAR_ALREADY_SELECTED_EXCEPTION_MSG = "You've already selected this car.";
    private static final String CAR_UNAVAILABLE_EXCEPTION_MSG = "The car is unavailable for selection.";
    private static final String CAR_IN_USE_EXCEPTION_MSG = "Car already being used by another driver.";
    private static final String CAR_DESELECTION_EXCEPTION_MSG = "Not allowed to deselect a car that is not assigned to the driver.";
    private final DriverService driverService;
    private final CarStateRepo carStateRepo;
    private final Converter carDOtoDTO;
    private CarRepo carRepo;

    @Autowired
    public CarServiceImpl(CarRepo carRepo, final DriverService driverService, final CarStateRepo carStateRepo,
                          @Qualifier("carDOtoDTO") final Converter carDOtoDTO) {
        this.carRepo = carRepo;
        this.driverService = driverService;
        this.carStateRepo = carStateRepo;
        this.carDOtoDTO = carDOtoDTO;
    }


    /**
     * Driver wishes to select a car.
     * Only a single thread a time time should be allowed to execute this section.
     *
     * @param driverId  ID of the driver.
     * @param licenceNo Licence-Plate-Number of the car.
     * @param selection choice of the car, De/Select.
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
    public synchronized void toggleCar(Long driverId, String licenceNo, Selection selection) throws EntityNotFoundException,
            CarAlreadySelectedException, CarAlreadyInUseException, DriverNotOnlineException, NoCarSelectionException, CarUnavailableException, DeselectionNotAllowedException {

        DriverDO driverDO = driverService.find(driverId);
        CarStateDO carStateDO = carStateRepo.findByCarDO_LicenceNo(licenceNo)
                .orElseThrow(() -> new EntityNotFoundException(CAR_NOT_FOUND_EXCEPTION_MSG + licenceNo));

        LOGGER.info("CarLog: " + carStateDO.toString());

        if (selection == Selection.SELECT) {
            selectCar(driverDO, carStateDO);
        } else {
            deselectCar(driverDO, carStateDO);
        }
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
            Iterable<CarDO> allCars = carRepo.findByCarStateDO_CarStatus(carStatus);
            List<CarDTO> carDTOList = getCarDTOS(allCars);
            return carDTOList;
        } catch (javax.persistence.EntityNotFoundException enf) {
            LOGGER.warn("Record not found.", enf);
            throw new EntityNotFoundException("Car Records not found.");
        }
    }

    public List<CarDTO> findCarCharacteristics(String licenceNo, String make, String model, Integer seatCount) {
        return getCarDTOS(carRepo.findAllByLicenceNoLikeOrCarType_MakeOrCarType_ModelOrCarType_SeatCount(licenceNo, make, model, seatCount));
    }

    private List<CarDTO> getCarDTOS(Iterable<CarDO> allCars) {
        List<CarDTO> carDTOList = new ArrayList<>();
        allCars.forEach(carDO -> carDTOList.add(((CarDTO) carDOtoDTO.convert(carDO))));
        return carDTOList;
    }


    private void selectCar(DriverDO driverDO, CarStateDO carStateDO) throws CarAlreadySelectedException, CarAlreadyInUseException,
            DriverNotOnlineException, CarUnavailableException, NoCarSelectionException {

        validateCarSelectionRules(driverDO, carStateDO);

        //Save Selected Car.
        carStateRepo.save(carStateDO);
    }

    private void validateCarSelectionRules(DriverDO driverDO, CarStateDO carStateDO) throws CarAlreadySelectedException, CarAlreadyInUseException,
            DriverNotOnlineException, CarUnavailableException, NoCarSelectionException {

        boolean isDriverOnline = driverDO.getOnlineStatus() == OnlineStatus.ONLINE;

        if (!isDriverOnline) {
            throw new DriverNotOnlineException(DRIVER_NOT_ONLINE_EXCEPTION_MSG);
        }


        if (null == carStateDO.getCarStatus()) {
            throw new NoCarSelectionException(NO_CAR_SELECTED_EXCEPTION_MSG);
        } else if (carStateDO.getCarStatus() == CarStatus.IN_USE && driverDO.getId().equals(carStateDO.getDriverId())) {
            /*
             * Todo: Consider adding a new table 1-1 Rel that keeps track of which driver is using the car.
             */
            throw new CarAlreadySelectedException(CAR_ALREADY_SELECTED_EXCEPTION_MSG);
        } else if (carStateDO.getCarStatus() == CarStatus.UNAVAILABLE) {
            throw new CarUnavailableException(CAR_UNAVAILABLE_EXCEPTION_MSG);
        } else if (carStateDO.getCarStatus() == CarStatus.IN_USE) {
            throw new CarAlreadyInUseException(CAR_IN_USE_EXCEPTION_MSG);
        } else {
            CarLogDO log = new CarLogDO();
            log.setDriverId(driverDO.getId());
            log.setCreated(new Date());
            log.setComments("Driver selected vehicle.");

            carStateDO.setDriverId(driverDO.getId());
            carStateDO.setCarStatus(CarStatus.IN_USE);
            carStateDO.setUpdated(new Date());
            carStateDO.getCarDO().getCarLogDOS().add(log);
        }
    }


    private void deselectCar(DriverDO driverDO, CarStateDO carStateDO) throws DeselectionNotAllowedException {

        if (!driverDO.getId().equals(carStateDO.getDriverId())) {
            throw new DeselectionNotAllowedException(CAR_DESELECTION_EXCEPTION_MSG);
        }

        CarLogDO log = new CarLogDO();
        log.setDriverId(driverDO.getId());
        log.setCreated(new Date());
        log.setComments("Driver deselected vehicle.");

        carStateDO.setDriverId(null);
        carStateDO.setCarStatus(CarStatus.READY);
        carStateDO.setUpdated(new Date());
        carStateDO.getCarDO().getCarLogDOS().add(log);
    }
}

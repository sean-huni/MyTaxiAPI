package com.mytaxi.engine.impl;

import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainobject.car.CarLogDO;
import com.mytaxi.domainobject.car.CarStateDO;
import com.mytaxi.domainvalue.CarStatus;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.engine.CarSelectionRules;
import com.mytaxi.exception.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.service.car.impl
 * USER      : sean
 * DATE      : 12-Fri-Oct-2018
 * TIME      : 11:45
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Component
public class CarSelectionRulesImpl implements CarSelectionRules {
    private static final String DRIVER_NOT_ONLINE_EXCEPTION_MSG = "The driver is OFFLINE. Driver must be ONLINE to select a car.";
    private static final String NO_CAR_SELECTED_EXCEPTION_MSG = "No car selection made.";
    private static final String CAR_ALREADY_SELECTED_EXCEPTION_MSG = "You've already selected this car.";
    private static final String CAR_UNAVAILABLE_EXCEPTION_MSG = "The car is unavailable for selection.";
    private static final String CAR_IN_USE_EXCEPTION_MSG = "Car already being used by another driver.";

    public void validateCarSelectionRules(DriverDO driverDO, CarStateDO carStateDO) throws CarAlreadySelectedException, CarAlreadyInUseException,
            DriverNotOnlineException, CarUnavailableException, NoCarSelectionException {
        boolean isDriverOnline = driverDO.getOnlineStatus() == OnlineStatus.ONLINE;

        if (!isDriverOnline) {
            throw new DriverNotOnlineException(DRIVER_NOT_ONLINE_EXCEPTION_MSG);
        }


        if (null == carStateDO.getCarStatus()) {
            throw new NoCarSelectionException(NO_CAR_SELECTED_EXCEPTION_MSG);
        } else if (carStateDO.getCarStatus() == CarStatus.IN_USE && driverDO.getId().equals(carStateDO.getDriverId())) {
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

}

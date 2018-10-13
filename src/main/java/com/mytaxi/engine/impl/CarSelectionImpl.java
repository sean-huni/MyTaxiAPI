package com.mytaxi.engine.impl;

import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainobject.car.CarLogDO;
import com.mytaxi.domainobject.car.CarStateDO;
import com.mytaxi.domainvalue.CarStatus;
import com.mytaxi.engine.CarSelection;
import com.mytaxi.engine.CarSelectionRules;
import com.mytaxi.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.service.car
 * USER      : sean
 * DATE      : 12-Fri-Oct-2018
 * TIME      : 11:43
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Component
public class CarSelectionImpl implements CarSelection {
    private static final String CAR_DESELECTION_EXCEPTION_MSG = "Not allowed to deselect a car that is not assigned to the driver.";
    private final CarSelectionRules carSelectionRules;

    @Autowired
    public CarSelectionImpl(final CarSelectionRules carSelectionRules) {
        this.carSelectionRules = carSelectionRules;
    }

    public void selectCar(DriverDO driverDO, CarStateDO carStateDO) throws CarAlreadySelectedException, CarAlreadyInUseException,
            DriverNotOnlineException, CarUnavailableException, NoCarSelectionException {

        carSelectionRules.validateCarSelectionRules(driverDO, carStateDO);
    }


    public void deselectCar(DriverDO driverDO, CarStateDO carStateDO) throws DeselectionNotAllowedException {

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

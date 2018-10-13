package com.mytaxi.engine;

import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainobject.car.CarStateDO;
import com.mytaxi.exception.*;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.engine
 * USER      : sean
 * DATE      : 12-Fri-Oct-2018
 * TIME      : 13:24
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public interface CarSelection {
    void selectCar(DriverDO driverDO, CarStateDO carStateDO) throws CarAlreadySelectedException, CarAlreadyInUseException,
            DriverNotOnlineException, CarUnavailableException, NoCarSelectionException;

    void deselectCar(DriverDO driverDO, CarStateDO carStateDO) throws DeselectionNotAllowedException;
}

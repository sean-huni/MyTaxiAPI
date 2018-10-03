package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.car.CarDO;
import com.mytaxi.domainvalue.CarStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.dataaccessobject
 * USER      : sean
 * DATE      : 02-Tue-Oct-2018
 * TIME      : 01:47
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public interface CarRepo extends CrudRepository<CarDO, Long> {
    Optional<CarDO> findByLicenceNo(String licencePlate);

    Iterable<CarDO> findByCarState_CarStatus(CarStatus carStatus);
}

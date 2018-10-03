package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.car.CarStateDO;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.dataaccessobject
 * USER      : sean
 * DATE      : 02-Tue-Oct-2018
 * TIME      : 12:55
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public interface CarStateRepo extends CrudRepository<CarStateDO, Long> {
    Optional<CarStateDO> findByCarDO_LicenceNo(String licenceNo);
}

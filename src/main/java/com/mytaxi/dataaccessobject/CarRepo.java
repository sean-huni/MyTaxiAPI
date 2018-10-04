package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.car.CarDO;
import com.mytaxi.domainvalue.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
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
public interface CarRepo extends JpaRepository<CarDO, Long> {
    Optional<CarDO> findByLicenceNo(String licencePlate);

    Iterable<CarDO> findByCarStateDO_CarStatus(CarStatus carStatus);

    List<CarDO> findAllByLicenceNoLikeOrCarType_MakeOrCarType_ModelOrCarType_SeatCount(String licenceNo, String make, String model, Integer seatCount);
}

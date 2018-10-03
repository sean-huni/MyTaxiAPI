package com.mytaxi.controller.mapper.impl;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.car.CarDO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.controller.mapper.impl
 * USER      : sean
 * DATE      : 02-Tue-Oct-2018
 * TIME      : 01:55
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Component("carDtoToCarDO")
public class CarDtoToCarDo implements Converter<CarDTO, CarDO> {

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public CarDO convert(CarDTO source) {
        CarDO target = new CarDO();

        target.setId(source.getCarId());
        target.setLicenceNo(source.getLicensePlate());
        target.setAverageRating(source.getAverageRating());
        target.getCarStateDO().setCarStatus(source.getCarStatus());
        target.setMileage(source.getMileage());
        target.setNextService(source.getNextService());
        return target;
    }
}

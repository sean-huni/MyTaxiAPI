package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mytaxi.domainvalue.CarStatus;

import java.util.Date;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.datatransferobject
 * USER      : sean
 * DATE      : 27-Thu-Sep-2018
 * TIME      : 12:09
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {
    @JsonIgnore
    private Long id;
    private Long carId;
    private CarStatus carStatus;
    private String licensePlate;
    private Double averageRating;
    private Double mileage;
    private Date nextService;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public Date getNextService() {
        return nextService;
    }

    public void setNextService(Date nextService) {
        this.nextService = nextService;
    }
}

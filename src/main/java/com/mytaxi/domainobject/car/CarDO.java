package com.mytaxi.domainobject.car;

import com.mytaxi.domainobject.AbstractDO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.domainobject
 * USER      : sean
 * DATE      : 27-Thu-Sep-2018
 * TIME      : 17:48
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Entity(name = "car")
@Table(schema = "sat", name = "car")
public class CarDO extends AbstractDO {
    @Column(name = "average_rating")
    private Double averageRating;   //Shouldn't be stored.
    @Column(name = "licence_no")
    private String licenceNo;   // Licence Plate No.
    @Column(name = "mileage")
    private Double mileage;
    @Column(name = "next_service")
    private Date nextService;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_type_id")
    private CarTypeDO carType;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinColumn(name = "car_id")
    private List<CarLog> carLogs = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    private CarState carState;

    public CarDO() {
        super();
    }

    public CarState getCarState() {
        return carState;
    }

    public void setCarState(CarState carState) {
        this.carState = carState;
    }

    public CarTypeDO getCarType() {
        return carType;
    }

    public void setCarType(CarTypeDO carType) {
        this.carType = carType;
    }

    public List<CarLog> getCarLogs() {
        return carLogs;
    }

    public void setCarLogs(List<CarLog> carLogs) {
        this.carLogs = carLogs;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
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

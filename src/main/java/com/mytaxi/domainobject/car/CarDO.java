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
    private List<CarLogDO> carLogDOS = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "car_state_id")
    private CarStateDO carStateDO;

    public CarDO() {
        super();
    }

    public CarStateDO getCarStateDO() {
        return carStateDO;
    }

    public void setCarStateDO(CarStateDO carStateDO) {
        this.carStateDO = carStateDO;
    }

    public CarTypeDO getCarType() {
        return carType;
    }

    public void setCarType(CarTypeDO carType) {
        this.carType = carType;
    }

    public List<CarLogDO> getCarLogDOS() {
        return carLogDOS;
    }

    public void setCarLogDOS(List<CarLogDO> carLogDOS) {
        this.carLogDOS = carLogDOS;
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

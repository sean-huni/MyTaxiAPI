package com.mytaxi.domainobject.car;

import com.mytaxi.domainobject.AbstractDO;
import com.mytaxi.domainvalue.CarStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.domainobject.car
 * USER      : sean
 * DATE      : 02-Tue-Oct-2018
 * TIME      : 12:20
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Entity(name = "car_state")
@Table(schema = "sat", name = "car_state", uniqueConstraints = @UniqueConstraint(name = "uc_driver_id", columnNames = {"driver_id"}))
public class CarStateDO extends AbstractDO {
    @Column(name = "driver_id")
    private Long driverId;
    @Column(name = "car_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CarStatus carStatus;
    @Column(name = "created", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date created;
    @Column(name = "updated", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date updated;

    @OneToOne(fetch = FetchType.EAGER)
//    @MapsId
    @JoinColumn(name = "car_id")
    private CarDO carDO;

    public CarStateDO() {
        super();
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public CarDO getCarDO() {
        return carDO;
    }

    public void setCarDO(CarDO carDO) {
        this.carDO = carDO;
    }

    @Override
    public String toString() {
        return "CarStateDO{" +
                "driverId=" + driverId +
                ", carStatus=" + carStatus +
                ", created=" + created +
                ", updated=" + updated +
                ", carDO=" + carDO +
                '}';
    }
}

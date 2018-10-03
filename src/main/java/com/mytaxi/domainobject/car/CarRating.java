package com.mytaxi.domainobject.car;

import com.mytaxi.domainobject.AbstractDO;
import com.mytaxi.domainvalue.Condition;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.domainobject
 * USER      : sean
 * DATE      : 27-Thu-Sep-2018
 * TIME      : 17:24
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Entity(name = "car_rating")
@Table(schema = "sat", name = "car_rating")
public class CarRating extends AbstractDO {
    private Long driverId;
    private Long carId;
    private Double rating;
    private Condition condition;
    private String comments;
    private Date created;
    private Date lastUpdated;

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}

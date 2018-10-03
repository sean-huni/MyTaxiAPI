package com.mytaxi.domainobject.car;

import com.mytaxi.domainobject.AbstractDO;
import com.mytaxi.domainvalue.TripStatus;

import javax.persistence.*;
import java.util.Date;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.domainobject
 * USER      : sean
 * DATE      : 27-Thu-Sep-2018
 * TIME      : 17:07
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Entity(name = "car_log")
@Table(schema = "sat", name = "car_log")
public class CarLogDO extends AbstractDO {
    @Column(name = "driver_id")
    private Long driverId; //IN, OUT.
    @Column(name = "trip_status")
    @Enumerated(EnumType.STRING)
    private TripStatus tripStatus; // BREAKDOWN, Completed, . capture the trip status
    @Column(name = "tot_distance")
    private Double totDistance;  //Distance travelled per trip.
    @Column(name = "comments")
    private String comments;
    @Column(name = "created")
    private Date created;

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }

    public Double getTotDistance() {
        return totDistance;
    }

    public void setTotDistance(Double totDistance) {
        this.totDistance = totDistance;
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
}

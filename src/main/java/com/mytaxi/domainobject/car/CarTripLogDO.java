package com.mytaxi.domainobject.car;

import com.mytaxi.domainobject.AbstractDO;
import com.mytaxi.domainvalue.GeoCoordinate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.domainobject
 * USER      : sean
 * DATE      : 01-Mon-Oct-2018
 * TIME      : 23:04
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
///////////////////////////////////////////////////////////////////////////
// Stores info about the car (per-trip) while in use.
// Preferable every minute.
///////////////////////////////////////////////////////////////////////////
@Entity(name = "trip_log")
@Table(schema = "sat", name = "trip_log")
public class CarTripLogDO extends AbstractDO {
    private Long carLogId;
    private GeoCoordinate geoCoordinate;
    private Double distance; //Distance Travelled.
    private Timestamp timestamp;

    public CarTripLogDO() {
        super();
    }

    public Long getCarLogId() {
        return carLogId;
    }

    public void setCarLogId(Long carLogId) {
        this.carLogId = carLogId;
    }

    public GeoCoordinate getGeoCoordinate() {
        return geoCoordinate;
    }

    public void setGeoCoordinate(GeoCoordinate geoCoordinate) {
        this.geoCoordinate = geoCoordinate;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}

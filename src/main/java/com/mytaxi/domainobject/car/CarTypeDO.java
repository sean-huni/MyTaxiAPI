package com.mytaxi.domainobject.car;

import com.mytaxi.domainobject.AbstractDO;
import com.mytaxi.domainvalue.BodyStyle;
import com.mytaxi.domainvalue.EngineType;

import javax.persistence.*;
import java.util.Date;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.domainobject
 * USER      : sean
 * DATE      : 27-Thu-Sep-2018
 * TIME      : 14:02
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Entity(name = "car_type")
@Table(schema = "sat", name = "car_type")
public class CarTypeDO extends AbstractDO {
    @Column(name = "make")
    private String make;    //A.K.A the Brand of the car.
    @Column(name = "model")
    private String model;   //which type of brand.
    @Column(name = "year")
    private Date year;      //Manufacture date.
    @Column(name = "body_style")
    @Enumerated(EnumType.STRING)
    private BodyStyle bodyStyle;
    @Column(name = "engine_type")
    @Enumerated(EnumType.STRING)
    private EngineType engineType;
    @Column(name = "seat_count")
    private Integer seatCount;
    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacturer_id")
    private ManufacturerDO manufacturerDO;

    public CarTypeDO() {
        super();
    }

    public ManufacturerDO getManufacturerDO() {
        return manufacturerDO;
    }

    public void setManufacturerDO(ManufacturerDO manufacturerDO) {
        this.manufacturerDO = manufacturerDO;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public BodyStyle getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(BodyStyle bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

package com.mytaxi.domainobject.car;

import com.mytaxi.domainobject.AbstractDO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.domainobject.car
 * USER      : sean
 * DATE      : 27-Thu-Sep-2018
 * TIME      : 13:57
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Entity(name = "manufacturer")
@Table(schema = "sat", name = "manufacturer", uniqueConstraints = @UniqueConstraint(name = "mc_name", columnNames = {"name"}))
public class ManufacturerDO extends AbstractDO {
    @Column(name = "name", nullable = false)
    @NotNull(message = "Name can not be null!")
    private String name;

    @Column(name = "origin", nullable = false)
    @NotNull(message = "Country of origin can not be null!")
    private String origin;

    @Column(name = "service_toll_free", nullable = false)
    @NotNull(message = "Service toll free number can not be null!")
    private String serviceTollFree;

    @Column(name = "website", nullable = false)
    @NotNull(message = "Website can not be null!")
    private String website;

    @Column(name = "factory", nullable = false)
    @NotNull(message = "Factory can not be null!")
    private String factory;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacturer_id")
    private List<CarTypeDO> carTypeDOList = new ArrayList<>();

    public List<CarTypeDO> getCarTypeDOList() {
        return carTypeDOList;
    }

    public void setCarTypeDOList(List<CarTypeDO> carTypeDOList) {
        this.carTypeDOList = carTypeDOList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getServiceTollFree() {
        return serviceTollFree;
    }

    public void setServiceTollFree(String serviceTollFree) {
        this.serviceTollFree = serviceTollFree;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }
}

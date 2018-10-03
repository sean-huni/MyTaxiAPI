package com.mytaxi.domainobject;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.domainobject
 * USER      : sean
 * DATE      : 27-Thu-Sep-2018
 * TIME      : 12:34
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@MappedSuperclass
public abstract class AbstractDO {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

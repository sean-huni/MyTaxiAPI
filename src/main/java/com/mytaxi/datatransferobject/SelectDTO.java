package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mytaxi.domainvalue.SelectionState;

import javax.validation.constraints.NotNull;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.datatransferobject
 * USER      : sean
 * DATE      : 12-Fri-Oct-2018
 * TIME      : 22:27
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonDeserialize(as = SelectDTO.class)
public class SelectDTO {

    @NotNull(message = "Driver-ID can not be null!")
    private Long driverId;
    @NotNull(message = "Selection-State can not be null!")
    private SelectionState selectionState;
    @NotNull(message = "Licence-No can not be null!")
    private String licenceNo;

    public SelectDTO() {
        super();
    }

    public SelectDTO(@NotNull(message = "Driver-ID can not be null!") Long driverId, @NotNull(message = "Selection-State can not be null!") SelectionState selectionState, @NotNull(message = "Licence-No can not be null!") String licenceNo) {
        this.driverId = driverId;
        this.selectionState = selectionState;
        this.licenceNo = licenceNo;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public SelectionState getSelectionState() {
        return selectionState;
    }

    public void setSelectionState(SelectionState selectionState) {
        this.selectionState = selectionState;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }
}

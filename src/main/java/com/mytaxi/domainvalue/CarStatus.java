package com.mytaxi.domainvalue;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.domainvalue
 * USER      : sean
 * DATE      : 27-Thu-Sep-2018
 * TIME      : 16:49
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public enum CarStatus {
    READY,          // Ready/Available for use.
    IN_USE,          // Already selected to be used/in-use by a driver.
    UNAVAILABLE    // Not available for selection for service maintenance reasons.
}

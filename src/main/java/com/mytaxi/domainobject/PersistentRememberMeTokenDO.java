package com.mytaxi.domainobject;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.datatransferobject
 * USER      : sean
 * DATE      : 04-Thu-Oct-2018
 * TIME      : 00:43
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Entity
@Table(schema = "sat", name = "token")
public class PersistentRememberMeTokenDO extends AbstractDO {
    private String username;
    private String series;
    private String tokenValue;
    private Date date;

    public PersistentRememberMeTokenDO() {
        super();
    }

    public PersistentRememberMeTokenDO(String username, String series, String tokenValue, Date lastUsed) {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

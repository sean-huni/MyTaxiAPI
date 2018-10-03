package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverDTO {
    @JsonIgnore
    private Long id;

    @NotNull(message = "Firstname can not be null!")
    private String firstname;

    @NotNull(message = "Surname can not be null!")
    private String surname;

    @NotNull(message = "Online-status can not be null!")
    private OnlineStatus onlineStatus;

    @NotNull(message = "Username can not be null!")
    private String username;

    @NotNull(message = "Password can not be null!")
    private String password;

    private GeoCoordinate coordinate;


    private DriverDTO() {
    }


    private DriverDTO(Long id, String firstname, String surname, String username, OnlineStatus onlineStatus, String password, GeoCoordinate coordinate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
        this.onlineStatus = onlineStatus;
        this.coordinate = coordinate;
    }


    public static DriverDTOBuilder newBuilder() {
        return new DriverDTOBuilder();
    }


    @JsonProperty
    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }


    public GeoCoordinate getCoordinate() {
        return coordinate;
    }

    public static class DriverDTOBuilder {
        private Long id;
        private String firstname;
        private String surname;
        private OnlineStatus onlineStatus;
        private String username;
        private String password;
        private GeoCoordinate coordinate;


        public DriverDTOBuilder setId(Long id) {
            this.id = id;
            return this;
        }


        public DriverDTOBuilder setUsername(String username) {
            this.username = username;
            return this;
        }


        public DriverDTOBuilder setPassword(String password) {
            this.password = password;
            return this;
        }


        public DriverDTOBuilder setCoordinate(GeoCoordinate coordinate) {
            this.coordinate = coordinate;
            return this;
        }


        public DriverDTOBuilder setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public DriverDTOBuilder setOnlineStatus(OnlineStatus onlineStatus) {
            this.onlineStatus = onlineStatus;
            return this;
        }

        public DriverDTOBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }


        public DriverDTO createDriverDTO() {
            return new DriverDTO(id, firstname, surname, username, onlineStatus, password, coordinate);
        }

    }
}

package com.derteuffel.springbootecommerce.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "boutique")
public class Boutique {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String localisation;
    private String orangeNumber;
    private String mtnNumber;
    private String othernumber;
    @NotNull
    private Boolean status = false;
    private String activationCode;

    @ManyToOne
    private User user;


    public Boutique() {
    }

    public Boutique(String name, String localisation, String orangeNumber, String mtnNumber,
                    String othernumber, Boolean status, String activationCode) {
        this.name = name;
        this.localisation = localisation;
        this.orangeNumber = orangeNumber;
        this.mtnNumber = mtnNumber;
        this.othernumber = othernumber;
        this.status = status;
        this.activationCode = activationCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getOrangeNumber() {
        return orangeNumber;
    }

    public void setOrangeNumber(String orangeNumber) {
        this.orangeNumber = orangeNumber;
    }

    public String getMtnNumber() {
        return mtnNumber;
    }

    public void setMtnNumber(String mtnNumber) {
        this.mtnNumber = mtnNumber;
    }

    public String getOthernumber() {
        return othernumber;
    }

    public void setOthernumber(String othernumber) {
        this.othernumber = othernumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
}

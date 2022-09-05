package com.finetwork.Exercice2.clients.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.Column;

public class ClientDto {

    private String name;

    private String email;

    private Integer phone;

    private String dni;
    public ClientDto() {
    }

    public ClientDto(String name, String email, Integer phone, String dni) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getDni() {
        return dni;
    }
    public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (Exception ex) {
            return "{}";
        }
    }
}

package com.finetwork.Exercice2.clients.model;

public class ClientUpdateDto {

    private String name;

    private Integer phone;

    public ClientUpdateDto() {
    }

    public ClientUpdateDto(String name, Integer phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
}

package com.finetwork.Exercice2.clients.model;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ClientDto {

    private Long id;

    private Long templateId;

    private String[] receivers;

    private Boolean isOperator;

    private Integer priority;

    private String createdBy;

    public ClientDto() {
    }

    public ClientDto(Long templateId, String[] receivers, Boolean isOperator, Integer priority, String createdBy) {
        this.templateId = templateId;
        this.receivers = receivers;
        this.isOperator = isOperator;
        this.priority = priority;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public String[] getReceivers() {
        return receivers;
    }

    public Boolean getIsOperator() {
        return isOperator;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (Exception ex) {
            return "{}";
        }
    }
}

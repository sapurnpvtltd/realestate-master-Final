package com.purnabhu.sales.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="agent")
public class Agent {
    @Id
    private String agentId;
    private String agentName;
    private String agentMobileNo;
    private String agentEmailId;
    private Date agentDob;
    private String agentPan;
    private String agentDeals;
    private String agentAddress;
    private String agentCity;
    private String agentState;
    private String agentPinCode;

}

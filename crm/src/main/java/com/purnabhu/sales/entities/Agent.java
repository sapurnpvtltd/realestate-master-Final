package com.purnabhu.sales.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="agent")
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer agentId;

    @NotBlank
    private String agentName;
    @NotBlank
    @NotNull
    private String agentMobileNo;
    @Email
    private String agentEmailId;
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private Date agentDob;
    private String agentPan;
    private String agentDeals;
    private String agentAddress;
    private String agentCity;
    private String agentState;
    private String agentPinCode;

}

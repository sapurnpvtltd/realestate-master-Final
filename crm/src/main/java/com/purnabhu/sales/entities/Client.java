package com.purnabhu.sales.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer clientId;

    @NotBlank
    @NotNull
    private String clientName;

    @NotBlank
    @NotNull
    private String clientMobileNo;
    @Email
    @NotBlank
    @NotNull
    private String clientEmailId;
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private Date clientDob;
    private String clientPan;
    private String clientAddress;
    private String clientCity;
    private String clientState;
    private String clientPinCode;
    private String clientOccupation;
    private String clientOrganization;
    private String clientDesignation;
    private String source;

}

package com.purnabhu.sales.entities;

import jakarta.persistence.*;
import lombok.*;

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
    private String clientName;
    private String clientMobileNo;
    private String clientEmailId;
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

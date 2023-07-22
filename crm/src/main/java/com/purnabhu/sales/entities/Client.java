package com.purnabhu.sales.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private String clientId;
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

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
@Table(name="lead")
public class Lead {
    @Id
    private String leadId;
    private String clientName;
    private String clientMobileNo;
    private String clientEmailId;
    private Date clientLocation;
    private String propertyType;
    private String source;

}

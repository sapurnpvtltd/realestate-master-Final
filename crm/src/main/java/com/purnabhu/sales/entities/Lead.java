package com.purnabhu.sales.entities;

import jakarta.persistence.*;
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
@Table(name="leads")
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer leadId;
    private String clientName;
    private String clientMobileNo;
    private String clientEmailId;
    private String clientLocation;
    private String propertyType;
    private String source;

}

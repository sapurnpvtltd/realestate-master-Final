package com.purnabhu.sales.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Property")
public class property {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  int propid;
  String propTitle;
  String propType;
  String refAgent;
  String agentContact;
  String paymentMode;
  String loanAvailibilty;
  String propAmenities;
  String propDescription;
  double propSize;
  float propAge;
  @Column(name="propImage", length=1000)
  byte[] propImage;

}

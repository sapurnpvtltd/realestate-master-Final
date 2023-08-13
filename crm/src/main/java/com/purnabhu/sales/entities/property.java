package com.purnabhu.sales.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Property")
public class property {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  int propid;
  @ManyToOne(fetch=FetchType.LAZY)
  project projectassociatted;
  @NotBlank
  String propTitle;
  @NotBlank
  String propType;
  String refAgent;
  String agentContact;
  String paymentMode;
  String loanAvailibilty;

  @NotBlank
  String propDescription;
  double propSize;
  float propAge;
  /*@Column(name="propImage", length=1000)
  byte[] propImage;*/
  int imagecount;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
          name = "project_amenities",
          joinColumns = @JoinColumn(
                  name = "property_id", referencedColumnName = "propid"),
          inverseJoinColumns = @JoinColumn(
                  name ="amenities_id", referencedColumnName = "amenityid")
  )
  List<Amenities> amenities = new ArrayList<>();


}

package com.purnabhu.sales.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table
public class Amenities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int amenityid;
    @Column(name = "amenityname")
    String amenityname;
}

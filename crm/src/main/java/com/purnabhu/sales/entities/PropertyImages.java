package com.purnabhu.sales.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.mapping.Property;

@Getter
@Setter
@Entity
@Table(name = "property_image")
public class PropertyImages {

    @ManyToOne
    @JoinColumn(name="propid", nullable=false)
    private property property;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    @Lob
    @Column(name = "imagedata" , columnDefinition="LONGBLOB")
    private byte[] imageData;

}
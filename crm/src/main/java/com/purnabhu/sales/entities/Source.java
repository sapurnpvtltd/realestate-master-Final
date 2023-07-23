package com.purnabhu.sales.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RestController;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    int id;
    String sourceName;
}

package com.purnabhu.sales.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="project")
public class project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private int pid;

    private String pname,plocation,parea,pdesc;
    private int uid;
    private Date pdate;

}

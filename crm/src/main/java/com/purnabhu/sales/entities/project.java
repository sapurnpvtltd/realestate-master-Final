package com.purnabhu.sales.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="project")
public class project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private int pid;

    @NotBlank
    private String pname;
    private String plocation;
    private String    parea;
    private String     pdesc;
    private int uid;
    private Date pdate;
    /*@OneToMany(mappedBy="project")
    private List<property> property;*/
}

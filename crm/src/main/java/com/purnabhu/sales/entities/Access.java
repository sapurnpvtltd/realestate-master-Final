package com.purnabhu.sales.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "access")
public class Access {
    @Id
    private String accessRoleId;
    private String accessModule;
    private String accessName;
}

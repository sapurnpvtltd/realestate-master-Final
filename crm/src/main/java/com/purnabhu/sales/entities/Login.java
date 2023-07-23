package com.purnabhu.sales.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "login")
public class Login {
    @Id
    private String loginId;
    private String loginRoleId;
    private String loginUserName;
    private String userPassword;

}

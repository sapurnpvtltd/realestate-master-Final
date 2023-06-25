package com.purnabhu.sales.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "useremailid")
        })
public class User {
    @Id
    private String userId;
    private String userName;
    private String userMobileNo;
    private String userEmailId;
    private String userAddress;
    private String userStatus;
    private String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(
                    name ="role_id", referencedColumnName = "roleId")
    )
    private Set<Roles> roles = new HashSet<>();

}

package com.purnabhu.sales.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "userName should not be blank")
    @Size(min = 3,message = "userName should be at least 3 chars")
    private String userName;
    private String userMobileNo;

    @Email
    private String userEmailId;
    private String userAddress;
    private String userStatus;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 3,message = "password should be at least 3 chars")
    @NonNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(
                    name ="role_id", referencedColumnName = "roleId")
    )
    private Set<Roles> roles = new HashSet<>();

}

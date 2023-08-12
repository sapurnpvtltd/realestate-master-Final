package com.purnabhu.sales.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JwtRequest {


    @NotBlank(message = "username is mandatory")
    @Size(min = 3,message = "username should be at least 3 chars")
    private String username;

    @NotBlank(message = "password is mandatory")
    @Size(min = 3,message = "password should be at least 3 chars")
    private String password;
}
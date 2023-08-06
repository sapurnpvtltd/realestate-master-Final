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

    @NotBlank
    @NotEmpty
    private String username;

    @NotBlank
    @NotEmpty
    private String password;
}
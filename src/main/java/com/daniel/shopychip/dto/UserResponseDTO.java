package com.daniel.shopychip.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String country;
    private LocalDateTime registrationDate;
    private String profilePictureUrl;
    private Set<String> roles;
}
package com.daniel.shopychip.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPatchDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String countryCode;
    private String profilePictureUrl;
}
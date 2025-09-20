package com.daniel.shopychip.mapper;

import com.daniel.shopychip.dto.UserRequestDTO;
import com.daniel.shopychip.dto.UserResponseDTO;
import com.daniel.shopychip.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDTO toResponseDTO(User user) {
        if (user == null) return null;
        return UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .country(user.getCountry())
                .registrationDate(user.getRegistrationDate())
                .profilePictureUrl(user.getProfilePictureUrl())
                .roles(user.getRoles() != null
                        ? user.getRoles().stream().map(r -> r.getName()).collect(java.util.stream.Collectors.toSet())
                        : null)
                .build();
    }

    public User toEntity(UserRequestDTO dto) {
        if (dto == null) return null;
        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .country(dto.getCountry())
                .profilePictureUrl(dto.getProfilePictureUrl())
                .build();
    }

    public void updateEntity(User user, UserRequestDTO dto) {
        if (dto.getFirstName() != null) user.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) user.setLastName(dto.getLastName());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getPassword() != null) user.setPassword(dto.getPassword());
        if (dto.getPhone() != null) user.setPhone(dto.getPhone());
        if (dto.getCountry() != null) user.setCountry(dto.getCountry());
        if (dto.getProfilePictureUrl() != null) user.setProfilePictureUrl(dto.getProfilePictureUrl());
    }
}
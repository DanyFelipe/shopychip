package com.daniel.shopychip.service;

import com.daniel.shopychip.dto.UserRequestDTO;
import com.daniel.shopychip.dto.UserResponseDTO;
import com.daniel.shopychip.model.User;
import com.daniel.shopychip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        log.info("Creating user with email: {}", userRequestDTO.getEmail());
        User user = User.builder()
                .firstName(userRequestDTO.getFirstName())
                .lastName(userRequestDTO.getLastName())
                .email(userRequestDTO.getEmail())
                .password(userRequestDTO.getPassword()) // In production, hash the password!
                .phone(userRequestDTO.getPhone())
                .country(userRequestDTO.getCountry())
                .profilePictureUrl(userRequestDTO.getProfilePictureUrl())
                .registrationDate(LocalDateTime.now())
                .build();
        User savedUser = userRepository.save(user);
        return mapToDTO(savedUser);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        log.info("Fetching user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        log.info("Updating user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setPhone(userRequestDTO.getPhone());
        user.setCountry(userRequestDTO.getCountry());
        user.setProfilePictureUrl(userRequestDTO.getProfilePictureUrl());
        User updated = userRepository.save(user);
        return mapToDTO(updated);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Deleting user with id: {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        log.info("Fetching all users");
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private UserResponseDTO mapToDTO(User user) {
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
                        ? user.getRoles().stream().map(r -> r.getName()).collect(Collectors.toSet())
                        : null)
                .build();
    }
}
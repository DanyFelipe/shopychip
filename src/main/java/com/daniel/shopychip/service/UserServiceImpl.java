package com.daniel.shopychip.service;

import com.daniel.shopychip.dto.UserRequestDTO;
import com.daniel.shopychip.dto.UserResponseDTO;
import com.daniel.shopychip.dto.UserPatchDTO;
import com.daniel.shopychip.exception.NotFoundException;
import com.daniel.shopychip.model.User;
import com.daniel.shopychip.model.Role;
import com.daniel.shopychip.mapper.UserMapper;
import com.daniel.shopychip.repository.UserRepository;
import com.daniel.shopychip.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        log.info("Creating user with email: {}", userRequestDTO.getEmail());
        Role defaultRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        User user = userMapper.toEntity(userRequestDTO);

        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setRegistrationDate(LocalDateTime.now());
        user.setRoles(Set.of(defaultRole));

        User savedUser = userRepository.save(user);
        return userMapper.toResponseDTO(savedUser);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        log.info("Fetching user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return userMapper.toResponseDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        log.info("Updating user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        userMapper.updateEntity(user, userRequestDTO);

        if (userRequestDTO.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        }
        User updated = userRepository.save(user);
        return userMapper.toResponseDTO(updated);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Deleting user with id: {}", id);
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        log.info("Fetching all users");
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserResponseDTO patchUser(Long id, UserPatchDTO patchDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        // Solo actualiza los campos que vengan en PATCH, nunca la contraseña
        if (patchDTO.getFirstName() != null) user.setFirstName(patchDTO.getFirstName());
        if (patchDTO.getLastName() != null) user.setLastName(patchDTO.getLastName());
        if (patchDTO.getEmail() != null) user.setEmail(patchDTO.getEmail());
        if (patchDTO.getPhone() != null) user.setPhone(patchDTO.getPhone());
        if (patchDTO.getCountry() != null) user.setCountry(patchDTO.getCountry());
        if (patchDTO.getProfilePictureUrl() != null) user.setProfilePictureUrl(patchDTO.getProfilePictureUrl());

        User updated = userRepository.save(user);
        return userMapper.toResponseDTO(updated);
    }
}
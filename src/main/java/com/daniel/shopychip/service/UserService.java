package com.daniel.shopychip.service;

import com.daniel.shopychip.dto.UserRequestDTO;
import com.daniel.shopychip.dto.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO getUserById(Long id);
    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);
    void deleteUser(Long id);
    List<UserResponseDTO> getAllUsers();
}
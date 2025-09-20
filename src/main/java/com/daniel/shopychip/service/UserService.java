package com.daniel.shopychip.service;

import com.daniel.shopychip.dto.UserRequestDTO;
import com.daniel.shopychip.dto.UserResponseDTO;
import com.daniel.shopychip.dto.UserPatchDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO getUserById(Long id);
    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);
    UserResponseDTO patchUser(Long id, UserPatchDTO patchDTO);
    void deleteUser(Long id);
    List<UserResponseDTO> getAllUsers();
}
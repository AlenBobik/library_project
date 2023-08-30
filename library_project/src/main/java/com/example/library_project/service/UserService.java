package com.example.library_project.service;

import com.example.library_project.dto.UserDto;
import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    UserDto returnByUserEmail(String ime);

    UserDto returnByUsername(String username);

    UserDto registerUser(String email, String username, String password);

    UserDto updateUser(String email, UserDto userDto);

    UserDto deleteUser(String email);

    UserDto dodajZaposlenegaUserju(String email, String oznakaPogodbe);
}

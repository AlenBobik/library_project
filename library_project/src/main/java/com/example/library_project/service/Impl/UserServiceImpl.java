package com.example.library_project.service.Impl;

import com.example.library_project.dto.UserDto;
import com.example.library_project.entities.Oseba;
import com.example.library_project.entities.User;
import com.example.library_project.entities.Zaposlen;
import com.example.library_project.enums.Roles;
import com.example.library_project.enums.Status;
import com.example.library_project.mappers.UserMapper;
import com.example.library_project.repository.*;
import com.example.library_project.securityConfig.jwt.JwtTokenUtil;
import com.example.library_project.service.UserService;
import java.util.List;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Data
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    private final OsebaRepository osebaRepository;
    private final IzkaznicaRepository izkaznicaRepository;
    private final ObvestiloRepository obvestiloRepository;
    private final ZaposlenRepository zaposlenRepository;

    private JwtTokenUtil jwtTokenUtil;

    @Autowired @Lazy private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> findAll() {
        return userMapper.mapUserToDtoList(userRepository.findAll());
    }

    @Override
    public UserDto returnByUserEmail(String email) {
        User user =
                userRepository
                        .findByOsebaOsebaEmail(email)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Uporabniski racun ne obstaja z imenom: " + email));

        return userMapper.convertToUserDto(user);
    }

    @Override
    public UserDto returnByUsername(String username) {
        User user =
                userRepository
                        .findByUsername(username)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Uporabniski racun ne obstaja z imenom: "
                                                        + username));

        return userMapper.convertToUserDto(user);
    }

    @Override
    public UserDto registerUser(String email, String username, String password) {
        User newUser = new User();

        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Uporabniško ime je že uporabljeno.");
        }

        Oseba oseba =
                osebaRepository
                        .findByOsebaEmail(email)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Uporabnik ne obstaja z email: " + email));

        newUser.setUsername(username);
        newUser.setOseba(oseba);
        newUser.setUserStatus(Status.ACTIVE);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRole(Roles.USER);
        newUser.setAccountNonExpired(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setEnabled(true);

        return userMapper.convertToUserDto(userRepository.save(newUser));
    }

    @Override
    public UserDto updateUser(String email, UserDto userDto) {
        User user =
                userRepository
                        .findByOsebaOsebaEmail(email)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Uporabniski racun ne obstaja z email: " + email));

        userMapper.updateValuesOfExistingUser(userDto, user);

        return userMapper.convertToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto deleteUser(String email) {
        User user =
                userRepository
                        .findByOsebaOsebaEmail(email)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Uporabniski racun ne obstaja z email: " + email));

        user.setUserStatus(Status.INACTIVE);

        return userMapper.convertToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto dodajZaposlenegaUserju(String email, String oznakaPogodbe) {
        User racun =
                userRepository
                        .findByOsebaOsebaEmail(email)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Uporabniski racun ne obstaja z email: " + email));

        Zaposlen zaposlen =
                zaposlenRepository
                        .findByZaposlenOznakaPogodbe(oznakaPogodbe)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Zaposlen ne obstaja z oznako pogodbe: "
                                                        + oznakaPogodbe));

        // racun.setZaposlen(zaposlen);

        return userMapper.convertToUserDto(userRepository.save(racun));
    }
}

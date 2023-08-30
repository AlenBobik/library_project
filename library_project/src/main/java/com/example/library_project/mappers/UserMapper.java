package com.example.library_project.mappers;

import com.example.library_project.dto.UserDto;
import com.example.library_project.entities.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "osebaEmail", source = "oseba.osebaEmail")
    @Mapping(target = "obvestiloId", source = "obvestilo.obvestiloId")
    @Mapping(target = "zaposlenOznakaPogodbe", source = "zaposlen.zaposlenOznakaPogodbe")
    UserDto convertToUserDto(User user);

    @Mapping(target = "oseba", ignore = true)
    @Mapping(target = "obvestilo", ignore = true)
    @Mapping(target = "zaposlen", ignore = true)
    @Mapping(target = "userStatus", ignore = true)
    @Mapping(target = "role", ignore = true)
    User mapToUser(UserDto userDto);

    @Mapping(target = "oseba", ignore = true)
    @Mapping(target = "obvestilo", ignore = true)
    @Mapping(target = "zaposlen", ignore = true)
    @Mapping(target = "userStatus", ignore = true)
    @Mapping(target = "role", ignore = true)
    void updateValuesOfExistingUser(UserDto userDto, @MappingTarget User user);

    List<UserDto> mapUserToDtoList(List<User> all);
}

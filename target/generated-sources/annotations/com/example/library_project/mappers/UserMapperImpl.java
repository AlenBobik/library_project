package com.example.library_project.mappers;

import com.example.library_project.dto.UserDto;
import com.example.library_project.entities.Obvestilo;
import com.example.library_project.entities.Oseba;
import com.example.library_project.entities.User;
import com.example.library_project.entities.Zaposlen;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-05T13:57:03+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto convertToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setOsebaEmail( userOsebaOsebaEmail( user ) );
        userDto.setObvestiloId( userObvestiloObvestiloId( user ) );
        userDto.setZaposlenOznakaPogodbe( userZaposlenZaposlenOznakaPogodbe( user ) );
        userDto.setUsername( user.getUsername() );
        userDto.setPassword( user.getPassword() );
        if ( user.getUserStatus() != null ) {
            userDto.setUserStatus( user.getUserStatus().name() );
        }
        if ( user.getRole() != null ) {
            userDto.setRole( user.getRole().name() );
        }

        return userDto;
    }

    @Override
    public User mapToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( userDto.getUsername() );
        user.password( userDto.getPassword() );

        return user.build();
    }

    @Override
    public void updateValuesOfExistingUser(UserDto userDto, User user) {
        if ( userDto == null ) {
            return;
        }

        user.setUsername( userDto.getUsername() );
        user.setPassword( userDto.getPassword() );
    }

    @Override
    public List<UserDto> mapUserToDtoList(List<User> all) {
        if ( all == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( all.size() );
        for ( User user : all ) {
            list.add( convertToUserDto( user ) );
        }

        return list;
    }

    private String userOsebaOsebaEmail(User user) {
        if ( user == null ) {
            return null;
        }
        Oseba oseba = user.getOseba();
        if ( oseba == null ) {
            return null;
        }
        String osebaEmail = oseba.getOsebaEmail();
        if ( osebaEmail == null ) {
            return null;
        }
        return osebaEmail;
    }

    private long userObvestiloObvestiloId(User user) {
        if ( user == null ) {
            return 0L;
        }
        Obvestilo obvestilo = user.getObvestilo();
        if ( obvestilo == null ) {
            return 0L;
        }
        long obvestiloId = obvestilo.getObvestiloId();
        return obvestiloId;
    }

    private String userZaposlenZaposlenOznakaPogodbe(User user) {
        if ( user == null ) {
            return null;
        }
        Zaposlen zaposlen = user.getZaposlen();
        if ( zaposlen == null ) {
            return null;
        }
        String zaposlenOznakaPogodbe = zaposlen.getZaposlenOznakaPogodbe();
        if ( zaposlenOznakaPogodbe == null ) {
            return null;
        }
        return zaposlenOznakaPogodbe;
    }
}

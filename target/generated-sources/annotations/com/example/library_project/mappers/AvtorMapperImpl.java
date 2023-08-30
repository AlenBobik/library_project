package com.example.library_project.mappers;

import com.example.library_project.dto.AvtorDto;
import com.example.library_project.entities.Avtor;
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
public class AvtorMapperImpl implements AvtorMapper {

    @Override
    public AvtorDto convertToAvtorDto(Avtor avtor) {
        if ( avtor == null ) {
            return null;
        }

        AvtorDto avtorDto = new AvtorDto();

        avtorDto.setAvtorId( avtor.getAvtorId() );
        avtorDto.setAvtorIme( avtor.getAvtorIme() );
        avtorDto.setAvtorPriimek( avtor.getAvtorPriimek() );

        return avtorDto;
    }

    @Override
    public Avtor mapDtoToAvtor(AvtorDto avtorDto) {
        if ( avtorDto == null ) {
            return null;
        }

        Avtor avtor = new Avtor();

        avtor.setAvtorIme( avtorDto.getAvtorIme() );
        avtor.setAvtorPriimek( avtorDto.getAvtorPriimek() );

        return avtor;
    }

    @Override
    public void updateValuesOfExistingAvtor(AvtorDto avtorDto, Avtor avtor) {
        if ( avtorDto == null ) {
            return;
        }

        avtor.setAvtorIme( avtorDto.getAvtorIme() );
        avtor.setAvtorPriimek( avtorDto.getAvtorPriimek() );
    }

    @Override
    public List<AvtorDto> mapAvtorToDtoList(List<Avtor> avtor) {
        if ( avtor == null ) {
            return null;
        }

        List<AvtorDto> list = new ArrayList<AvtorDto>( avtor.size() );
        for ( Avtor avtor1 : avtor ) {
            list.add( convertToAvtorDto( avtor1 ) );
        }

        return list;
    }
}

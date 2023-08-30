package com.example.library_project.mappers;

import com.example.library_project.dto.ObvestiloDto;
import com.example.library_project.entities.Obvestilo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-05T13:57:04+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class ObvestiloMapperImpl implements ObvestiloMapper {

    @Override
    public ObvestiloDto convertToObvestiloDto(Obvestilo obvestilo) {
        if ( obvestilo == null ) {
            return null;
        }

        ObvestiloDto obvestiloDto = new ObvestiloDto();

        obvestiloDto.setObvestiloId( obvestilo.getObvestiloId() );
        obvestiloDto.setObvestiloIme( obvestilo.getObvestiloIme() );
        obvestiloDto.setObvestiloOpis( obvestilo.getObvestiloOpis() );
        obvestiloDto.setObvestiloDatum( obvestilo.getObvestiloDatum() );

        return obvestiloDto;
    }

    @Override
    public Obvestilo mapDtoToObvestilo(ObvestiloDto obvestiloDto) {
        if ( obvestiloDto == null ) {
            return null;
        }

        Obvestilo obvestilo = new Obvestilo();

        obvestilo.setObvestiloIme( obvestiloDto.getObvestiloIme() );
        obvestilo.setObvestiloOpis( obvestiloDto.getObvestiloOpis() );
        obvestilo.setObvestiloDatum( obvestiloDto.getObvestiloDatum() );

        return obvestilo;
    }

    @Override
    public void updateValuesOfExistingObvestilo(ObvestiloDto obvestiloDto, Obvestilo obvestilo) {
        if ( obvestiloDto == null ) {
            return;
        }

        obvestilo.setObvestiloIme( obvestiloDto.getObvestiloIme() );
        obvestilo.setObvestiloOpis( obvestiloDto.getObvestiloOpis() );
        obvestilo.setObvestiloDatum( obvestiloDto.getObvestiloDatum() );
    }

    @Override
    public List<ObvestiloDto> mapObvestiloToDtoList(List<Obvestilo> obvestila) {
        if ( obvestila == null ) {
            return null;
        }

        List<ObvestiloDto> list = new ArrayList<ObvestiloDto>( obvestila.size() );
        for ( Obvestilo obvestilo : obvestila ) {
            list.add( convertToObvestiloDto( obvestilo ) );
        }

        return list;
    }
}

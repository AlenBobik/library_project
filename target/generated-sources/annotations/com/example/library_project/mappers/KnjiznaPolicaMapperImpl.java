package com.example.library_project.mappers;

import com.example.library_project.dto.KnjiznaPolicaDto;
import com.example.library_project.entities.KnjiznaPolica;
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
public class KnjiznaPolicaMapperImpl implements KnjiznaPolicaMapper {

    @Override
    public KnjiznaPolicaDto convertToKnjiznaPolicaDto(KnjiznaPolica knjiznaPolica) {
        if ( knjiznaPolica == null ) {
            return null;
        }

        KnjiznaPolicaDto knjiznaPolicaDto = new KnjiznaPolicaDto();

        knjiznaPolicaDto.setKnjiznaPolicaId( knjiznaPolica.getKnjiznaPolicaId() );
        knjiznaPolicaDto.setKnjiznaPolicaOznaka( knjiznaPolica.getKnjiznaPolicaOznaka() );

        return knjiznaPolicaDto;
    }

    @Override
    public KnjiznaPolica mapToKnjiznaPolica(KnjiznaPolicaDto knjiznaPolicaDto) {
        if ( knjiznaPolicaDto == null ) {
            return null;
        }

        KnjiznaPolica knjiznaPolica = new KnjiznaPolica();

        knjiznaPolica.setKnjiznaPolicaOznaka( knjiznaPolicaDto.getKnjiznaPolicaOznaka() );

        return knjiznaPolica;
    }

    @Override
    public void updateValuesOfExistingKnjiznaPolica(KnjiznaPolicaDto knjiznaPolicaDto, KnjiznaPolica knjiznaPolica) {
        if ( knjiznaPolicaDto == null ) {
            return;
        }

        knjiznaPolica.setKnjiznaPolicaOznaka( knjiznaPolicaDto.getKnjiznaPolicaOznaka() );
    }

    @Override
    public List<KnjiznaPolicaDto> mapKnjiznaPolicaToDtoList(List<KnjiznaPolica> knjiznaPolica) {
        if ( knjiznaPolica == null ) {
            return null;
        }

        List<KnjiznaPolicaDto> list = new ArrayList<KnjiznaPolicaDto>( knjiznaPolica.size() );
        for ( KnjiznaPolica knjiznaPolica1 : knjiznaPolica ) {
            list.add( convertToKnjiznaPolicaDto( knjiznaPolica1 ) );
        }

        return list;
    }
}

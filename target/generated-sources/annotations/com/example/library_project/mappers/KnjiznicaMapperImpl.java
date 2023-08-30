package com.example.library_project.mappers;

import com.example.library_project.dto.KnjiznicaDto;
import com.example.library_project.entities.Knjiznica;
import com.example.library_project.enums.KnjiznicaStatus;
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
public class KnjiznicaMapperImpl implements KnjiznicaMapper {

    @Override
    public KnjiznicaDto convertToKnjiznicaDto(Knjiznica knjiznica) {
        if ( knjiznica == null ) {
            return null;
        }

        KnjiznicaDto knjiznicaDto = new KnjiznicaDto();

        knjiznicaDto.setKnjiznicaId( knjiznica.getKnjiznicaId() );
        knjiznicaDto.setKnjiznicaIme( knjiznica.getKnjiznicaIme() );
        knjiznicaDto.setKnjiznicaNaslov( knjiznica.getKnjiznicaNaslov() );
        knjiznicaDto.setKnjiznicaPostnaStevilka( knjiznica.getKnjiznicaPostnaStevilka() );
        if ( knjiznica.getKnjiznicaStatus() != null ) {
            knjiznicaDto.setKnjiznicaStatus( knjiznica.getKnjiznicaStatus().name() );
        }

        return knjiznicaDto;
    }

    @Override
    public Knjiznica mapDtoToKnjiznica(KnjiznicaDto knjiznicaDto) {
        if ( knjiznicaDto == null ) {
            return null;
        }

        Knjiznica knjiznica = new Knjiznica();

        knjiznica.setKnjiznicaIme( knjiznicaDto.getKnjiznicaIme() );
        knjiznica.setKnjiznicaNaslov( knjiznicaDto.getKnjiznicaNaslov() );
        knjiznica.setKnjiznicaPostnaStevilka( knjiznicaDto.getKnjiznicaPostnaStevilka() );
        if ( knjiznicaDto.getKnjiznicaStatus() != null ) {
            knjiznica.setKnjiznicaStatus( Enum.valueOf( KnjiznicaStatus.class, knjiznicaDto.getKnjiznicaStatus() ) );
        }

        return knjiznica;
    }

    @Override
    public void updateValuesOfExistingknjiznica(KnjiznicaDto knjiznicaDto, Knjiznica knjiznica) {
        if ( knjiznicaDto == null ) {
            return;
        }

        knjiznica.setKnjiznicaIme( knjiznicaDto.getKnjiznicaIme() );
        knjiznica.setKnjiznicaNaslov( knjiznicaDto.getKnjiznicaNaslov() );
        knjiznica.setKnjiznicaPostnaStevilka( knjiznicaDto.getKnjiznicaPostnaStevilka() );
    }

    @Override
    public List<KnjiznicaDto> mapKnjiznicaToDtoList(List<Knjiznica> knjiznica) {
        if ( knjiznica == null ) {
            return null;
        }

        List<KnjiznicaDto> list = new ArrayList<KnjiznicaDto>( knjiznica.size() );
        for ( Knjiznica knjiznica1 : knjiznica ) {
            list.add( convertToKnjiznicaDto( knjiznica1 ) );
        }

        return list;
    }
}
